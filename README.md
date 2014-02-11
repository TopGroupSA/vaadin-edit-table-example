vaadin-edit-table-example
=========================

Example of a VAADIN editable table using commons-vaadin library class EditableTableDecorator.

Usage:

```java
class BooksPanel extends Panel {
  
  public void init() {
    //..
    
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

To run the sample application change into the project directory and type:

    mvn install jetty:run
