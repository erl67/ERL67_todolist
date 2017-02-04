package edu.pitt.todolist.controller;
/**
 * Class Controller / builds controls
 * @author ERL67
 * created: 19JAN17
 */
import edu.pitt.todolist.model.Model;
import edu.pitt.todolist.view.View;

public class Controller {

	private View view;
	private Model model;

	private static AddButton addButton;
	private static DeleteButton deleteButton;

	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
	}

	public View getView() {
		return view;
	}

	public Model getModel() {
		return model;
	}

	public static AddButton getAddButton() {
		return addButton;
	}

	public static DeleteButton getDeleteButton() {
		return deleteButton;
	}

}
