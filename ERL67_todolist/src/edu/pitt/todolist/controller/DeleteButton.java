package edu.pitt.todolist.controller;
/**
 * Class DeleteButton / provides functions for deleting list items
 * @author ERL67
 * created: 19JAN17
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import edu.pitt.todolist.model.ListItem;
import edu.pitt.todolist.view.View;

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
			System.out.println(item.getChildren().size() + " leaf=" +item.isLeaf());
			if (item.isLeaf() == true) {
				System.out.println("Deleting leaf " + item.getId());
				controller.getModel().deleteListItem(item);
			} else {
				JOptionPane.showMessageDialog(View.getList1(),
						"Cannot delete parent tasks without completing or removing subtasks",
						"Error",
						JOptionPane.ERROR_MESSAGE);
				//below code is if for deleting subtasks automatically
//				for (ListItem childs : item.getChildren()) {
//					System.out.println("Deleting child: " + childs.getDescription());
//					controller.getModel().deleteListItem(childs);
//				}
//				controller.getModel().deleteListItem(item);
			}
		} catch (NullPointerException e1) {
			//System.out.println(e1.getMessage()); //e1.printStackTrace();
		}
		catch (Exception f) {
			System.out.println(f.getMessage() + " No item selected"); //f.printStackTrace();
		}
	}

}
