package edu.pitt.todolist.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import edu.pitt.todolist.view.View;


public class Model {

	private static HashMap<Integer, UserItem> userMap = new HashMap<Integer, UserItem>();
	private static HashMap<ListItem, UserItem> taskMap = new HashMap<ListItem, UserItem>();

	private static Connection con;
	private final String DB_URL = "jdbc:mysql://sis-teach-01.sis.pitt.edu:3306/erl67is1017";
	private final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private final String DB_USERNAME = "erl67is1017";
	private final String DB_PASSWORD = "erl67@pitt.edu";

	public Model() {
		try {
			Class.forName(DB_DRIVER).newInstance();
			con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			pullDbUsers (con);
			pullDbTasks (con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pullDbUsers(Connection con) {

		String query = "SELECT * FROM erl67is1017.user ORDER BY lname ASC;";

		try {
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			System.out.println(stmt.toString());

			while (rs.next()) {
				UserItem user = new UserItem(rs.getInt(1), rs.getString(2), rs.getString(3));
				userMap.put(user.getId(), user);
				System.out.println("DB:"+rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " // Map: " + user.getId()+", " + userMap.get(user.getId()));
				View.getCboUser().addItem(user);
				View.getCboFilter().addItem(user);
			}
			rs.close(); stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	} 

	public static void pullDbTasks(Connection con) {
		String query = "SELECT * FROM todolist JOIN user_todo ON todolist.id = user_todo.fk_todo_id JOIN user ON user_todo.fk_user_id = user.user_id ORDER BY user.lname, todolist.id;";

		try {
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			System.out.println(stmt.toString());
			
			while (rs.next()) {

				ListItem item = new ListItem(rs.getString(2), rs.getInt(1), rs.getTimestamp(3));
				taskMap.put(item, userMap.get(rs.getInt("fk_user_id")));

				View.getListModel().addElement(new ListItem(item.getDescription() + "       [" + taskMap.get(item).lastDotF() + "] ", item.getId(), item.getTimestamp()));
				System.out.println(item.getId() + " " + item.getDescription() + " " + item.getTimestamp() + " " + taskMap.get(item));

				try {	//need to slow down otherwise database will outrun the GUI
					Thread.sleep(50);
				} catch (InterruptedException e) {}
			}
			rs.close(); stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	} 

	public void addListItem(ListItem item, UserItem user) {
		try {
			//Enter task into Database
			PreparedStatement stmt = con.prepareStatement("INSERT INTO erl67is1017.todolist (description) VALUES (?)");
			stmt.setString(1, item.getDescription());  
			stmt.executeUpdate();

			//Assign taskID to class because we need to wait for the database to assign an ID, otherwise FK conflicts and auto-increment errors will snowball  
			int id = getmaxID();
			item.setId(id);

			//Assign user to task and store in DB
			PreparedStatement stmt2 = con.prepareStatement("INSERT INTO erl67is1017.user_todo (fk_todo_id, fk_user_id) VALUES (?,?)");
			stmt2.setInt(1, id); 
			stmt2.setInt(2, user.getId());
			stmt2.executeUpdate();

			System.out.println(stmt.toString().substring(47) + "   " + stmt2.toString().substring(47));
			stmt.close(); stmt2.close();

			taskMap.put(item, user);
			View.getListModel().addElement(new ListItem(item.getDescription() + "       [" + taskMap.get(item).lastDotF() + "] ",item.getId(), item.getTimestamp()));
			View.getJtfInput().setText("");
			System.out.println("Item Added");
		}
		catch (MySQLIntegrityConstraintViolationException f) {
			System.out.println("Duplicate item, unable to add"); //f.printStackTrace();
			JOptionPane.showMessageDialog(View.getCboUser(),
					"This task is already in the list",
					"Try Again",
					JOptionPane.ERROR_MESSAGE);
		}
		catch (SQLException e) {
			System.out.println("SQL error"); e.printStackTrace();
		}
	}

	public void deleteListItem(ListItem item) {
		int id = View.getListModel().elementAt(View.getList1().getSelectedIndex()).getId();
		for (ListItem mapitem: taskMap.keySet()){
			if (mapitem.getId() == item.getId()) {
				taskMap.remove(mapitem);
				View.getListModel().removeElement(item);
				System.out.println("Item deleted");
				try {
					PreparedStatement stmt = con.prepareStatement("DELETE FROM erl67is1017.user_todo WHERE fk_todo_id=?");
					PreparedStatement stmt2 = con.prepareStatement("DELETE FROM erl67is1017.todolist WHERE todolist.id=?");
					stmt.setInt(1, id);   
					stmt2.setInt(1, id);
					System.out.println(stmt.toString().substring(47) + "   " + stmt2.toString().substring(47));		//substring to hide JDBC class junk
					stmt.executeUpdate();
					stmt2.executeUpdate();
					stmt.close(); stmt2.close();
				}
				catch (SQLException e) {
					System.out.println("Item not available");  e.printStackTrace();
				}
				break;
			}		
		}	
	}

	public static HashMap<ListItem, UserItem> getTaskList() {
		return taskMap;
	}

	public static HashMap<Integer, UserItem> getUserList() {
		return userMap;
	}

	/**
	 * @return int of the max database task rows
	 * this was required because the database rejects any previous numbers even if they were deleted
	 * therefore it is impossible to assign a task ID until the database provides it
	 */
	public static int getmaxID() {
		int i = 0;
		try {
			PreparedStatement st = con.prepareStatement("SELECT LAST_INSERT_ID();");
			ResultSet rs = st.executeQuery();
			rs.next();
			i = rs.getInt(1);
			rs.close(); st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 			
		return i;	
	}

	public static void filterUsers(UserItem user) {
		View.getListModel().clear();
		if (user==null) {
			for (ListItem item: taskMap.keySet()){
				View.getListModel().addElement(new ListItem(item.getDescription() + "       [" + taskMap.get(item).lastDotF() + "] ",item.getId(), item.getTimestamp()));			
			}
		} else {
			for (ListItem item: taskMap.keySet()){
				if (taskMap.get(item) == user) {
					View.getListModel().addElement(item);
				}		
			}
		}
	}

	public void reset() {
		taskMap.clear();
		pullDbTasks (con);	
	}

	public static void closeDB(){
		try {
			con.close();
			System.out.println(con.toString() +" "+ con.isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
