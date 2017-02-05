package edu.pitt.todolist.view;
import java.awt.BorderLayout;
/**
 * Class View / builds view
 * @author ERL67
 * created: 19JAN17
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.pitt.todolist.controller.AddButton;
import edu.pitt.todolist.controller.DeleteButton;
import edu.pitt.todolist.model.ListItem;
import edu.pitt.todolist.model.Model;
import edu.pitt.todolist.model.UserItem;

public class View implements ListSelectionListener, ActionListener {

	private JFrame frmMain;
	private JLabel lblToDo;
	private JButton btnDelete;
	private JButton btnAdd;
	private JButton btnRst;
	private static JTextField jtfInput;
	private static JList<ListItem> list1;
	private static DefaultListModel<ListItem> listModel;
	private static JComboBox<UserItem> cboUser;
	private static JComboBox<UserItem> cboFilter;
	private JScrollPane scrollPane;
	private JPanel panel;

	public View() {

		frmMain = new JFrame("IS1017 Assignment4 // ERL67");
		frmMain.setLayout(new FlowLayout());
		panel = new JPanel();
		panel.setLayout(new FlowLayout());

		frmMain.getContentPane().setBackground(Color.PINK);

		frmMain.setContentPane(panel);
		frmMain.setBounds(350, 150, 500, 650);
		frmMain.setResizable(false);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMain.setAlwaysOnTop(true);

		lblToDo = new JLabel("To Do List");
		lblToDo.setForeground(Color.RED);
		lblToDo.setFont(new Font("Serif", Font.BOLD, 25));
		lblToDo.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel txtPanel = new JPanel();
		txtPanel.setLayout(new BorderLayout(5,5));
		jtfInput = new JTextField(40);
		jtfInput.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
		jtfInput.setBackground(Color.WHITE);
//		jtfInput.setText("     Enter Tasks Here");
		jtfInput.setMargin(new Insets(10,10,10,10));
		txtPanel.add(lblToDo, BorderLayout.PAGE_START);
		txtPanel.add(jtfInput, BorderLayout.CENTER);

		cboUser = new JComboBox<UserItem>();
		cboUser.setBackground(Color.lightGray);
		cboUser.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 5, 5, 5), new EtchedBorder()));
		txtPanel.add(cboUser, BorderLayout.PAGE_END);

		JPanel listPanel = new JPanel();
		listPanel.setLayout(new FlowLayout());
		listModel = new DefaultListModel<ListItem>();
		list1 = new JList<ListItem>(listModel);

		list1.setPreferredSize(new Dimension (400,400));
		list1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		list1.addListSelectionListener(this);
		list1.setBackground(Color.pink);
		list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	
		//scrollPane = new JScrollPane(list1, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//		frmMain.getContentPane().add(scrollPane);
		listPanel.add(list1);
		//listPanel.add(scrollPane);

		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout());
		btnDelete = new JButton("Remove Item");
		btnAdd = new JButton("Add Item");
		btnRst = new JButton("Reset");
		btnAdd.setPreferredSize(new Dimension(120, 40));
		btnDelete.setPreferredSize(new Dimension(120, 40));
		btnRst.setPreferredSize(new Dimension(120, 40));
		btnPanel.add(btnAdd); btnPanel.add(btnDelete); btnPanel.add(btnRst);

		cboFilter = new JComboBox<UserItem>();
		cboFilter.setPreferredSize(new Dimension(300,20));

		panel.add(txtPanel);
		panel.add(listPanel);
		panel.add(btnPanel);
		panel.add(cboFilter);
		txtPanel.setBackground(Color.DARK_GRAY);
		btnPanel.setBackground(Color.DARK_GRAY);
		panel.setBackground(Color.DARK_GRAY);
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
