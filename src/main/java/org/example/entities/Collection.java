package org.example.entities;

import java.util.*;
import java.util.stream.Collectors;

public class Collection {

    //Scelgo di usare una Map in quanto le chiavi non si possono ripetere, quindi quando aggiungerò un gioco
    //setterò come chiave l'id del gioco stesso
    protected Map<Integer, Game> gameCollection = new HashMap<>();


    //IL MAP sul quale andremo ad interagire
    public Map<Integer, Game> getGameCollection() {
        return gameCollection;
    }

    //Aggiunta nuovo gioco
    public void addGame(Game newGame) {
        if (gameCollection.containsKey(newGame.getId())) {
            System.err.println("Errore: Un gioco con ID " + newGame.getId() + " è già presente in collezione.");
        } else {
            gameCollection.put(newGame.getId(), newGame);
            System.out.println("Aggiunto con successo: " + newGame.getTitle());
        }
    }

    //Ricerca gioco tramite ID
    public Game searchGameById(int id){
        Game searched = gameCollection.get(id);
        if(searched==null) System.err.println("NESSUN GIOCO TROVATO! ID: " + id);
        return searched;
    }

    //Ricerca per prezzo (so che chiedeva solo inferiori, ma mi sembrava più logico includere il prezzo inserito, cambia solo un operatore
    public List<Game> searchGameByPrice (double price){
        List<Game> filteredList = gameCollection.values().stream()
                .filter(game -> game.getPrice()<=price)
                .collect(Collectors.toList());
        if(filteredList == null) System.err.println("NESSUN GIOCO TROVATO CON PREZZO MINORE O UGUALE DI " + price);
        return filteredList;
    }

    //Ricerca per numero di giocatori
    public List<BoardGame> searchGameByPlayerNumber(int numberOfPlayers){
        List<BoardGame> filteredList = gameCollection.values().stream()
                .filter(game -> game instanceof BoardGame)
                .map(board -> (BoardGame) board)
                .filter(board -> board.getNumberOfPlayers()==numberOfPlayers)
                .toList();
        return filteredList;
    }

    //Rimozione di un elemento per ID
    public void removeGameById (int id) {
        if (!gameCollection.containsKey(id)) System.err.println("NESSUN GIOCO TROVATO PER L'ID " + id);
        else {
            gameCollection.remove(id);
            System.out.println("Elemento rimosso con successo!");
        }
    }

    //Aggiornamento di un elemento per ID
    public void updateGameById (int id, Game updatedGame) {
        if(gameCollection.containsKey(id)){
            gameCollection.put(id, updatedGame);
            System.out.println("Gioco aggiornato con successo!");
        }
        else System.err.println("NESSUN GIOCO TROVATO PER ID " + id);
    }

    //Stampa statistiche
    public void printStatistics(){
        int numberOfVideogames = Math.toIntExact(gameCollection.values().stream().filter(game -> game instanceof Videogame).count());
        int numberOfBoardGames = Math.toIntExact(gameCollection.values().stream().filter(game -> game instanceof BoardGame).count());

        //Gioco più costoso
        Optional<Game> mostExpensive = gameCollection.values().stream()
                .max(Comparator.comparingDouble(Game::getPrice));

        //Media prezzi
        OptionalDouble averagePrice = gameCollection.values().stream()
                .mapToDouble(Game::getPrice)
                .average();

        System.out.println("\nSTATISTICHE COLLEZIONE");
        System.out.println("Totale Videogiochi: " + numberOfVideogames);
        System.out.println("Totale Giochi da Tavolo: " + numberOfBoardGames);
        mostExpensive.ifPresent(game ->
                System.out.println("Gioco più caro: " + game.getTitle() + " col prezzo di " + game.getPrice()));
        System.out.println("Media prezzi: " +
                String.format("%.2f", averagePrice.isPresent() ? averagePrice.getAsDouble() : 0.0) + "€");
    }
}
