package org.lwazimonopoly;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class MonopolyBoard {
    //Shuffle Banking Event Card Deck

    public ArrayList<String> specialEventCardsDeck = SpecialEventCards.specialEventCards_shuffled;
    public ArrayList<Integer> statusEffectPostions = new ArrayList<>();
    public ArrayList<Integer> propertyCardPositions = new ArrayList<>();
    public ArrayList<Integer> locationCardPositions = new ArrayList<>();
    public ArrayList<Integer> specialEventCardPositions = new ArrayList<>();

    StatusEffectCard start = new StatusEffectCard("Go");
    Property oldKentRoad = new Property("Old Kent Road",60, new int[]{70, 130, 220, 370, 750}, "brown");
    SpecialEventCards specialEventCardOne = new SpecialEventCards();
    Property whitechapelRoad = new Property("Whitechapel Road", 60, new int[]{70, 130, 220, 370, 750}, "brown");
    SpecialEventCards specialEventCardTwo = new SpecialEventCards();
    Property angelIslington = new Property("The Angel, Islington", 100, new int[]{80, 140, 240, 410, 800}, "light blue");
    Property euston = new Property("Euston Road", 100, new int[]{80, 140, 240, 410, 800}, "light blue");
    LocationCard locationCardOne = new LocationCard();
    Property pentonville = new Property("Pentonville Road", 120, new int[]{100, 160, 260, 440, 860}, "light blue");
    StatusEffectCard jail = new StatusEffectCard("Jail");
    Property pallMall = new Property("Pall Mall", 140, new int[]{110, 180, 290, 460, 900}, "pink");
    SpecialEventCards specialEventCardThree = new SpecialEventCards();
    Property whitehall = new Property("Whitehall", 140, new int[]{110, 180, 290, 460, 900}, "pink");
    Property northhumbAve = new Property("Northumberland Avenue", 160, new int[]{130, 200, 310, 490, 980}, "pink");
    Property bowStreet = new Property("Bow Street", 180, new int[]{140, 210, 330, 520, 1000}, "orange");
    Property  marlborough = new Property("Marlborough Street", 180, new int[]{140, 210, 330, 520, 1000}, "orange");
    LocationCard locationCardTwo = new LocationCard();
    Property vineStreet = new Property("Vine Street", 200, new int[]{160, 230, 350, 550, 1100}, "orange");
    StatusEffectCard freeParking = new StatusEffectCard("Free Parking");
    Property strand = new Property("Strand", 220, new int[]{170, 250, 380, 5580, 1160}, "red");
    SpecialEventCards specialEventCardFour = new SpecialEventCards();
    Property fleetStreet = new Property("Fleet Street", 220, new int[]{170, 250, 380, 5580, 1160}, "red");
    Property trafalgar = new Property("Trafalgar Square", 240, new int[]{190, 270, 400, 610, 1200}, "red");
    Property leicester = new Property("Leicester Square", 260, new int[]{200, 280, 420, 640, 1300}, "yellow");
    Property coventry = new Property("Coventry Street",260, new int[]{200, 280, 420, 640, 1300}, "yellow");
    LocationCard locationCardThree = new LocationCard();
    Property picadilly = new Property("Picadilly",280, new int[]{220, 300, 440, 670, 1340}, "yellow");
    StatusEffectCard goToJail = new StatusEffectCard("Go to Jail");
    Property regentStreet = new Property("Regent Street",300, new int[]{230, 320, 460, 700, 1400}, "green");
    SpecialEventCards specialEventCardFive = new SpecialEventCards();
    Property oxford = new Property("Oxford Street",300, new int[]{230, 320, 460, 700, 1400}, "green");
    Property bondStreet = new Property("Bond Street",320,  new int[]{250, 340, 480, 730, 1440}, "green");
    SpecialEventCards specialEventCardSix = new SpecialEventCards();
    Property parkLane = new Property("Park Lane", 350, new int[]{270, 360, 510, 740, 1500}, "blue");
    LocationCard locationCardFour = new LocationCard();
    Property mayfair = new Property("Mayfair",400, new int[]{300, 400, 560, 810, 1600}, "blue");

    public ArrayList<Object> orderedBoard = new ArrayList<>(
            Arrays.asList(
                    start, oldKentRoad, specialEventCardOne, whitechapelRoad,specialEventCardTwo,
                    angelIslington, euston, locationCardOne, pentonville, jail, pallMall,
                    specialEventCardThree, whitehall, northhumbAve, bowStreet, marlborough,
                    locationCardTwo, vineStreet, freeParking, strand, specialEventCardFour,
                    fleetStreet, trafalgar, leicester, coventry, locationCardThree, picadilly,
                    goToJail, regentStreet, specialEventCardFive, oxford, bondStreet,
                    specialEventCardSix, parkLane, locationCardFour, mayfair
            )
    );

    public MonopolyBoard() {
        // "i" will be used to find the index positions of each cell on the board
        // this will help with accessing methods
        for (int i = 0; i < orderedBoard.size(); i++) {
            if (orderedBoard.get(i) instanceof Property) {
                propertyCardPositions.add(i);
            } else if (orderedBoard.get(i) instanceof LocationCard) {
                locationCardPositions.add(i);
            } else if (orderedBoard.get(i) instanceof SpecialEventCards) {
                specialEventCardPositions.add(i);
            } else if (orderedBoard.get(i) instanceof StatusEffectCard) {
                statusEffectPostions.add(i);
            }
        }
    }
    public void movePlayerOnBoard(Player player) {
        player.rollDice();
        // 2nd Roll
        if (!player.playerActive && player.rolledDice[0] == player.rolledDice[1]) {
            player.rollDice();
            // 3rd Roll
            if (!player.playerActive && player.rolledDice[0] == player.rolledDice[1]) {
                System.out.println("Rolled Double 3 Times. Can't Roll Again.");
            }
        } else if (player.playerActive && player.rolledDice[0] == player.rolledDice[1]) {
            player.setPlayerBoardPosition(player.rolledDice[0] + player.rolledDice[1]);
            Object cell = orderedBoard.get(player.playerBoardPosition);
            if (propertyCardPositions.contains(player.playerBoardPosition)) {
                propertyTransaction((Property) cell, player);
            } else if (locationCardPositions.contains(player.playerBoardPosition)) {
                newPosition(player);
            } else if (specialEventCardPositions.contains(player.playerBoardPosition)) {
                specialEvent(player);
            } else if (statusEffectPostions.contains(player.playerBoardPosition)) {
                statusEffect(player);
            }
        }
    }

    private void propertyTransaction(Property boardCell, Player player) {
         if (boardCell.isPropertyOwned() && player != boardCell.getPropertyOwner()){
             // Logic for paying rent
         } else if (boardCell.isPropertyOwned() && player == boardCell.getPropertyOwner()) {
             // Give player the option to increase rent level
         } else if (!boardCell.isPropertyOwned()) {
             // Give player option to buy property
         }
    }
    private void newPosition(Player player) {
        System.out.println(player.getPlayerName() + " landed on a Location cell.");
        System.out.println("Options:");
        System.out.println("1. Pay R100 to move to an vacant property");
        System.out.println("2. Stay on current cell");
        int input = new Scanner(System.in).nextInt();
        while (!new ArrayList<>(Arrays.asList(1,2)).contains(input)) {
            System.out.println("!!!Invalid input!!!");
            System.out.println("1. Pay R100 to move to an vacant property");
            System.out.println("2. Stay on current cell");
            input = new Scanner(System.in).nextInt();
        }

        if (input == 1) {
            newPositionTransaction(player);

        }
    }
    private void specialEvent(Player player) {

    }
    private void statusEffect(Player player) {

    }
    private void newPositionTransaction(Player player) {
        player.deductPlayerCredit(LocationCard.movingCost);
        ArrayList<Property> availableProperties = new ArrayList<>();
        ArrayList<Integer> index = new ArrayList<>();
        for (int i : propertyCardPositions) {
            if (Objects.equals(((Property) orderedBoard.get(i)).getPropertyOwner(), null)) {
                availableProperties.add((Property) orderedBoard.get(i));
            }
        }

        System.out.println("Available properties for purchase, enter property index");

        for (int i = 0; i < availableProperties.size(); i++) {
            System.out.println(i + " - " + availableProperties.get(i).getPropertyName());
            index.add(i);
        }

        int input = new Scanner(System.in).nextInt();
        while (!index.contains(input)) {
            System.out.println("!!!Invalid input, Try Again!!!");
        }

        player.setPlayerProperties(availableProperties.get(input));

        System.out.println("Purchased " + availableProperties.get(input).getPropertyName());
    }

}
