package org.example.entities;

public class Videogame extends Game{
    protected String platform;
    protected int gameplayHours;
    protected Genre genre;


    //COSTRUTTORE CHE RICHIAMA IL SUPER (DELLA CLASSE PADRE)
    public Videogame(int id, String title, int publishYear, double price, String platform, int gameplayHours, Genre genre) {
        super(id, title, publishYear, price);
        this.platform = platform;
        this.gameplayHours = gameplayHours;
        this.genre = genre;
    }


    //TO STRING
    @Override
    public String toString() {
        return "Videogame{" +
                "platform='" + platform + '\'' +
                ", gameplayHours=" + gameplayHours +
                ", genre=" + genre +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", publishYear=" + publishYear +
                ", price=" + price +
                '}';
    }

    //GETTER E SETTER
    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public int getGameplayHours() {
        return gameplayHours;
    }

    public void setGameplayHours(int gameplayHours) {
        this.gameplayHours = gameplayHours;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
