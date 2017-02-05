package edu.pitt.todolist.controller;
/**
 * Class AddButton / provides functions for adding list items
 * @author ERL67
 * created: 19JAN17
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

import edu.pitt.todolist.model.ListItem;
import edu.pitt.todolist.model.Model;
import edu.pitt.todolist.view.View;

public class AddButton implements ActionListener {

	private Controller controller;

	public AddButton(Controller controller) {
		this.controller = controller;
	}

	public AddButton() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {		//prevent blank entries and input errors	
			if (!View.getJtfInput().getText().equalsIgnoreCase("")) {	
				ListItem item = new ListItem (View.getJtfInput().getText(), new Timestamp(System.currentTimeMillis()));
				Model.addListItem(item, View.cboUserSelect());
			} else {
				System.out.println("No item entered");
			}	
		} catch (Exception f) {
			System.out.println(f.getMessage());
		}
	}

}
