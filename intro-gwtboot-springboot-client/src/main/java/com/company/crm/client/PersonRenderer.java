package com.company.crm.client;

import java.util.function.Consumer;

import org.dominokit.domino.ui.grid.flex.FlexItem;
import org.dominokit.domino.ui.grid.flex.FlexJustifyContent;
import org.dominokit.domino.ui.grid.flex.FlexLayout;
import org.dominokit.domino.ui.header.BlockHeader;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.lists.ListGroup;
import org.dominokit.domino.ui.lists.ListItem;
import org.dominokit.domino.ui.style.Color;
import org.dominokit.domino.ui.style.Styles;

import com.company.crm.shared.PersonDto;

public class PersonRenderer implements ListGroup.ItemRenderer<PersonDto> {

    private Consumer<PersonDto> onCheckHandler = todoItem -> {};

    @Override
    public void onRender(ListGroup<PersonDto> listGroup, ListItem<PersonDto> listItem) {
        listItem.css(Styles.padding_10)
                .appendChild(FlexLayout.create().setJustifyContent(
                        FlexJustifyContent.SPACE_AROUND)
                        .appendChild(FlexItem.create().setFlexGrow(1)
                                .appendChild(BlockHeader.create(
                                        listItem.getValue().getName(),
                                        listItem.getValue().getDate().toString())
                                        .css(Styles.m_b_0)))
                        .appendChild(FlexItem.create()
                                .appendChild(Icons.ALL.check_bold_mdi()
                                        .setColor(Color.GREEN)
                                        .clickable()
                                        .addClickListener(
                                                addClickEvent -> onCheckHandler.accept(listItem.getValue()))
                                ))
                );
    }

    public void setOnCheckHandler(Consumer<PersonDto> onCheckHandler) {
        this.onCheckHandler = onCheckHandler;
    }
    
}
