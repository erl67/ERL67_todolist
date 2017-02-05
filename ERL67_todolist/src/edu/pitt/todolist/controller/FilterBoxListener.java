package edu.pitt.todolist.controller;
/**
 * Class ResetButton / provides functions for filtering users
 * @author ERL67
 * created: 5FEB17
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FilterBoxListener implements ActionListener {

	private Controller controller;

	public FilterBoxListener(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.getModel().filterUsers(controller.getView().cboFilterSelect());
	}

}