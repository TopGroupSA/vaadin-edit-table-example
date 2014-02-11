vaadin-edit-table-example
=========================

Example of a VAADIN editable table using commons-vaadin library class EditableTableDecorator. This helper classes are available in commons-vaadin 1.0.17 or later.

Download
--------

Grab via Maven:
```xml
<dependency>
	<groupId>topgroup.commons</groupId>
	<artifactId>commons-vaadin</artifactId>
	<version>1.0.17</version>
</dependency>
```

Usage
-----

```java


import com.topgroup.commons.vaadin.view.table.edit.EditRowTableFieldFactory;
import com.topgroup.commons.vaadin.view.table.edit.EditableTableDecorator;
// other imports

class BooksPanel extends Panel {
  
  public void init() {
    	// init code here
    	table = new Table();
    	EditableTableDecorator<BookEditRow> tableDecorator = new EditableTableDecorator<BookEditRow>(table, BookEditRow.class);
		table.setTableFieldFactory(new EditRowTableFieldFactory(table) {

			@Override
			protected void focus(Object propertyId, Field field) {
				if (BookEditRow.ISBN.equals(propertyId)) {
					field.focus();
				}
			}

		});
		// register the handlers
		tableDecorator.addActionHandler(window);
		addComponent(tableDecorator.getTableLayout());
		// populate table data
		table.focus();
		table.setValue(table.firstItemId());
  }
}
```

Run the application
-------------------
To run the sample application change into the project directory and type:

    mvn install jetty:run
