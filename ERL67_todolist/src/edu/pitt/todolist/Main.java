package edu.pitt.todolist;
/**
 * Class Main / tests view models
 * @author ERL67
 * created: 19JAN17
 */
import edu.pitt.todolist.controller.Controller;
import edu.pitt.todolist.model.Model;
import edu.pitt.todolist.model.UserItem;
import edu.pitt.todolist.view.View;

public class Main {

	public static void main(String[] args) {

		View frame = new View();
		Model model = new Model();
		Controller controller = new Controller(frame, model);
		
		//Model.closeDB();
		

	}


}

