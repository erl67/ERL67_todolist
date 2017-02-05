package edu.pitt.todolist.model;

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

	public String getFname() {
		return fname;
	}

	public String getLname() {
		return lname;
	}
	
	public String lastDotF(){
		return lname + ", " + fname.substring(0, 1) + ".";
	}
	
	@Override
	public String toString(){
		return lname + ", " + fname + " (" + getId() +")";
	}

}
