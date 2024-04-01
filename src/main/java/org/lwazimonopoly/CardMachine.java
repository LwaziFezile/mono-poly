package org.lwazimonopoly;

import java.util.Scanner;

public class CardMachine {
//TODO: Card Machine potential game master(?) Probably maintains the game state and
//TODO: values?
    private Player player1, player2, player3, player4 = null;
    private Player[] players;
    public CardMachine(int numberOfPlayers){
        players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            int index = i + 1;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Player " + index + " Name: ");
            String name = scanner.nextLine();
            if (player1 == null){
                this.player1 = new Player(name);
                players[i] = player1;
            } else if (player2 == null) {
                this.player2 = new Player(name);
                players[i] = player2;
            } else if (player3 == null) {
                this.player3 = new Player(name);
                players[i] = player3;
            } else if (player4 == null) {
                this.player4 = new Player(name);
                players[i] = player4;
            }
        }
        for (Player player : players){
            System.out.println(player.getPlayerName());
        }
    }
}
