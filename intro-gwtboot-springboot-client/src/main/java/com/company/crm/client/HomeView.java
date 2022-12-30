package com.company.crm.client;

import static com.company.crm.client.HomeClientBundle.BUNDLE;
import static com.company.crm.client.HomeClientBundle.CONSTANTS;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.forms.TextArea;
import org.dominokit.domino.ui.forms.TextBox;
import org.dominokit.domino.ui.grid.flex.FlexItem;
import org.dominokit.domino.ui.grid.flex.FlexJustifyContent;
import org.dominokit.domino.ui.grid.flex.FlexLayout;
import org.dominokit.domino.ui.header.BlockHeader;
import org.dominokit.domino.ui.layout.Layout;
import org.dominokit.domino.ui.lists.ListGroup;
import org.dominokit.domino.ui.popover.Tooltip;
import org.dominokit.domino.ui.style.Styles;
import org.dominokit.domino.ui.themes.Theme;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeView {

	private static Logger logger = Logger
			.getLogger(HomeView.class.getName());

	public HomeView() {
		logger.info("Create HomeView");
	}

	@Provides
	@Singleton
	@Inject
	Layout layout(TextBox titleTextBox, TextArea descriptionTextArea,
			@Named("todoItemsListGroup") ListGroup<PersonDto> todoItemsListGroup,
			@Named("doneItemsListGroup") ListGroup<PersonDto> doneItemsListGroup,
			Button addButton) {
		Layout layout = Layout.create(CONSTANTS.appTitle()).removeLeftPanel()
				.show(Theme.BLUE);
		layout.getContentPanel().appendChild(
				Card.create(CONSTANTS.new_todo(), CONSTANTS.add_new_todo())
						.appendChild(titleTextBox.element())
						.appendChild(descriptionTextArea.element())
						.appendChild(addButton.element()).element());
		layout.getContentPanel().appendChild(Card.create(CONSTANTS.todo_items())
				.appendChild(todoItemsListGroup.element()).element());
		layout.getContentPanel().appendChild(Card.create(CONSTANTS.done_items())
				.appendChild(doneItemsListGroup.element()).element());

		logger.info("Button: " + addButton.toString());

		return layout;
	}

	@Provides
	@Singleton
	TextBox titleTextBox() {
		return TextBox.create(CONSTANTS.title()).floating();
	}

	@Provides
	@Singleton
	TextArea descriptionTextArea() {
		return TextArea.create(CONSTANTS.description()).floating().setRows(1);
	}

	@Named("todoItemsListGroup")
	@Provides
	@Singleton
	ListGroup<PersonDto> todoItemsListGroup() {
		ListGroup<PersonDto> todoItemsListGroup = ListGroup.create();
		return todoItemsListGroup;
	}

	@Provides
	@Singleton
	PersonRenderer todoItemRenderer() {
		return new PersonRenderer();
	}

	@Named("doneItemsListGroup")
	@Provides
	@Singleton
	ListGroup<PersonDto> doneItemsListGroup() {
		ListGroup<PersonDto> doneItemsListGroup = ListGroup.create();

		doneItemsListGroup.setItemRenderer((listGroup, listItem) -> {
			listItem.css(Styles.padding_10).appendChild(
				FlexLayout.create().setJustifyContent(FlexJustifyContent.SPACE_AROUND)
						.appendChild(FlexItem.create().setFlexGrow(1)
						.appendChild(BlockHeader.create(
								listItem.getValue().getTitle(),
								listItem.getValue().getDescription())
								.css(Styles.m_b_0))));
		});

		return doneItemsListGroup;
	}

	@Provides
	@Singleton
	Button addButton() {
		Button addButton = Button.createPrimary(CONSTANTS.add());
		addButton.element().classList.add(BUNDLE.css().addButton());
		tooltip(addButton);
		return addButton;
	}
	
	Tooltip tooltip(Button addButton) {
        return Tooltip.create(addButton.element(), CONSTANTS.add());
    }

}
