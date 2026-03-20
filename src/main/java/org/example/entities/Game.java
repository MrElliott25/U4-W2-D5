package org.example.entities;

public abstract class Game {
    protected int id;
    protected String title;
    protected int publishYear;
    protected double price;

    //COSTRUTTORE
    public Game(int id, String title, int publishYear, double price) {
        this.id = id;
        this.title = title;
        this.publishYear = publishYear;
        this.price = price;
    }

    //TO STRING
    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publishYear=" + publishYear +
                ", price=" + price +
                '}';
    }

    //GETTER E SETTER
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
