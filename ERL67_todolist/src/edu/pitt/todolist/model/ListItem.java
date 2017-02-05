package edu.pitt.todolist.model;

import java.sql.Timestamp;

/**
 * Class ListItem / defines ListItems
 * @author ERL67
 * created: 19JAN17
 */

public class ListItem {

	private String description;
	private int id;
	private  Timestamp timestamp;

	/**
	 * @param description
	 * @param id
	 * @param timestamp
	 * This constructor will cause errors with the database, because we need to wait for the DB to assign the ID based on the DBengine
	 * Only use for tasks already in the DB, otherwise use the one below, and setID() based on what row the DB assigns
	 */
	public ListItem(String description, int id, Timestamp timestamp) {
		this.description = description;
		this.id = id;
		this.timestamp = timestamp;
	}

	/**
	 * @param description
	 * @param timestamp
	 */
	public ListItem(String description, Timestamp timestamp) {
		this.description = description;
		this.timestamp = timestamp;
	}
//	
//	public ListItem(String description) {
//		this.description = description;
//	}

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

}
