package com.company.crm.client;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.datepicker.DateBox;
import org.dominokit.domino.ui.forms.TextBox;
import org.dominokit.domino.ui.layout.Layout;
import org.dominokit.domino.ui.lists.ListGroup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.company.crm.shared.PersonDto;

@ExtendWith(MockitoExtension.class)
public class HomeCompositeTest {

    @Mock
    TextBox nameTextBox;

    @Mock
    DateBox birthdateDateBox;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    ListGroup<PersonDto> personListGroup;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    ListGroup<PersonDto> donePersonListGroup;

    @Mock
    Button addButton;

    @Mock
    Layout layout;

    @Mock
    PersonCallbackApi personCallbackApi;

    @Mock
    PersonRenderer personItemRenderer;

    @Test
    void handle_check_what_dialog_to_display() {
        HomeComposite homeComposite = new HomeComposite(nameTextBox, birthdateDateBox, personListGroup,
                donePersonListGroup, personItemRenderer, addButton, layout, personCallbackApi);

        PersonDto personDto = new PersonDto();

        when(personListGroup.getItems().size()).thenReturn(3);

        homeComposite.handleCheckOkClick(personDto);

        verify(donePersonListGroup, times(1)).addItem(personDto);
    }

}
