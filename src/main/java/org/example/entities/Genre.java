package org.example.entities;

public enum Genre {
    ACTION("Azione"),
    RPG("Gioco di Ruolo"),
    ADVENTURE("Avventura"),
    STRATEGY("Strategia"),
    SPORTS("Sport"),
    HORROR("Horror"),
    SIMULATION("Simulazione"),
    FPS("Sparatutto in prima persona");

    private final String displayedName;

    Genre(String displayedName) {
        this.displayedName = displayedName;
    }

    public String getDisplayedName() {
        return displayedName;
    }
}
