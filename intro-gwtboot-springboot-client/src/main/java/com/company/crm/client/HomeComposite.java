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

    PersonClientFactory personClientFactory;

    @Inject
    public HomeComposite(TextBox nameTextBox, DateBox birthdateDateBox,
            @Named("personListGroup") ListGroup<PersonDto> personListGroup,
            @Named("donePersonListGroup") ListGroup<PersonDto> donePersonListGroup,
            PersonRenderer personItemRenderer,
            Button addButton, Layout layout, PersonClientFactory personClientFactory) {
        logger.info("Create HomeComposite");

        this.nameTextBox = nameTextBox;
        this.birthdateDateBox = birthdateDateBox;
        this.personListGroup = personListGroup;
        this.donePersonListGroup = donePersonListGroup;
        this.addButton = addButton;
        this.layout = layout;
        this.personClientFactory = personClientFactory;

        // Add checkOk and listener
        personItemRenderer.setOnCheckHandler(this::handleCheckOkClick);
        this.personListGroup.setItemRenderer(personItemRenderer);

        logger.info("Button: " + addButton.toString());

        // Add button and listener
        this.addButton.addClickListener(addButtonClickEvent -> {
            handleAddButtonClick();
        });
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

            // Get data from server
            getPersons();
        }
    }

    void getPersons() {
        personClientFactory.getPersons().onSuccess(response -> {
            response.forEach(person -> logger
                    .info("Person: " + person.getName() + " - Date: " + person.getDate() + " - Type: " + person.getPersonType()));
        }).onFailed(failedResponse -> {
            logger.info("Error: " + failedResponse.getStatusCode() + "\nMessages: " + failedResponse.getStatusText());
        }).send();
    }

    void handleCheckOkClick(PersonDto person) {
        personListGroup.removeItem(person);
        donePersonListGroup.addItem(person);
    }

}