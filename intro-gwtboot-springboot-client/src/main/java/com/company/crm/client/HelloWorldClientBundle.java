package com.company.crm.client;

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
		@DefaultStringValue("Person List")
		String appTitle();

		@DefaultStringValue("Title")
		String title();

		@DefaultStringValue("Description")
		String description();

		@DefaultStringValue("Add")
		String add();

		@DefaultStringValue("Mark Done")
		String mark_done();

		@DefaultStringValue("New Person")
		String new_todo();

		@DefaultStringValue("Add a new person")
		String add_new_todo();

		@DefaultStringValue("Persons")
		String todo_items();

		@DefaultStringValue("Done Persons")
		String done_items();
	}

	interface HelloWorldCssResource extends CssResource {
		String addButton();

		String doneButton();
	}

	@Source("helloworld.gss")
	HelloWorldCssResource css();
}
