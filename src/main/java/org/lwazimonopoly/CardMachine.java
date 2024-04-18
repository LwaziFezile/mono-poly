package org.lwazimonopoly;

import java.util.HashMap;
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

    }

    public Player[] getPlayers() {
        return players;
    }
    public void collect200(Player targetPlayer){
        targetPlayer.setPlayerCredit(200);
    }

    public void playerBuyProperty(Player targetPlayer, HashMap<Integer, Property> monopolyGameBoard, CardMachine cardMachine) {
        int currentPosition = targetPlayer.getPlayerPosition();
        Property landedProperty = monopolyGameBoard.get(currentPosition);
        int propertyPrice = landedProperty.getPurchaseValue();
        if (currentPosition > 0 && landedProperty.isPropertyOwnerNull()) {
            System.out.println(landedProperty.getPropertyName() + " is available to purchase for $" + propertyPrice);
            System.out.println("Press [1] to purchase this property or [0] to end your turn");
            int playerDicePrompt = Main.scanner.nextInt();

            while (playerDicePrompt != 1 && playerDicePrompt != 0) {
                System.out.println("Press [1] to purchase this property or [0] to end your turn");
                playerDicePrompt = Main.scanner.nextInt();
            }
            if (playerDicePrompt == 1) {
                System.out.println(
                        "You Have Purchased " + landedProperty.getPropertyName()
                                + ". Starting Rent Level: " + landedProperty.getCurrentRentLevel()
                );
                landedProperty.setPropertyOwner(targetPlayer);
                targetPlayer.setPlayerProperties(landedProperty);
                targetPlayer.deductPlayerCredit(propertyPrice);
                for (Player player : cardMachine.getPlayers()) {
                    System.out.println(player.getPlayerName() + " - " + "$" + player.getPlayerCredit());
                }
            } else if (playerDicePrompt == 0) {
                System.out.println(targetPlayer.getPlayerName() + " ended their turn.");
            }

        }
    }
}
