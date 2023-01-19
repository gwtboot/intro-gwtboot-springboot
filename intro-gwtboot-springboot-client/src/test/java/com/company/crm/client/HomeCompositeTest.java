package com.company.crm.client;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.datepicker.DateBox;
import org.dominokit.domino.ui.forms.TextBox;
import org.dominokit.domino.ui.layout.Layout;
import org.dominokit.domino.ui.lists.ListGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
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

    HomeComposite homeComposite;

    @BeforeEach
    void setup() {
        homeComposite = new HomeComposite(nameTextBox, birthdateDateBox, personListGroup,
                donePersonListGroup, personItemRenderer, addButton, layout, personCallbackApi);
    }

    @Test
    void handle_check_what_dialog_to_display_items_3() {
        PersonDto personDto = new PersonDto();

        when(personListGroup.getItems().size()).thenReturn(3);

        homeComposite.handleCheckOkClick(personDto);

        verify(donePersonListGroup, times(1)).addItem(personDto);
    }

    @Test
    void handle_check_what_dialog_to_display_items_2() {
        HomeComposite homeCompositeSpy = Mockito.spy(homeComposite);

        PersonDto personDto = new PersonDto();

        when(personListGroup.getItems().size()).thenReturn(2);
        doNothing().when(homeCompositeSpy).createWarningDialog();

        homeCompositeSpy.handleCheckOkClick(personDto);

        verify(homeCompositeSpy, times(1)).createWarningDialog();
        verify(donePersonListGroup, times(1)).addItem(personDto);
    }

    @Test
    void handle_check_what_dialog_to_display_items_0() {
        HomeComposite homeCompositeSpy = Mockito.spy(homeComposite);

        PersonDto personDto = new PersonDto();

        when(personListGroup.getItems().size()).thenReturn(0);
        doNothing().when(homeCompositeSpy).createErrorDialog(anyString());

        homeCompositeSpy.handleCheckOkClick(personDto);

        verify(homeCompositeSpy, times(1)).createErrorDialog(anyString());
        verify(donePersonListGroup, times(1)).addItem(personDto);
    }

    @Test
    void handle_add_button_click_with_error_dialog_less_3_chars() {
        HomeComposite homeCompositeSpy = Mockito.spy(homeComposite);

        when(nameTextBox.isEmpty()).thenReturn(false);
        when(nameTextBox.getValue()).thenReturn("Lof");
        when(birthdateDateBox.isEmpty()).thenReturn(false);

        doNothing().when(homeCompositeSpy).createErrorDialog(anyString());

        homeCompositeSpy.handleAddButtonClick();

        verify(homeCompositeSpy, times(1)).createErrorDialog(anyString());
    }

}
