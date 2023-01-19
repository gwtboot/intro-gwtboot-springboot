package com.company.crm.client;

import static com.company.crm.client.HomeClientBundle.CONSTANTS;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.datepicker.DateBox;
import org.dominokit.domino.ui.dialogs.MessageDialog;
import org.dominokit.domino.ui.forms.TextBox;
import org.dominokit.domino.ui.layout.Layout;
import org.dominokit.domino.ui.lists.ListGroup;
import org.dominokit.domino.ui.notifications.Notification;
import org.dominokit.domino.ui.style.Color;

import com.company.crm.shared.FieldVerifier;
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
			boolean validName = FieldVerifier.isValidName(nameTextBox.getValue());

			if (validName) {
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
			} else {
				createErrorDialog("The name is not valid!");
			}
		}
	}

	void createErrorDialog(String title) {
		MessageDialog customColors = MessageDialog.createMessage(
				title, "Oh snap! Change a few things up and try submitting again.",
				() -> Notification.create("Dialog closed").show()).error().setModalColor(Color.RED)
				.setIconColor(Color.GREY, Color.WHITE);

		customColors.open();
	}

	void createWarningDialog() {
		MessageDialog warningMessage = MessageDialog.createMessage(
				"Warning",
				"Warning! The list is almost empty.",
				() -> Notification.create("Dialog closed").show())
				.warning();

		warningMessage.open();
	}

	void getPersonsWithError() {
		try {
			personCallbackApi.getPersonsWithError(errorList -> {
				logger.info("Callback getPersonsWithError amount: " + errorList.size());
				createErrorDialog("Error on callback getPersonsWithError");
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

		int size = personListGroup.getItems().size();
		if (size == 2) {
			createWarningDialog();
		} else if (size == 0) {
			createErrorDialog("Error on personListGroup is empty!");
		}

		donePersonListGroup.addItem(person);
	}

}
