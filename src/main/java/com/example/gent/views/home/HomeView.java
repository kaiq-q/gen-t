package com.example.gent.views.home;

import com.example.gent.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Home")
@Route(value = "Home", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@Uses(Icon.class)
public class HomeView extends Composite<VerticalLayout> {

    public HomeView() {
        Hr hr = new Hr();
        H1 h1 = new H1();
        Paragraph text = new Paragraph();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        //avatarItem.setWidth("min-content");
        //setAvatarItemSampleData(avatarItem);
        h1.setText("Gent T");
        text.setText("We are working hard in this project..."+
                     "More contents soon!");

        h1.setWidth("max-content");
        //getContent().add(avatarItem);

        getContent().add(hr);
        getContent().add(h1);
        getContent().add(text);

    }

    /*private void setAvatarItemSampleData(AvatarItem avatarItem) {
        avatarItem.setHeading("Aria Bailey");
        avatarItem.setDescription("Endocrinologist");
        avatarItem.setAvatar(new Avatar("Aria Bailey"));
    }*/
}
