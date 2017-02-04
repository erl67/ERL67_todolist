package edu.pitt.todolist.controller;
/**
 * Class DeleteButton / provides functions for deleting list items
 * @author ERL67
 * created: 19JAN17
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.pitt.todolist.model.Model;
import edu.pitt.todolist.view.View;

public class DeleteButton implements ActionListener {

	private Controller controller;

	public DeleteButton(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// prevent Array errors if trying to delete empty list
		try { 
			Model.deleteListItem(View.getListModel().elementAt(View.getList1().getSelectedIndex()));
		} catch (Exception f) {
			System.out.println(f.getMessage() + " No item selected");
		}
	}

}
