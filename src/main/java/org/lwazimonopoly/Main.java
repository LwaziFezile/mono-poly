package org.lwazimonopoly;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Enter No. of players in the game below [No. of players 2-4]: ");

        int noOfPlayers = scanner.nextInt();
        while (noOfPlayers > 4 || noOfPlayers < 2){
            System.out.println("Monopoly can have at min. 2 Players, and at max. 4 Players, Try Again: ");
            noOfPlayers = scanner.nextInt();
        }

        CardMachine cardMachine = new CardMachine(noOfPlayers);

        boolean playerBankrupt = false;
        boolean playersCompletedCircuit = false;
        int numberOfPlayers = 0;

        MonopolyBoard gameBoard = new MonopolyBoard();
        Player[] players = cardMachine.getPlayers();
        // Boolean to telling us if the deck has been shuffled, bankingEventCards is an arraylist of the shuffled deck
        boolean bankingEventCardsShuffled = gameBoard.shuffleDeck;
        ArrayList <String> bankingEventCards = gameBoard.deck;
        HashMap<Integer, Property> board = new HashMap<>();

        Property [] propertyList = gameBoard.propertiesList;
        // Creating an Array of Properties
        for (int i = 0; i < propertyList.length; i++) {
            board.put(i, propertyList[i]);
        }
        Integer[] boardCells = board.keySet().toArray(new Integer[0]);
        Arrays.sort(boardCells);

        System.out.println("Monopoly Board Initialized");
        System.out.println("Starting credit for every player");
        for (Player player : cardMachine.getPlayers()){
            System.out.println(player.getPlayerName() + " - " + "$" + player.getPlayerCredit());
        }

        completeInitialCircuit(playersCompletedCircuit, players, numberOfPlayers, board, cardMachine);
        System.out.println("ALL PLAYERS COMPLETED INITIAL CIRCUIT");
        while (!playerBankrupt){
            for (Player player : players){
                System.out.println(
                        player.getPlayerName() +
                        "\'s Turn. Press [1] to Roll Dice, Press [2] to view credit for all players"
                );
                int playerDicePrompt = scanner.nextInt();
                while (playerDicePrompt != 1 ){
                    System.out.println("Wrong input, Press [1] to Roll Dice:");
                    playerDicePrompt = scanner.nextInt();
                }

            }
        }
        }
    public static void completeInitialCircuit(
            boolean playersCompCircuit, Player[] gamePlayers,
            int playerNumber, HashMap<Integer, Property> monopolyGameBoard, CardMachine machine){
        int helperIndex = 0;
        while(!playersCompCircuit){
            for (Player player : gamePlayers){

                System.out.println(player.getPlayerName() + "\'s turn. "+ "Press [1] to Roll Dice");
                int playerDicePrompt = scanner.nextInt();
                while (playerDicePrompt != 1){
                    System.out.println("Wrong input, Press [1] to Roll Dice:");
                    playerDicePrompt = scanner.nextInt();
                }
                int[] playerRolledDice = player.rollDice();
                playerNumber = rollDiceHelper(player, playerNumber, playerRolledDice, monopolyGameBoard, machine);

                if (playerRolledDice[0] == playerRolledDice[1]){
                    System.out.println(player.getPlayerName() + " rolled a double, "+ "Press [1] to Roll Dice:");
                    playerDicePrompt = scanner.nextInt();

                    while (playerDicePrompt != 1){
                        System.out.println("Wrong input, Press [1] to Roll Dice:");
                        playerDicePrompt = scanner.nextInt();
                    }
                    playerRolledDice = player.rollDice();
                    playerNumber = rollDiceHelper(player, playerNumber, playerRolledDice, monopolyGameBoard, machine);
                    if (playerRolledDice[0] == playerRolledDice[1]){
                        System.out.println(
                                player.getPlayerName() + " rolled a double, next double rolled, ends " +
                                player.getPlayerName()+ "\'s turn. Press [1] to Roll Dice:");

                        playerDicePrompt = scanner.nextInt();

                        while (playerDicePrompt != 1){
                            System.out.println("Wrong input, Press [1] to Roll Dice:");
                            playerDicePrompt = scanner.nextInt();
                        }

                        playerRolledDice = player.rollDice();
                        if (playerRolledDice[0] == playerRolledDice[1]){
                            System.out.println(
                                player.getPlayerName() + " rolled a double 3 times, "+
                                player.getPlayerName() + "\'s turn is over."
                            );
                        }else {
                            playerNumber = rollDiceHelper(player, playerNumber, playerRolledDice, monopolyGameBoard, machine);
                        }
                    }
                }

                if (playerNumber == gamePlayers.length){
                    playersCompCircuit = true;
                }
                System.out.println(
                        "Number of Players Completed Initial Circuit: " + playerNumber + ". playersCompCircuit = " +
                        playersCompCircuit
                );
                if (helperIndex == gamePlayers.length - 1){
                    helperIndex = 0;
                }
                else{
                    helperIndex += 1;
                }
            }
        }
    }
    public static int rollDiceHelper(
            Player currentPlayer, int playersInGame,
            int[] currentRolledDice, HashMap<Integer,
            Property> monopolyGameBoard, CardMachine machine){
        /* Rolls the dice for the player, and updates the player's position
         * in the board*/
        int rolledDiceSum = currentRolledDice[0] + currentRolledDice[1];

        int currentPosition = currentPlayer.getPlayerPosition();
        System.out.println(
                "You Rolled: " + currentRolledDice[0] + " and " + currentRolledDice[1] +
                        " Current Position: " + currentPosition
        );

        int newPosition = (currentPosition + rolledDiceSum);

        System.out.println(currentPlayer.getPlayerName() + " Rolled a " + rolledDiceSum);
        if (newPosition > 35 && !currentPlayer.isPlayerBuyProperty()){
            currentPlayer.setPlayerBuyProperty(true);
            System.out.println(currentPlayer.getPlayerName() + " Collected $200");
            currentPlayer.setPlayerCredit(200);
            currentPlayer.setPlayerPosition(newPosition - 36);
            playersInGame += 1;
            playerBuyProperty(currentPlayer, monopolyGameBoard, machine);
            System.out.println(currentPlayer.getPlayerName() + " COMPLETED CIRCUIT.");
        }
        else if (newPosition <= 35 && currentPlayer.isPlayerBuyProperty()) {
            System.out.println(currentPlayer.getPlayerName() + " CAN NOW PURCHASE PROPERTY.");
            currentPlayer.setPlayerPosition(newPosition);
            playerBuyProperty(currentPlayer, monopolyGameBoard, machine);
        }
        else if (newPosition > 35 && currentPlayer.isPlayerBuyProperty()) {
            System.out.println(currentPlayer.getPlayerName() + " Collected $200");
            currentPlayer.setPlayerCredit(200);
            currentPlayer.setPlayerPosition(newPosition - 36);
            playerBuyProperty(currentPlayer, monopolyGameBoard, machine);
        }
        else{
            currentPlayer.setPlayerPosition(newPosition);
        }
        System.out.println(currentPlayer.getPlayerName() + " is in position: " + currentPlayer.getPlayerPosition());

        return playersInGame;

    }
    public static void playerBuyProperty(Player targetPlayer, HashMap <Integer, Property> monopolyGameBoard, CardMachine cardMachine){
        int currentPosition = targetPlayer.getPlayerPosition();
        Property landedProperty = monopolyGameBoard.get(currentPosition);
        int propertyPrice = landedProperty.getPurchaseValue();
        if (currentPosition > 0 && landedProperty.isPropertyOwnerNull()){
            System.out.println(landedProperty.getPropertyName() + " is available to purchase for $" + propertyPrice);
            System.out.println("Press [1] to purchase this property or [0] to end your turn");
            int playerDicePrompt = scanner.nextInt();

            while (playerDicePrompt != 1 && playerDicePrompt != 0){
                System.out.println("Press [1] to purchase this property or [0] to end your turn");
                playerDicePrompt = scanner.nextInt();
            }
            if (playerDicePrompt == 1){
                System.out.println(
                        "You Have Purchased " + landedProperty.getPropertyName()
                        + ". Starting Rent Level: " + landedProperty.getCurrentRentLevel()
                );
                landedProperty.setPropertyOwner(targetPlayer);
                targetPlayer.setPlayerProperties(landedProperty);
                targetPlayer.deductPlayerCredit(propertyPrice);
                for (Player player : cardMachine.getPlayers()){
                    System.out.println(player.getPlayerName() + " - " + "$" + player.getPlayerCredit());
                }
            } else if (playerDicePrompt == 0) {
                System.out.println(targetPlayer.getPlayerName() + " ended their turn.");
            }

        }
    }
}