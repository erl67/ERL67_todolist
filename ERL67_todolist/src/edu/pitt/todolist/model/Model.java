package edu.pitt.todolist.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Class Model / defines model
 * @author ERL67
 * created: 19JAN17
 */
import java.util.Vector;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import edu.pitt.todolist.view.View;

public class Model {

	private static Vector<ListItem> todoList = new Vector<ListItem>();
	private static Connection con;

	public Model() {

		final String DB_URL = "jdbc:mysql://sis-teach-01.sis.pitt.edu:3306/erl67is1017";
		final String DB_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_USERNAME = "erl67is1017";
		final String DB_PASSWORD = "erl67@pitt.edu";	

		try {
			Class.forName(DB_DRIVER).newInstance();
			con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			pullDbItems (con);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void pullDbItems(Connection con) {
		String query = "SELECT * FROM todolist ORDER BY id ASC";
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				ListItem item = new ListItem(rs.getString(2), rs.getInt(1), rs.getTimestamp(3));
				todoList.add(item);
				View.getListModel().addElement(item);
				System.out.println(item.getId() + " " + item.getDescription() + " " + item.getTimestamp());

				//need to slow down otherwise database will outrun the GUI
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {}
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	} 

	public static void addListItem(ListItem item) {

		String query = "INSERT INTO erl67is1017.todolist (description) VALUES ('" + item.getDescription() + "')";
		System.out.println(query);
		
		try {
			Statement statement = con.createStatement();
			statement.executeUpdate(query);
			statement.close();
			todoList.add(item);
			View.getListModel().addElement(item);
			View.resetJtfInput();
			System.out.println("Item Added");
		}
		catch (MySQLIntegrityConstraintViolationException f) {
			System.out.println("Duplicate item, unable to add"); // f.printStackTrace();
		}
		catch (SQLException e) {
			System.out.println("SQL error"); //e.printStackTrace();
		}
	}

	public static void deleteListItem(ListItem item) {

		int id = View.getListModel().elementAt(View.getList1().getSelectedIndex()).getId();

		String query = "DELETE FROM erl67is1017.todolist WHERE todolist.id = " + id;
		System.out.println(query);
		
		try {
			Statement statement = con.createStatement();
			statement.executeUpdate(query);
			statement.close();
			todoList.remove(item);
			View.getListModel().removeElement(item);
			System.out.println("Item deleted");
		}
		catch (SQLException e) {
			System.out.println("Item not available");  e.printStackTrace();
		}
	}

	public static Vector<ListItem> getList() {
		return todoList;
	}

	public static int getmaxID() {
		int i = 0;
		for (ListItem item : todoList) {
			if (item.getId() > i) i = item.getId();
		}	
		//System.out.println("Current maxID = " + todoList.elementAt(todoList.size()-1).getId() + " loop=" + i);
		return i+1;
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
