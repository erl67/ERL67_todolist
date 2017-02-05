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
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.pitt.todolist.model.ListItem;
import edu.pitt.todolist.model.UserItem;

public class View implements ListSelectionListener, ActionListener {
	
	private JFrame frmMain;
	private JButton btnDelete;
	private JButton btnAdd;
	private JButton btnRst;
	private static JTextField jtfInput;
	private static JList<ListItem> list1;
	private static DefaultListModel<ListItem> listModel;
	private static JComboBox<UserItem> cboUser;
	private static JComboBox<UserItem> cboFilter;
	private JScrollPane list1scr;
	private JPanel panel = new JPanel();
	private JPanel txtPanel = new JPanel();
	private JPanel usrPanel = new JPanel();
	private JPanel filterPanel = new JPanel();
	private JPanel listPanel = new JPanel();
	private JPanel btnPanel = new JPanel();

	public View() {
		
		frmMain = new JFrame("IS1017 Assignment4 // ERL67");
		frmMain.setLayout(new BorderLayout());
		panel.setLayout(new BorderLayout());		
		txtPanel.setLayout(new BorderLayout(5,5));
		usrPanel.setLayout(new BorderLayout(5,5));
		filterPanel.setLayout(new BorderLayout(5,5));
		btnPanel.setLayout(new FlowLayout());
		listPanel.setLayout(new FlowLayout());
		
		listPanel.setBackground(Color.DARK_GRAY);
		txtPanel.setBackground(Color.DARK_GRAY);
		usrPanel.setBackground(Color.DARK_GRAY);
		btnPanel.setBackground(Color.DARK_GRAY);
		filterPanel.setBackground(Color.DARK_GRAY);
		panel.setBackground(Color.DARK_GRAY);
		
		listPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(0, Color.BLACK, Color.BLACK),  BorderFactory.createEtchedBorder(0, Color.LIGHT_GRAY, Color.BLACK)));
		txtPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(0, Color.BLACK, Color.BLACK),  BorderFactory.createEtchedBorder(0, Color.LIGHT_GRAY, Color.BLACK)));
		btnPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(1, Color.BLACK, Color.BLACK), BorderFactory.createEtchedBorder(0, Color.GREEN, Color.BLACK)));
		filterPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(1, Color.BLACK, Color.BLACK), BorderFactory.createRaisedBevelBorder()));

		frmMain.setContentPane(panel);
		frmMain.setBounds(350, 150, 500, 575);
		frmMain.setResizable(false);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMain.setAlwaysOnTop(true);

		JLabel lblToDo = new JLabel("To Do List");
		lblToDo.setForeground(Color.RED);
		lblToDo.setFont(new Font("Serif", Font.BOLD, 25));
		lblToDo.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblTask = new JLabel("Enter Task");
		lblTask.setForeground(Color.GREEN);
		lblTask.setFont(new Font("San Serif", Font.BOLD, 12));
		
		JLabel lblUser = new JLabel("Assign User");
		lblUser.setForeground(Color.GREEN);
		lblUser.setFont(new Font("San Serif", Font.BOLD, 12));
		
		JLabel lblFilter = new JLabel("Filter Users");
		lblFilter.setForeground(Color.GREEN);
		lblFilter.setFont(new Font("San Serif", Font.BOLD, 12));

		jtfInput = new JTextField(30);
		jtfInput.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(1, Color.BLACK, Color.BLACK), BorderFactory.createEtchedBorder(1, Color.BLACK, Color.GREEN)));
		jtfInput.setBackground(Color.WHITE);
		jtfInput.setPreferredSize(new Dimension(400,30));
		
		txtPanel.add(lblToDo, BorderLayout.PAGE_START);
		txtPanel.add(lblTask, BorderLayout.WEST);
		txtPanel.add(jtfInput, BorderLayout.CENTER);

		cboUser = new JComboBox<UserItem>();
		cboUser.setBackground(Color.lightGray);
		cboUser.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		cboUser.insertItemAt(null, 0);
		
		usrPanel.add(lblUser, BorderLayout.WEST);
		usrPanel.add(cboUser, BorderLayout.CENTER);
		txtPanel.add(usrPanel, BorderLayout.PAGE_END);	
		
		listModel = new DefaultListModel<ListItem>();
		list1 = new JList<ListItem>(listModel);
		list1.setBackground(Color.pink);
		list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list1.addListSelectionListener(this);
		list1.setVisibleRowCount(20);
		list1scr = new JScrollPane(list1, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		listPanel.add(list1scr);

		btnDelete = new JButton("Remove Item");
		btnAdd = new JButton("Add Item");
		btnRst = new JButton("Reset View");
		btnAdd.setPreferredSize(new Dimension(120, 40));
		btnDelete.setPreferredSize(new Dimension(120, 40));
		btnRst.setPreferredSize(new Dimension(120, 40));
		btnPanel.add(btnAdd); btnPanel.add(btnDelete); btnPanel.add(btnRst);

		cboFilter = new JComboBox<UserItem>();	
		cboFilter.insertItemAt(null, 0);
		filterPanel.add(lblFilter, BorderLayout.WEST);
		filterPanel.add(cboFilter, BorderLayout.CENTER);
		
		panel.add(txtPanel, BorderLayout.PAGE_START);
		listPanel.add(btnPanel);
		panel.add(listPanel, BorderLayout.CENTER);
		panel.add(filterPanel, BorderLayout.PAGE_END);
		frmMain.setVisible(true);
	}
	
	public static JComboBox<UserItem> getCboUser() {
		return cboUser;
	}
	
	public static JComboBox<UserItem> getCboFilter() {
		return cboFilter;
	}

	public static UserItem cboUserSelect() {
		return (UserItem) cboUser.getSelectedItem();
	}
	
	public static UserItem cboFilterSelect() {
		return (UserItem) cboFilter.getSelectedItem();
	}

	public static JList<ListItem> getList1() {
		return list1;
	}

	public static  DefaultListModel<ListItem> getListModel() {
		return listModel;
	}

	public static JTextField getJtfInput() {
		return jtfInput;
	}
	
	public JButton getDeleteButton() {
		return btnDelete;
	}
	
	public JButton getResetButton() {
		return btnRst;
	}
	public JButton getAddButton() {
		return btnAdd;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
