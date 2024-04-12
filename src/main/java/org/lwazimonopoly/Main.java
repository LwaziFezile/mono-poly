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
        boolean bankingEventCardsShuffled = gameBoard.shuffleDeck;
        ArrayList <String> bankingEventCards = gameBoard.deck;
        HashMap<Integer, Property> board = new HashMap<>();

        Property [] propertyList = new Property[]{
                new Property(),
                gameBoard.oldKentRoad, new Property(),
                gameBoard.whitechapelRoad, new Property(),
                gameBoard.angelIslington, new Property(),
                gameBoard.euston, new Property(), gameBoard.pentonville,
                new Property(), gameBoard.pallMall, gameBoard.whitehall,
                gameBoard.northhumbAve, gameBoard.bowStreet, gameBoard.marlborough,
                new Property(), gameBoard.vineStreet, new Property(),
                gameBoard.strand, new Property(), gameBoard.fleetStreet,
                gameBoard.trafalgar, gameBoard.leicester, gameBoard.coventry,
                new Property(), gameBoard.picadilly, new Property(),
                gameBoard.regentStreet, new Property(), gameBoard.oxford,
                gameBoard.bondStreet, new Property(), gameBoard.parkLane,
                new Property(), gameBoard.mayfair

        };
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

        completeInitialCircuit(playersCompletedCircuit, players, numberOfPlayers);
        System.out.println("ALL PLAYERS COMPLETED INITIAL CIRCUIT");
//        while(!playerBankrupt){
//
//
//
//        }
        }
    public static void completeInitialCircuit(boolean playersCompCircuit, Player[] gamePlayers, int playerNumber){
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
                playerNumber = rollDiceHelper(player, playerNumber, playerRolledDice);

                if (playerRolledDice[0] == playerRolledDice[1]){
                    System.out.println(player.getPlayerName() + " rolled a double, "+ "Press [1] to Roll Dice:");
                    playerDicePrompt = scanner.nextInt();

                    while (playerDicePrompt != 1){
                        System.out.println("Wrong input, Press [1] to Roll Dice:");
                        playerDicePrompt = scanner.nextInt();
                    }
                    playerRolledDice = player.rollDice();
                    playerNumber = rollDiceHelper(player, playerNumber, playerRolledDice);
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
                            playerNumber = rollDiceHelper(player, playerNumber, playerRolledDice);
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
    public static int rollDiceHelper(Player currentPlayer, int playersInGame, int[] currentRolledDice){


        int rolledDiceSum = currentRolledDice[0] + currentRolledDice[1];

        int currentPosition = currentPlayer.getPlayerPosition();
        System.out.println(
                "You Rolled: " + currentRolledDice[0] + " and " + currentRolledDice[1] +
                        " Current Position: " + currentPosition
        );

        int newPosition = (currentPosition + rolledDiceSum);

        if (newPosition > 35){

            currentPlayer.setPlayerPosition(newPosition - 36);
            playersInGame += 1;
            System.out.println(currentPlayer.getPlayerName() + " COMPLETED CIRCUIT.");
        }else{
            currentPlayer.setPlayerPosition(newPosition);
        }
        System.out.println(currentPlayer.getPlayerName() + " Rolled a " + rolledDiceSum);
        System.out.println(currentPlayer.getPlayerName() + " is in position: " + currentPlayer.getPlayerPosition());

        return playersInGame;

    }
    public static void playerBuyProperty(Player targetPlayer){

    }
}