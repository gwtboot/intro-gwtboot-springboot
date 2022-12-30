package com.company.crm.client;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.forms.TextArea;
import org.dominokit.domino.ui.forms.TextBox;
import org.dominokit.domino.ui.layout.Layout;
import org.dominokit.domino.ui.lists.ListGroup;

@Singleton
public class HomeComposite {

    private static Logger logger = Logger
            .getLogger(HomeComposite.class.getName());

    TextBox titleTextBox;

    TextArea descriptionTextArea;

    ListGroup<PersonDto> todoItemsListGroup;

    ListGroup<PersonDto> doneItemsListGroup;

    Button addButton;

    Layout layout;

    @Inject
    public HomeComposite(TextBox titleTextBox, TextArea descriptionTextArea,
                          @Named("todoItemsListGroup") ListGroup<PersonDto> todoItemsListGroup,
                          @Named("doneItemsListGroup") ListGroup<PersonDto> doneItemsListGroup,
                          PersonRenderer toDoItemRenderer,
                          Button addButton, Layout layout) {
        logger.info("Create HelloWorldView");

        this.titleTextBox = titleTextBox;
        this.descriptionTextArea = descriptionTextArea;
        this.todoItemsListGroup = todoItemsListGroup;
        this.doneItemsListGroup = doneItemsListGroup;
        this.addButton = addButton;
        this.layout = layout;

        // Add checkOk and listener
        toDoItemRenderer.setOnCheckHandler(this::handleCheckOkClick);
        this.todoItemsListGroup.setItemRenderer(toDoItemRenderer);

        logger.info("Button: " + addButton.toString());

        // Add button and listener
        this.addButton.addClickListener(addButtonClickEvent -> {
            handleAddButtonClick();
        });
    }

    void handleAddButtonClick() {
        if (!titleTextBox.isEmpty() && !descriptionTextArea.isEmpty()) {
            PersonDto todoItem = new PersonDto(titleTextBox.getValue(),
                    descriptionTextArea.getValue());

            todoItemsListGroup.addItem(todoItem);

            titleTextBox.setValue("");
            descriptionTextArea.setValue("");
        }
    }

    void handleCheckOkClick(PersonDto todoItem) {
        todoItemsListGroup.removeItem(todoItem);
        doneItemsListGroup.addItem(todoItem);
    }

}
