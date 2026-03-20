package org.example.entities;

public class BoardGame extends Game{
    protected int numberOfPlayers;
    protected int gameplayMinutes;

    public BoardGame(int id, String title, int publishYear, double price, int numberOfPlayers, int gameplayMinutes) {
        super(id, title, publishYear, price);
        this.numberOfPlayers = numberOfPlayers;
        this.gameplayMinutes = gameplayMinutes;
    }

    //TO STRING
    @Override
    public String toString() {
        return "BoardGame{" +
                "numberOfPlayers=" + numberOfPlayers +
                ", gameplayMinutes=" + gameplayMinutes +
                ", id=" + id +
                ", publishYear=" + publishYear +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }

    //GETTER E SETTER
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getGameplayMinutes() {
        return gameplayMinutes;
    }

    public void setGameplayMinutes(int gameplayMinutes) {
        this.gameplayMinutes = gameplayMinutes;
    }
}
