package edu.pitt.todolist.controller;
/**
 * Class Controller / builds controls
 * @author ERL67
 * created: 19JAN17
 */
import edu.pitt.todolist.model.Model;
import edu.pitt.todolist.view.View;

public class Controller {
	private AddButton addButtonListener;
	private DeleteButton deleteButtonListener;
	private ResetButton resetButtonListener;
	private FilterBoxListener filterBoxListener;
	private View view;
	private Model model;

	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
		view.getAddButton().addActionListener(new AddButton(this));
		view.getDeleteButton().addActionListener(new DeleteButton(this));
		view.getResetButton().addActionListener(new ResetButton(this));
		View.getCboFilter().addActionListener(new FilterBoxListener(this));
	}

	public AddButton getAddButtonListener() {
		return addButtonListener;
	}

	public DeleteButton getDeleteButtonListener() {
		return deleteButtonListener;
	}

	public ResetButton getResetButtonListener() {
		return resetButtonListener;
	}

	public FilterBoxListener getFilterBoxListener() {
		return filterBoxListener;
	}

	public View getView() {
		return view;
	}

	public Model getModel() {
		return model;
	}

}
