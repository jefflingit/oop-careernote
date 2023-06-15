package com.oopcourse.careernote.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PermitAll
@Route(value="card-view",layout= MainLayout.class)
public class CardView extends VerticalLayout {

    public CardView(){
        setSpacing(true);
        CardComponent cardComponent1 = new CardComponent("Card1","Subtitle1","Card content 1", "Badge 1", "Badge 2", 3L);
        CardComponent cardComponent2 = new CardComponent("Card1","Subtitle1","Card content 1", "Badge 1", "Badge 2", 6L);

        add(cardComponent1,cardComponent2);
    }
}
