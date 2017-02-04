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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.pitt.todolist.controller.AddButton;
import edu.pitt.todolist.controller.DeleteButton;
import edu.pitt.todolist.model.ListItem;

public class View implements ListSelectionListener, ActionListener {

	private JFrame frmMain;
	private JLabel lblToDo;
	private JButton btnDelete;
	private JButton btnAdd;
	private static JTextField jtfInput;
	private static JList<ListItem> list1;
	private static DefaultListModel<ListItem> listModel;
	private JScrollPane scrollPane;

	public View() {

		frmMain = new JFrame("IS1017 Assignment3 // ERL67");
		frmMain.setLayout(null);
		frmMain.setBounds(300, 300, 500, 500);
		frmMain.setResizable(false);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMain.setAlwaysOnTop(true);
		frmMain.getContentPane().setBackground(Color.DARK_GRAY);

		lblToDo = new JLabel("To Do List");
		lblToDo.setForeground(Color.RED);
		lblToDo.setFont(new Font("Serif", Font.BOLD, 25));
		lblToDo.setBounds(200, 10, 150, 30);

		btnDelete = new JButton("Remove Item");
		btnDelete.setBounds(290, 400, 110, 25);

		btnAdd = new JButton("Add Item");
		btnAdd.setBounds(100, 400, 110, 25);

		jtfInput = new JTextField(50);
		jtfInput.setBounds(100, 50, 300, 25);
		jtfInput.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		jtfInput.setBackground(Color.white);

		listModel = new DefaultListModel<ListItem>();
		list1 = new JList<ListItem>(listModel);
		list1.setBounds(100, 90, 300, 300);
		list1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		list1.addListSelectionListener(this);
		list1.setBackground(Color.pink);
		list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane = new JScrollPane(list1, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
		frmMain.add(lblToDo);
		frmMain.add(btnDelete);
		frmMain.add(btnAdd);
		frmMain.add(getJtfInput());
		frmMain.add(list1);
		frmMain.add(scrollPane);
		frmMain.setVisible(true);

		ActionListener addListener = new AddButton(null);
		btnAdd.addActionListener(addListener);

		ActionListener deleteListener = new DeleteButton(null);
		btnDelete.addActionListener(deleteListener);

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
