package org.lwazimonopoly;


import java.lang.reflect.Array;
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
                        "\'s Turn. Press [1] to Roll Dice, Press [2] to view credit for all " +
                        "or [3] to view your properties"
                );
                int playerDicePrompt = validateInput(scanner);
                while (playerDicePrompt == 2 || playerDicePrompt == 3){
                    if (playerDicePrompt == 2) {
                        System.out.println("Player Credits:");
                        for (Player playerObj : cardMachine.getPlayers()){
                            System.out.println(playerObj.getPlayerName() + " - " + playerObj.getPlayerCredit());
                        }
                    } else if (playerDicePrompt == 3) {
                        System.out.println(player.getPlayerName() + "'\s Properties:");
                        player.getPlayerProperties();
                    }
                    System.out.println(
                            player.getPlayerName() +
                            "\'s Turn. Press [1] to Roll Dice, Press [2] to view credit for all " +
                            "or [3] to view your properties"
                    );
                    playerDicePrompt = validateInput(scanner);
                }
                if (playerDicePrompt == 1){
                    int[] playerRolledDice = player.rollDice();
                    rollDiceHelper(player, noOfPlayers, playerRolledDice, board, cardMachine);
                    // TODO: Refactor this
                    cardMachine.playerBuyProperty(player, board, cardMachine);
                } else if (playerDicePrompt == 2) {
                    System.out.println("Player Credits:");
                    for (Player playerObj : cardMachine.getPlayers()){
                        System.out.println(playerObj.getPlayerName() + " - " + playerObj.getPlayerCredit());
                    }
                } else if (playerDicePrompt == 3) {
                    System.out.println(player.getPlayerName() + "'\s Properties:");
                    player.getPlayerProperties();
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
                if (validateInput() == 1){
                    int[] playerRolledDice = player.rollDice();
                    playerNumber = rollDiceHelper(player, playerNumber, playerRolledDice, monopolyGameBoard, machine);
                    if (playerRolledDice[0] == playerRolledDice[1]){
                        System.out.println(player.getPlayerName() + " rolled a double, "+ "Press [1] to Roll Dice:");
                        if (validateInput() == 1){
                            playerRolledDice = player.rollDice();
                            playerNumber = rollDiceHelper(
                                    player, playerNumber, playerRolledDice,
                                    monopolyGameBoard, machine);

                            if (playerRolledDice[0] == playerRolledDice[1]){
                                System.out.println(
                                        player.getPlayerName() + " rolled a double, next double rolled, ends " +
                                                player.getPlayerName()+ "\'s turn. Press [1] to Roll Dice:");

                                if(validateInput() == 1){
                                    playerRolledDice = player.rollDice();
                                    if (playerRolledDice[0] == playerRolledDice[1]){
                                        System.out.println(
                                                player.getPlayerName() + " rolled a double 3 times, "+
                                                        player.getPlayerName() + "\'s turn is over."
                                        );
                                    }
                                    else {
                                        playerNumber = rollDiceHelper(
                                                player, playerNumber, playerRolledDice,
                                                monopolyGameBoard, machine
                                        );
                                    }
                                }
                            }
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
            machine.collect200(currentPlayer);
            currentPlayer.setPlayerPosition(newPosition - 36);
            playersInGame += 1;
            machine.playerBuyProperty(currentPlayer, monopolyGameBoard, machine);
            System.out.println(currentPlayer.getPlayerName() + " COMPLETED CIRCUIT.");
        }
        else if (newPosition <= 35 && currentPlayer.isPlayerBuyProperty()) {
            System.out.println(currentPlayer.getPlayerName() + " CAN NOW PURCHASE PROPERTY.");
            currentPlayer.setPlayerPosition(newPosition);
            machine.playerBuyProperty(currentPlayer, monopolyGameBoard, machine);
        }
        else if (newPosition > 35 && currentPlayer.isPlayerBuyProperty()) {
            System.out.println(currentPlayer.getPlayerName() + " Collected $200");
            machine.collect200(currentPlayer);
            currentPlayer.setPlayerPosition(newPosition - 36);
            machine.playerBuyProperty(currentPlayer, monopolyGameBoard, machine);
        }
        else{
            currentPlayer.setPlayerPosition(newPosition);
        }
        System.out.println(currentPlayer.getPlayerName() + " is in position: " + currentPlayer.getPlayerPosition());
        //TODO: ?? Sort out this because why am i returning this?
        return playersInGame;
    }

    // TODO: validateInput is used in completeInitialCircuit, it will bug out if the prompt was 3
    // TODO: at anytime during completeInitialCircuit call
    // QUICK FIX: Two methods of the same name with different parameters. Better than what was
    // QUICK FIX: previous
    public static int validateInput(Scanner scanner){
        int playerDicePrompt = scanner.nextInt();
        while (playerDicePrompt != 1 && playerDicePrompt != 2 && playerDicePrompt != 3){
            System.out.println(
                    "Wrong input, Press [1] to Roll Dice or [2] to view credit for all players" +
                            "or [3] to view your properties"
            );
            playerDicePrompt = scanner.nextInt();
        }
        return playerDicePrompt;
    }
    public static int validateInput(){
        Scanner scanner = new Scanner(System.in);
        int playerDicePrompt = scanner.nextInt();
        while (playerDicePrompt != 1 && playerDicePrompt != 2){
            System.out.println(
                    "Wrong input, Press [1] to Roll Dice or [2] to view credit for all players"
            );
            playerDicePrompt = scanner.nextInt();
        }
        return playerDicePrompt;
    }
    public static void validatePosition(
            int updatedPosition, Player currentPlayer,
            MonopolyBoard monopolyBoard, CardMachine machine, ArrayList<String> bankingEventCards ){

        Property currentCell = monopolyBoard.propertiesList[updatedPosition];
        if (currentCell.isPropertyColorGroupNull()){
            if (currentCell.getPropertyName() == "Banking Event Card"){
                String special = bankingEventCards.removeFirst();

                if (new ArrayList<String>(Arrays.asList("HAUNTED HOUSE","IN THE MONEY")).contains(special)
                ){
                    Property.exchangeProperties(currentPlayer, machine.getPlayers());
                }
                else if (new ArrayList<String>
                        (Arrays.asList("STOP THE PRESSES", "BOOM TOWN", "DEAL OF THE WEEK")).contains(special)) {
                    // TODO: Might have to display the properties not bought for clarity and the property
                    // TODO: the player owns if they want to increase the rent level
                    Property.moveToPropertySpace(currentPlayer, monopolyBoard);
                }
                else if (new ArrayList<String>
                        (Arrays.asList("WIBBLE WOBBLE", "LOVE IS IN THE AIR")).contains(special)) {
                    Property.increaseCreditBy200(currentPlayer, machine.getPlayers());
                }
                else if (new ArrayList<String>
                        (Arrays.asList("TORNADO ALLEY", "DEMOLISHED")).contains(special)) {
                    Property.resetPropertyRentLevel(currentPlayer);
                }
                else if (new ArrayList<String>
                        (Arrays.asList("CRIME DOWN", "STARGAZING", "ON THE MAP")).contains(special)) {
                    Property.increaseColorSetRentLevel(currentPlayer, monopolyBoard);
                } else if (special == "\'TIS THE SEASON") {
                    Property.decreaseColorSetRentLevel(currentPlayer);
                }
                else if (special == "PONG! WHAT A STINKER"){
                    Property.decreaseRentLevelSideBoard(currentPlayer, machine.getPlayers());
                }
                else if (special == "PICK YOUR OWN") {
                    Property.sendPlayerJail(machine.getPlayers());
                }
                else if (special == "ON THE RUN") {
                    Property.payLevelOneNextTwoTurns(machine.getPlayers());
                }
                else if (special == "GRAND DESIGNS") {
                    Property.increasePropertyMaxRent(currentPlayer);
                }
                else if (special == "WHAT A RIDE!") {
                    Property.increaseRentLevelSideBoard(currentPlayer);
                }
                else if (special == "HOUSE PARTY") {
                    Property.increaseRentLevelDropForNeighbours(currentPlayer);
                }
                else if (special == "HIGHWAY TAX"){
                    Property.payTaxForEveryProperty(currentPlayer);
                }
                else if (special == "TOTAL GRIDLOCK"){
                    Property.moveAllPlayersToParking(machine.getPlayers());
                }
                bankingEventCards.addLast(special);
            }
        }
    }
}

