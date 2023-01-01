package com.company.crm.client;

import javax.inject.Singleton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

@Singleton
public interface HomeClientBundle extends ClientBundle {

	HomeClientBundle BUNDLE = GWT.create(HomeClientBundle.class);

	HomeConstants CONSTANTS = GWT.create(HomeConstants.class);

	interface HomeConstants extends Constants {

		@DefaultStringValue("Person List")
		String appTitle();

		@DefaultStringValue("Name")
		String name();

		@DefaultStringValue("Birthdate")
		String birthdate();

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

		@DefaultStringValue("yyyy/MM/dd")
		String birthdateStringFormat();
	}

	interface HomeCssResource extends CssResource {

		String addButton();

		String doneButton();
	}

	@Source("home.gss")
	HomeCssResource css();
}
