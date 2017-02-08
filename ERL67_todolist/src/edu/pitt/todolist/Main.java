package edu.pitt.todolist;
/**
 * Class Main / tests view models
 * @author ERL67
 * created: 19JAN17
 */
import edu.pitt.todolist.controller.Controller;
import edu.pitt.todolist.model.Model;
import edu.pitt.todolist.view.View;

public class Main {
	public static void main(String[] args) {
<<<<<<< HEAD
		View frame = new View();
		Model model = new Model();
		@SuppressWarnings("unused")
		Controller controller = new Controller(frame, model);	
=======

		//View frame = new View();
		//Model model = new Model();
		Controller controller = new Controller(new View(), new Model());
		
>>>>>>> refs/remotes/origin/master
		//Model.closeDB();
	}
}

