package edu.pitt.todolist.model;

import java.sql.Timestamp;
import java.util.Vector;

/**
 * Class ListItem / defines ListItems
 * @author ERL67
 * created: 19JAN17
 */

public class ListItem {

	private String description;
	private int id;
	private  Timestamp timestamp;
	
	//tree stuff
	private ListItem parent = null;
	private Vector<ListItem> children = new Vector<ListItem>();
	

	/**
	 * @param description
	 * @param id
	 * @param timestamp
	 * This constructor can cause conflicts with the database, because we need to wait for the DB to assign the ID based on the DBengine
	 * Only used for tasks already in the DB, otherwise use the one below, and setID() based on what row the DB assigns
	 */
	public ListItem(String description, int id, Timestamp timestamp, ListItem parent) {
		this.description = description;
		this.id = id;
		this.timestamp = timestamp;
		this.parent = parent;
		this.children = new Vector<ListItem>();
		if(parent != null){
			this.parent.addChild(this);
		}
	}
	
	
	public ListItem(String description, int id, Timestamp timestamp) {
		this.description = description;
		this.id = id;
		this.timestamp = timestamp;
		//this.parent = parent;
		this.children = new Vector<ListItem>();
		if(parent != null){
			this.parent.addChild(this);
		}
	}

	/**
	 * @param description
	 * @param timestamp
	 */
	public ListItem(String description, Timestamp timestamp) {
		this.description = description;
		this.timestamp = timestamp;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	@Override
	public String toString() {
		return this.description;
	}
	
	public void addChild(ListItem child) {
		children.add(child);
		child.setParent(this);
	}
	
	public void removeChild(ListItem child) {
		children.remove(child);
		child.setParent(this);
	}
	
	public Vector<ListItem> getChildren() {
		return children;
	}

	public void setParent(ListItem parent) {
		this.parent = parent;
	}
	
	public ListItem getParent() {
		return this.parent;
	}
	
	public boolean isRoot() {
		if (this.parent == null) {
			return true;
		}
		return false;
	}
	
	public boolean isLeaf() {
		if (this.children.size() == 0) {
			return true;
		}
		return false;
	}
	
	public ListItem getContent() {
		return this;
	}

}
