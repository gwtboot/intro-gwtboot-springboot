package com.company.crm.client;

import static com.company.crm.client.HomeClientBundle.CONSTANTS;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.datepicker.DateBox;
import org.dominokit.domino.ui.forms.TextBox;
import org.dominokit.domino.ui.layout.Layout;
import org.dominokit.domino.ui.lists.ListGroup;

import com.company.crm.shared.PersonDto;
import com.company.crm.shared.PersonException;
import com.google.gwt.i18n.client.DateTimeFormat;

@Singleton
public class HomeComposite {

	private static Logger logger = Logger.getLogger(HomeComposite.class.getName());

	TextBox nameTextBox;

	DateBox birthdateDateBox;

	ListGroup<PersonDto> personListGroup;

	ListGroup<PersonDto> donePersonListGroup;

	Button addButton;

	Layout layout;

	PersonCallbackApi personCallbackApi;

	@Inject
	public HomeComposite(TextBox nameTextBox, DateBox birthdateDateBox,
			@Named("personListGroup") ListGroup<PersonDto> personListGroup,
			@Named("donePersonListGroup") ListGroup<PersonDto> donePersonListGroup, PersonRenderer personItemRenderer,
			Button addButton, Layout layout, PersonCallbackApi personCallbackApi) {
		logger.info("Create HomeComposite");

		this.nameTextBox = nameTextBox;
		this.birthdateDateBox = birthdateDateBox;
		this.personListGroup = personListGroup;
		this.donePersonListGroup = donePersonListGroup;
		this.addButton = addButton;
		this.layout = layout;
		this.personCallbackApi = personCallbackApi;

		personItemRenderer.setOnCheckHandler(this::handleCheckOkClick);
		this.personListGroup.setItemRenderer(personItemRenderer);

		logger.info("HomeComposite Button: " + addButton.getTextContent());

		this.addButton.addClickListener(addButtonClickEvent -> {
			handleAddButtonClick();
		});

		addPersonsListGroup();
	}

	void handleAddButtonClick() {
		if (!nameTextBox.isEmpty() && !birthdateDateBox.isEmpty()) {
			PersonDto person = new PersonDto();
			person.setName(nameTextBox.getValue());
			person.setDate(birthdateDateBox.getValue());

			String pattern = CONSTANTS.birthdateStringFormat();
			DateTimeFormat dateFormat = DateTimeFormat.getFormat(pattern);
			person.setFormattedDate(dateFormat.format(person.getDate()));

			personListGroup.addItem(person);

			nameTextBox.setValue("");
			birthdateDateBox.setValue(null);

			createPerson(person);

			addPersonsListGroup();

			getPersonsWithError();
		}
	}

	void getPersonsWithError() {
		try {
			personCallbackApi.getPersonsWithError(errorList -> {
				logger.info("Callback getPersonsWithError amount: " + errorList.size());
			});
		} catch (PersonException e) {
			logger.warning("Error: " + e.getLocalizedMessage());
		}
	}

	void createPerson(PersonDto person) {
		// Create the person on the server - Async
		personCallbackApi.createPerson(person, personCreated -> {
			logger.info("Callback: createPerson: " + personCreated.getName());
		});
	}

	void addPersonsListGroup() {
		// Get data from server - Async
		personCallbackApi.getPersons(personList -> {
			logger.info("Callback getPersons amount: " + personList.size());

			personListGroup.addItems(personList);
		});
	}

	void handleCheckOkClick(PersonDto person) {
		personListGroup.removeItem(person);
		donePersonListGroup.addItem(person);
	}

}
