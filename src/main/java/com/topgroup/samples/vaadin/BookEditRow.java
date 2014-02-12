package com.topgroup.samples.vaadin;

import com.topgroup.commons.vaadin.view.table.edit.TableEditRowBean;

public class BookEditRow extends TableEditRowBean {

	public static final String ISBN = "isbn";

	public static final String TITLE = "title";

	public static final String CATEGORY = "category";

	public static final String AVAILABLE = "available";

	private String isbn;

	private String title;

	private Category category;

	private boolean available = true;

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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public static class Builder {

		private BookEditRow row;

		public Builder(String isbn, String title) {
			row = new BookEditRow();
			row.setIsbn(isbn);
			row.setTitle(title);
		}

		public Builder category(Category category) {
			row.setCategory(category);
			return this;
		}

		public Builder available(boolean available) {
			row.setAvailable(available);
			return this;
		}

		public BookEditRow build() {
			return row;
		}
	}

}
