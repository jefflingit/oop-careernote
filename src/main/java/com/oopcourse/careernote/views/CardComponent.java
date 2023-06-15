package com.oopcourse.careernote.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

public class CardComponent extends Div {

    private Div cardContent;

    public CardComponent(String title,String subtitle,String content, String badge1, String badge2, Long daysLeft) {

        this.addClassName("card");

        cardContent = new Div();
        cardContent.addClassName("card-content");

        Span titleSpan = new Span(title);
        titleSpan.addClassName("card-title");

        Span subtitleSpan = new Span(subtitle);
        subtitleSpan.addClassName("card-subtitle");

        Span badge1Span = new Span(badge1);
        badge1Span.addClassName("card-badge");

        Span badge2Span = new Span(badge2);
        badge2Span.addClassName("card-badge");

        Span daysLeftSpan = new Span(daysLeft.toString());
        daysLeftSpan.addClassName("card-days-left");

        cardContent.add(titleSpan,subtitleSpan,badge1Span,daysLeftSpan);
        getElement().appendChild(cardContent.getElement());




    }

}
