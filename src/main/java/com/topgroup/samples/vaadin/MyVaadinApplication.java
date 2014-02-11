/*
 * Copyright 2009 IT Mill Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.topgroup.samples.vaadin;

import java.util.ArrayList;
import java.util.List;

import com.topgroup.commons.vaadin.view.table.edit.EditRowTableFieldFactory;
import com.topgroup.commons.vaadin.view.table.edit.EditableTableDecorator;
import com.vaadin.Application;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class MyVaadinApplication extends Application {

	private Window window;

	private Table table;

	@Override
	public void init() {
		window = new Window("Vaadin Editable Table Example");
		setMainWindow(window);

		VerticalLayout container = new VerticalLayout();
		container.setMargin(true);
		container.setSpacing(true);
		container.setWidth("500px");
		Label label = new Label("Ejemplo de grilla editable usando EditableTableDecorator de commons-vaadin");
		container.addComponent(label);

		table = new Table();
		table.setSizeFull();
		EditableTableDecorator<BookEditRow> tableDecorator = new EditableTableDecorator<BookEditRow>(table, BookEditRow.class) {

			protected String getAddImageResourceId() {
				return "images/add.png";
			}

			protected String getEditImageResourceId() {
				return "images/edit.png";
			}

			protected String getDeleteImageResourceId() {
				return "images/delete.png";
			}

		};
		table.setTableFieldFactory(new EditRowTableFieldFactory(table) {

			@Override
			protected void focus(Object propertyId, Field field) {
				if (BookEditRow.ISBN.equals(propertyId)) {
					field.focus();
				}
			}

		});
		tableDecorator.addActionHandler(window);
		container.addComponent(tableDecorator.getTableLayout());
		initTable();
		table.focus();
		table.setValue(table.firstItemId());
		window.setContent(container);
	}

	private void initTable() {
		List<BookEditRow> books = new ArrayList<BookEditRow>();
		books.add(new BookEditRow.Builder("0321356683", "Effective Java (2nd Edition)").build());
		books.add(new BookEditRow.Builder("978-0596007126", "Head First Design Patterns").build());
		books.add(new BookEditRow.Builder("0321349601", "Java Concurrency in Practice").build());
		table.setContainerDataSource(new BeanItemContainer<BookEditRow>(BookEditRow.class, books));
		table.setVisibleColumns(new String[] { BookEditRow.ISBN, BookEditRow.TITLE });
	}

}
