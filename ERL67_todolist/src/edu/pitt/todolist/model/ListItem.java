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

	public ListItem(String description, int id, Timestamp timestamp) {
		this.description = description;
		this.id = id;
		this.timestamp = timestamp;
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
