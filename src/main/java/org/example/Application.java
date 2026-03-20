package org.example;

import org.example.entities.BoardGame;
import org.example.entities.Collection;
import org.example.entities.Game;
import org.example.entities.Genre;
import org.example.entities.Videogame;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Collection collection = new Collection();
        boolean running = true;

        //region
        //Inizializzo leggermente l'array con qualcosa da avere dentro
        collection.addGame(new Videogame(101, "Elden Ring", 2022, 59.99, "PC", 120, Genre.RPG));
        collection.addGame(new Videogame(102, "Hades", 2020, 24.50, "Switch", 50, Genre.ACTION));
        collection.addGame(new Videogame(103, "FIFA 24", 2023, 69.90, "PS5", 500, Genre.SPORTS));
        collection.addGame(new Videogame(104, "Hollow Knight", 2017, 14.99, "PC", 40, Genre.ADVENTURE));
        collection.addGame(new Videogame(105, "Civilization VI", 2016, 29.99, "PC", 300, Genre.STRATEGY));

        collection.addGame(new BoardGame(201, "Catan", 1995, 42.00, 4, 90));
        collection.addGame(new BoardGame(202, "Dixit", 2008, 29.90, 6, 30));
        collection.addGame(new BoardGame(203, "Pandemic", 2008, 35.00, 4, 45));
        collection.addGame(new BoardGame(204, "Ticket to Ride", 2004, 44.99, 5, 60));
        collection.addGame(new BoardGame(205, "Risiko", 1957, 32.50, 6, 120));
        //endregion

        System.out.println("Benvenuto nel Gestore della tua Collezione di Giochi!");

        while (running) {
            try {
                System.out.println("\n================ MENU PRINCIPALE ================");
                System.out.println("1. Aggiungi un nuovo gioco");
                System.out.println("2. Cerca un gioco per ID");
                System.out.println("3. Ricerca giochi per budget (prezzo <=)");
                System.out.println("4. Ricerca giochi da tavolo per numero di giocatori");
                System.out.println("5. Aggiorna un gioco esistente");
                System.out.println("6. Rimuovi un gioco tramite ID");
                System.out.println("7. Stampa le statistiche della collezione");
                System.out.println("0. Esci dal programma");
                System.out.print("Fai la tua scelta: ");

                int choice = scanner.nextInt();
                //Ho scoperto che dopo un nextInt il prossimo nextLine è dato vuoto in automatico, quindi pulisco lo scanner
                scanner.nextLine();

                //Switch case per utilizzare tutte le funzioni della collection
                switch (choice) {
                    case 1:
                        addGameFromMenu(scanner, collection);
                        break;

                    case 2:
                        System.out.print("Inserisci l'ID (numerico) da cercare: ");
                        int idSearch = scanner.nextInt();
                        scanner.nextLine();
                        Game found = collection.searchGameById(idSearch);
                        if (found != null) {
                            System.out.println("Gioco trovato: " + found);
                        }
                        break;

                    case 3:
                        System.out.print("Inserisci il prezzo massimo: ");
                        double maxPrice = scanner.nextDouble();
                        scanner.nextLine();
                        List<Game> cheapGames = collection.searchGameByPrice(maxPrice);
                        if (cheapGames.isEmpty()) {
                            System.out.println("Nessun gioco trovato a " + maxPrice + "€ o meno.");
                        } else {
                            cheapGames.forEach(System.out::println);
                        }
                        break;

                    case 4:
                        System.out.print("Inserisci il numero di giocatori desiderato: ");
                        int players = scanner.nextInt();
                        scanner.nextLine();
                        List<BoardGame> boardGames = collection.searchGameByPlayerNumber(players);
                        if (boardGames.isEmpty()) {
                            System.out.println("Nessun gioco da tavolo trovato per " + players + " giocatori.");
                        } else {
                            boardGames.forEach(System.out::println);
                        }
                        break;

                    case 5:
                        System.out.print("Inserisci l'ID del gioco da aggiornare: ");
                        int idUpdate = scanner.nextInt();
                        scanner.nextLine();

                        if (collection.searchGameById(idUpdate) != null) {
                            System.out.println("Inserisci i nuovi dati per questo ID:");
                            Game updatedGame = createGameFromInput(scanner, idUpdate);
                            if (updatedGame != null) {
                                collection.updateGameById(idUpdate, updatedGame);
                            }
                        }
                        break;

                    case 6:
                        System.out.print("Inserisci l'ID del gioco da rimuovere: ");
                        int idRemove = scanner.nextInt();
                        scanner.nextLine();
                        collection.removeGameById(idRemove);
                        break;

                    case 7:
                        collection.printStatistics();
                        break;

                    case 0:
                        running = false;
                        System.out.println("Va bene per oggi chiudiamo :(");
                        break;

                    case 69:
                        System.out.println("Bravo sei molto simpatico e maturo per aver scritto questo numero");
                        break;

                    case 90:
                        System.out.println("Molto poco simpatico questo numero");
                        break;

                    case 104:
                        System.out.println("Sarebbe una buona soluzione, ci stiamo lavorando per ottenerla, servirà ancora qualche mese");

                    default:
                        System.out.println("Scelta non valida. Riprova.");
                }

            } catch (InputMismatchException e) {
                System.err.println("ERRORE DI INPUT: DEVI INSERIRE UN NUMERO!");
                scanner.nextLine();
            } catch (Exception e) {
                System.err.println("Si è verificato un errore: " + e.getMessage());
            }
        }
        scanner.close();
    }

    //Aggiungo le funzioni dello switch qui sotto per tenere il main pulito

    //Funzione per il case 1
    private static void addGameFromMenu(Scanner scanner, Collection collection) {
        System.out.print("Inserisci un nuovo ID (numerico): ");
        int id = scanner.nextInt();
        scanner.nextLine();

        //Se l'ID esiste già, non chiedo nemmeno i dati
        if (collection.getGameCollection().containsKey(id)) {
            System.err.println("Errore: Gioco con ID " + id + " già presente in collezione.");
            return;
        }

        //Richiamo la funzione presente sotto per richiedere tutti i dati in input
        Game newGame = createGameFromInput(scanner, id);
        if (newGame != null) {
            collection.addGame(newGame);
        }
    }

    //Funzione per prendere in input da tastiera tutti i dati per la greazione di un nuovo gioco
    private static Game createGameFromInput(Scanner scanner, int id) {
        System.out.println("Che tipo di gioco vuoi inserire?");
        System.out.println("1. Videogioco");
        System.out.println("2. Gioco da Tavolo");
        System.out.print("Scelta: ");
        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Titolo: ");
        String title = scanner.nextLine();

        System.out.print("Anno di pubblicazione: ");
        int year = scanner.nextInt();

        System.out.print("Prezzo: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        if (type == 1) {
            System.out.print("Piattaforma (es. PC, PS5): ");
            String platform = scanner.nextLine();

            System.out.print("Durata in ore: ");
            int duration = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Genere (es. ACTION, RPG, SPORTS): ");
            String genreInput = scanner.nextLine().toUpperCase();
            Genre genre;
            try {
                genre = Genre.valueOf(genreInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Genere non riconosciuto. Impostato su ACTION di default.");
                genre = Genre.ACTION;
            }

            return new Videogame(id, title, year, price, platform, duration, genre);

        } else if (type == 2) {
            System.out.print("Numero di giocatori: ");
            int players = scanner.nextInt();

            System.out.print("Durata media partita (minuti): ");
            int durationMins = scanner.nextInt();
            scanner.nextLine();

            return new BoardGame(id, title, year, price, players, durationMins);
        } else {
            System.err.println("Scelta tipo gioco non valida.");
            return null;
        }
    }
}