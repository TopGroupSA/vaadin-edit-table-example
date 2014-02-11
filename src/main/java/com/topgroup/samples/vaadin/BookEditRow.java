package com.topgroup.samples.vaadin;

import com.topgroup.commons.vaadin.view.table.edit.TableEditRowBean;

public class BookEditRow extends TableEditRowBean {

	public static final String ISBN = "isbn";

	public static final String TITLE = "title";

	private String isbn;

	private String title;

	public BookEditRow() {
		super();
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	protected Object getRowId() {
		return isbn;
	}

	public static class Builder {

		private BookEditRow row;

		public Builder(String isbn, String title) {
			row = new BookEditRow();
			row.setIsbn(isbn);
			row.setTitle(title);
		}

		public BookEditRow build() {
			return row;
		}
	}

}
