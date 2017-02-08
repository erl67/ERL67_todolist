package edu.pitt.todolist.model;

/**
 * Class UserItem / definesUserItems
 * @author ERL67
 * created: 3FEB17
 */
public class UserItem {
	
	private int id;
	private String fname;
	private String lname;
	
	public UserItem(int id, String fname, String lname) {
		this.id = id;
		this.fname = fname;
		this.lname = lname;
	}

	public int getId() {
		return id;
	}
	
	public String lastDotF(){
		return lname + ", " + fname.substring(0, 1) + ".";
	}
	
	@Override
	public String toString(){
		return lname + ", " + fname + " (" + getId() +")";
	}

}
