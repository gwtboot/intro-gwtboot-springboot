package com.company.crm.client.ui;

import javax.inject.Singleton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

@Singleton
public interface HelloWorldClientBundle extends ClientBundle {

	HelloWorldClientBundle BUNDLE = GWT.create(HelloWorldClientBundle.class);

	HelloWorldConstants CONSTANTS = GWT.create(HelloWorldConstants.class);

	interface HelloWorldConstants extends Constants {
		@DefaultStringValue("Todo List")
		String appTitle();

		@DefaultStringValue("Title")
		String title();

		@DefaultStringValue("Description")
		String description();

		@DefaultStringValue("Add")
		String add();

		@DefaultStringValue("Mark Done")
		String mark_done();

		@DefaultStringValue("New Todo")
		String new_todo();

		@DefaultStringValue("Add a new todo list item")
		String add_new_todo();

		@DefaultStringValue("Todo Items")
		String todo_items();

		@DefaultStringValue("Done Items")
		String done_items();
	}

	interface HelloWorldCssResource extends CssResource {
		String addButton();

		String doneButton();
	}

	@Source("helloworld.gss")
	HelloWorldCssResource css();
}
