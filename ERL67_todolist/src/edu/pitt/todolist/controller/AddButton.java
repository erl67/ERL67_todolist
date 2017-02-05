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
import edu.pitt.todolist.model.UserItem;

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
			
			if (!description.equalsIgnoreCase("") && !(user==null)) {	//prevent blank entries and input errors	
				ListItem item = new ListItem (description, new Timestamp(System.currentTimeMillis()));
				controller.getModel().addListItem(item, user);
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
