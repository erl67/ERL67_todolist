package edu.pitt.todolist.view;
/**
 * Class View / builds view
 * @author ERL67
 * created: 19JAN17
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.pitt.todolist.controller.AddButton;
import edu.pitt.todolist.controller.DeleteButton;
import edu.pitt.todolist.model.ListItem;
import edu.pitt.todolist.model.Model;
import edu.pitt.todolist.model.UserItem;

public class View extends JPanel implements ListSelectionListener, ActionListener {

	private JFrame frmMain;
	private JLabel lblToDo;
	private JButton btnDelete;
	private JButton btnAdd;
	private JButton btnRst;
	private static JTextField jtfInput;
	private static JList<ListItem> list1;
	private static DefaultListModel<ListItem> listModel;
	//	private static JList<String> list1;
	//	private static DefaultListModel<String> listModel;
	private static JComboBox<UserItem> cboUser;
	private static JComboBox<UserItem> cboFilter;
	private JScrollPane scrollPane;

	public View() {

		frmMain = new JFrame("IS1017 Assignment4 // ERL67");
		frmMain.setContentPane(this);
		//frmMain.setLayout(null);
		frmMain.setBounds(300, 150, 500, 650);
		frmMain.setResizable(false);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMain.setAlwaysOnTop(true);
		
		frmMain.getContentPane().setBackground(Color.DARK_GRAY);

		lblToDo = new JLabel("To Do List");
		lblToDo.setForeground(Color.RED);
		lblToDo.setFont(new Font("Serif", Font.BOLD, 25));
		lblToDo.setBounds(200, 10, 150, 30);

		btnDelete = new JButton("Remove Item");
		btnDelete.setBounds(290, 500, 110, 25);

		btnAdd = new JButton("Add Item");
		btnAdd.setBounds(100, 500, 110, 25);

		btnRst = new JButton("Reset");
		btnRst.setBounds(200, 575, 100, 20);

		jtfInput = new JTextField(50);
		jtfInput.setBounds(100, 50, 300, 25);
		jtfInput.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		jtfInput.setBackground(Color.white);

		cboUser = new JComboBox<UserItem>();
		cboUser.setBounds(100,100,300,22);
		cboFilter = new JComboBox<UserItem>();
		cboFilter.setBounds(120,540,250,22);

		listModel = new DefaultListModel<ListItem>();
		list1 = new JList<ListItem>(listModel);
		//		listModel = new DefaultListModel<String>();
		//		list1 = new JList<String>(listModel);
		list1.setBounds(100, 150, 300, 300);
		list1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		list1.addListSelectionListener(this);
		list1.setBackground(Color.pink);
		list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane = new JScrollPane(list1, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		frmMain.getContentPane().add(scrollPane);

		frmMain.add(lblToDo);
		frmMain.add(btnDelete);
		frmMain.add(btnAdd);
		frmMain.add(btnRst);
		frmMain.add(getJtfInput());
		frmMain.add(list1);
		frmMain.add(cboUser);
		frmMain.add(cboFilter);
		frmMain.add(scrollPane);
		frmMain.setVisible(true);

		ActionListener addListener = new AddButton(null);
		btnAdd.addActionListener(addListener);

		ActionListener deleteListener = new DeleteButton(null);
		btnDelete.addActionListener(deleteListener);

		btnRst.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Model.resetButton();
			}
		});

		cboFilter.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Model.filterUsers((UserItem)cboFilter.getSelectedItem());
			}
		});

	}

	public static void fillComboBox(){
		cboFilter.addItem(new UserItem (-1, " ", "    SELECT HERE TO FILTER BY USER"));
		for (UserItem u: Model.getUserList().values()){
			cboUser.addItem(u);
			cboFilter.addItem(u);
		}
	}

	public static UserItem cboUserSelect() {
		return (UserItem) cboUser.getSelectedItem();
	}

	public static JList<ListItem> getList1() {
		return list1;
	}

	public static DefaultListModel<ListItem> getListModel() {
		return listModel;
	}

	public static void setListModel(DefaultListModel<ListItem> listModel) {
		View.listModel = listModel;
	}

	//	public static JList<String> getList1() {
	//		return list1;
	//	}
	//
	//	public static DefaultListModel<String> getListModel() {
	//		return listModel;
	//	}
	//
	//	public static void setListModel(DefaultListModel<String> listModel) {
	//		View.listModel = listModel;
	//	}

	public static JTextField getJtfInput() {
		return jtfInput;
	}

	public static void resetJtfInput() {
		jtfInput.setText(null);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
