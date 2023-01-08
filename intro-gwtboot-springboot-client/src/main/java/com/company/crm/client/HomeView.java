package com.company.crm.client;

import static com.company.crm.client.HomeClientBundle.BUNDLE;
import static com.company.crm.client.HomeClientBundle.CONSTANTS;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.datepicker.DateBox;
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

import com.company.crm.shared.PersonDto;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeView {

	private static Logger logger = Logger.getLogger(HomeView.class.getName());

	public HomeView() {
		logger.info("Create HomeView");
	}

	@Provides
	@Singleton
	@Inject
	Layout layout(TextBox nameTextBox, DateBox birthdateDateBox,
			@Named("personListGroup") ListGroup<PersonDto> personListGroup,
			@Named("donePersonListGroup") ListGroup<PersonDto> donePersonListGroup, Button addButton) {
		Layout layout = Layout.create(CONSTANTS.appTitle()).removeLeftPanel().show(Theme.BLUE);
		layout.getContentPanel().appendChild(
				Card.create(CONSTANTS.new_todo(), CONSTANTS.add_new_todo()).appendChild(nameTextBox.element())
						.appendChild(birthdateDateBox.element()).appendChild(addButton.element()).element());
		layout.getContentPanel()
				.appendChild(Card.create(CONSTANTS.todo_items()).appendChild(personListGroup.element()).element());
		layout.getContentPanel()
				.appendChild(Card.create(CONSTANTS.done_items()).appendChild(donePersonListGroup.element()).element());

		logger.info("HomeView Button: " + addButton.getTextContent());

		return layout;
	}

	@Provides
	@Singleton
	TextBox nameTextBox() {
		return TextBox.create(CONSTANTS.name()).floating();
	}

	@Provides
	@Singleton
	DateBox birthdateDateBox() {
		return DateBox.create(CONSTANTS.birthdate()).setPattern("yyyy/MM/dd");
	}

	@Named("personListGroup")
	@Provides
	@Singleton
	ListGroup<PersonDto> personListGroup() {
		ListGroup<PersonDto> personListGroup = ListGroup.create();
		return personListGroup;
	}

	@Provides
	@Singleton
	PersonRenderer personItemRenderer() {
		return new PersonRenderer();
	}

	@Named("donePersonListGroup")
	@Provides
	@Singleton
	ListGroup<PersonDto> donePersonListGroup() {
		ListGroup<PersonDto> donePersonListGroup = ListGroup.create();

		donePersonListGroup.setItemRenderer((listGroup, listItem) -> {
			listItem.css(Styles.padding_10)
					.appendChild(
							FlexLayout
									.create().setJustifyContent(
											FlexJustifyContent.SPACE_AROUND)
									.appendChild(FlexItem.create().setFlexGrow(1)
											.appendChild(BlockHeader
													.create(listItem.getValue().getName(),
															listItem.getValue().getFormattedDate())
													.css(Styles.m_b_0))));
		});

		return donePersonListGroup;
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
