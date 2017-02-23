package edu.pitt.todolist.controller;
/**
 * Class AddButton / provides functions for adding list items
 * @author ERL67
 * created: 19JAN17
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

import javax.swing.JOptionPane;

import edu.pitt.todolist.model.ListItem;
import edu.pitt.todolist.model.Model;
import edu.pitt.todolist.model.UserItem;
import edu.pitt.todolist.view.View;

public class AddButton implements ActionListener {

	Controller controller;

	public AddButton(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {		
			
			String description = controller.getView().getJtfInput().getText();
			UserItem user = controller.getView().cboUserSelect();
			
			int parentid;
			
			try {
				//get Id of parent task
				parentid = View.getListModel().elementAt(View.getList1().getSelectedIndex()).getId();
				//automatically get user of parent task, overrides selection box user so subtask gets same user
				for (ListItem item: Model.getTaskList().keySet()){
					if (item.getId() == parentid) {
						user = Model.getTaskList().get(item);
					}		
				}
				
			} catch (ArrayIndexOutOfBoundsException f) {
				parentid = 0;
			}
			System.out.println(parentid);
			
			if (!description.equalsIgnoreCase("") && !(user==null)) {	//prevent blank entries and input errors	
				ListItem item = new ListItem (description, new Timestamp(System.currentTimeMillis()));
				controller.getModel().addListItem(item, user, parentid);
			} else {
				System.out.println("You must enter a task and assign a user.");
				JOptionPane.showMessageDialog(controller.getView().getDeleteButton(),
						"You must Both enter a task and assign a user.",
						"Try Again",
						JOptionPane.ERROR_MESSAGE);
			}				
		} catch (Exception f) {
			System.out.println(f.getMessage());
		}
	}
}
