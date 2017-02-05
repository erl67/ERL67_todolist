package edu.pitt.todolist.controller;
/**
 * Class ResetButton / provides functions for resetting view
 * @author ERL67
 * created: 5FEB17
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetButton implements ActionListener {

	private Controller controller;

	public ResetButton(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.getView().getCboFilter().setSelectedIndex(0);
		controller.getView().getCboUser().setSelectedIndex(0);
		controller.getView().getListModel().clear();
		controller.getModel().reset();
	}

}