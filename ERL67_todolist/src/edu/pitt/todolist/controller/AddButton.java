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

public class AddButton implements ActionListener {

	 Controller controller;

	public AddButton(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {		//prevent blank entries and input errors	
			if (!controller.getView().getJtfInput().getText().equalsIgnoreCase("") && !(controller.getView().cboUserSelect()==null)) {	
				ListItem item = new ListItem (controller.getView().getJtfInput().getText(), new Timestamp(System.currentTimeMillis()));
				controller.getModel().addListItem(item, controller.getView().cboUserSelect());
			} else {
				System.out.println("You must enter a task and assign a user.");
				JOptionPane.showMessageDialog(controller.getView().getCboUser(),
					    "You must Both enter a task and assign a user.",
					    "Try Again",
					    JOptionPane.ERROR_MESSAGE);
			}	
		} catch (Exception f) {
			System.out.println(f.getMessage());
		}
	}

}
