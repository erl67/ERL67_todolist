package edu.pitt.todolist.controller;
/**
 * Class DeleteButton / provides functions for deleting list items
 * @author ERL67
 * created: 19JAN17
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.pitt.todolist.model.ListItem;

public class DeleteButton implements ActionListener {

	Controller controller;

	public DeleteButton(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// prevent Array errors if trying to delete empty list
		try { 
			int idx = controller.getView().getList1().getSelectedIndex();
			ListItem item = controller.getView().getListModel().elementAt(idx);
			controller.getModel().deleteListItem(item);
		} catch (Exception f) {
			System.out.println(f.getMessage() + " No item selected");
			 //f.printStackTrace();
		}
	}

}
