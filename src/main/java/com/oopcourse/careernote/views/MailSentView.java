package com.oopcourse.careernote.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.PermitAll;

@PermitAll
@Route("sendMail")
@PageTitle("mail sent successfully!")
public class MailSentView extends VerticalLayout {
    public MailSentView() {
        add("Email sent successfully!");

        // Add a button to go back to the root page
        Button backButton = new Button("Back to Root");
        backButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        backButton.addClickListener(e -> UI.getCurrent().navigate(""));
        add(backButton);



    }
}