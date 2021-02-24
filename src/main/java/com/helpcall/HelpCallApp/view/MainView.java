package com.helpcall.HelpCallApp.view;

import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.stream.Stream;

@Route
public class MainView extends VerticalLayout {

    public MainView() {
        MenuBar menuBar = new MenuBar();
        menuBar.setOpenOnHover(true);

        Stream.of("Start", "Jak to działa?", "Mapa", "Statystyki", "Opinie",
                "Potrzebuję pomocy", "Logowanie")
                .forEach(menuBar::addItem);
        add(menuBar);

        MenuItem registration = menuBar.addItem("Rejestracja");
        registration.getSubMenu().addItem("Rejestracja dla osób potrzebujących pomocy");
        registration.getSubMenu().addItem("Rejestracja dla wolontariuszy");
        add(menuBar);
    }
}
