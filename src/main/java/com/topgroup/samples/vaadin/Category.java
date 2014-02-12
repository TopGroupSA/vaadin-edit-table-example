package com.topgroup.samples.vaadin;

import java.io.Serializable;

public class Category implements Serializable {

	public static final String ID = "id";

	public static final String NAME = "name";

	private Integer id;

	private String name;

	public Category() {
	}

	public Category(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
