package com.topgroup.samples.vaadin;

import java.util.ArrayList;
import java.util.List;

import com.topgroup.commons.vaadin.view.table.edit.EditRowTableFieldFactory;
import com.topgroup.commons.vaadin.view.table.edit.EditableTableDecorator;
import com.vaadin.Application;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
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

	private static final List<Category> CATEGORIES = new ArrayList<Category>();

	static {
		CATEGORIES.add(new Category(1, "Programming"));
		CATEGORIES.add(new Category(2, "Drama"));
		CATEGORIES.add(new Category(3, "Science"));
	}

	private Window window;

	private Table table;

	@Override
	public void init() {
		window = new Window("Vaadin Editable Table Example");
		setMainWindow(window);

		VerticalLayout container = new VerticalLayout();
		container.setMargin(true);
		container.setSpacing(true);
		container.setWidth("760px");
		Label label = new Label("Ejemplo de grilla editable usando EditableTableDecorator de commons-vaadin");
		container.addComponent(label);

		table = new Table() {

			@Override
			protected String formatPropertyValue(Object rowId, Object colId, Property property) {
				if (BookEditRow.AVAILABLE.equals(colId)) {
					return ((BookEditRow) rowId).isAvailable() ? "Yes" : "No";
				}
				return super.formatPropertyValue(rowId, colId, property);
			}

		};
		table.setSizeFull();
		table.setColumnWidth(BookEditRow.CATEGORY, 200);
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
			protected Field buildField(Container container, Object itemId, Object propertyId, Component uiContext) {
				Field field = null;
				if (BookEditRow.CATEGORY.equals(propertyId)) {
					field = buildCategoriesComboBox();
				} else if (BookEditRow.AVAILABLE.equals(propertyId)) {
					CheckBox checkBox = new CheckBox();
					checkBox.setImmediate(true);
					field = checkBox;
				} else {
					field = super.buildField(container, itemId, propertyId, uiContext);
				}
				return field;
			}

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
		books.add(new BookEditRow.Builder("0321356683", "Effective Java (2nd Edition)").category(CATEGORIES.get(2)).available(true).build());
		books.add(new BookEditRow.Builder("978-0596007126", "Head First Design Patterns").category(CATEGORIES.get(0)).available(true).build());
		books.add(new BookEditRow.Builder("0321349601", "Java Concurrency in Practice").category(CATEGORIES.get(1)).available(false).build());
		table.setContainerDataSource(new BeanItemContainer<BookEditRow>(BookEditRow.class, books));
		table.setVisibleColumns(new String[] { BookEditRow.ISBN, BookEditRow.TITLE, BookEditRow.CATEGORY, BookEditRow.AVAILABLE });
	}

	private ComboBox buildCategoriesComboBox() {
		ComboBox comboBox = new ComboBox();
		comboBox.setImmediate(true);
		comboBox.setNullSelectionAllowed(false);
		comboBox.setRequired(true);
		comboBox.setItemCaptionPropertyId(Category.NAME);
		comboBox.setContainerDataSource(new BeanItemContainer<Category>(Category.class, CATEGORIES));
		return comboBox;
	}

}
