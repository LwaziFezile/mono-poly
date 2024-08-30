package org.lwazimonopoly;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MonopolyBoard {
    //Shuffle Banking Event Card Deck

    public ArrayList<String> specialEventCardsDeck = SpecialEventCards.specialEventCards_shuffled;
    public ArrayList<Integer> statusEffectPostions = new ArrayList<>();
    public ArrayList<Integer> propertyCardPositions = new ArrayList<>();
    public ArrayList<Integer> locationCardPositions = new ArrayList<>();
    public ArrayList<Integer> specialEventCardPositions = new ArrayList<>();

    StatusEffectCard start = new StatusEffectCard("Go");
    Property oldKentRoad = new Property("Old Kent Road", new int[]{70, 130, 220, 370, 750}, "brown");
    SpecialEventCards specialEventCardOne = new SpecialEventCards();
    Property whitechapelRoad = new Property("Whitechapel Road", new int[]{70, 130, 220, 370, 750}, "brown");
    SpecialEventCards specialEventCardTwo = new SpecialEventCards();
    Property angelIslington = new Property("The Angel, Islington", new int[]{80, 140, 240, 410, 800}, "light blue");
    Property euston = new Property("Euston Road", new int[]{80, 140, 240, 410, 800}, "light blue");
    LocationCard locationCardOne = new LocationCard();
    Property pentonville = new Property("Pentonville Road", new int[]{100, 160, 260, 440, 860}, "light blue");
    StatusEffectCard jail = new StatusEffectCard("Jail");
    Property pallMall = new Property("Pall Mall", new int[]{110, 180, 290, 460, 900}, "pink");
    SpecialEventCards specialEventCardThree = new SpecialEventCards();
    Property whitehall = new Property("Whitehall", new int[]{110, 180, 290, 460, 900}, "pink");
    Property northhumbAve = new Property("Northumberland Avenue", new int[]{130, 200, 310, 490, 980}, "pink");
    Property bowStreet = new Property("Bow Street", new int[]{140, 210, 330, 520, 1000}, "orange");
    Property  marlborough = new Property("Marlborough Street", new int[]{140, 210, 330, 520, 1000}, "orange");
    LocationCard locationCardTwo = new LocationCard();
    Property vineStreet = new Property("Vine Street", new int[]{160, 230, 350, 550, 1100}, "orange");
    StatusEffectCard freeParking = new StatusEffectCard("Free Parking");
    Property strand = new Property("Strand", new int[]{170, 250, 380, 5580, 1160}, "red");
    SpecialEventCards specialEventCardFour = new SpecialEventCards();
    Property fleetStreet = new Property("Fleet Street", new int[]{170, 250, 380, 5580, 1160}, "red");
    Property trafalgar = new Property("Trafalgar Square", new int[]{190, 270, 400, 610, 1200}, "red");
    Property leicester = new Property("Leicester Square", new int[]{200, 280, 420, 640, 1300}, "yellow");
    Property coventry = new Property("Coventry Street", new int[]{200, 280, 420, 640, 1300}, "yellow");
    LocationCard locationCardThree = new LocationCard();
    Property picadilly = new Property("Picadilly", new int[]{220, 300, 440, 670, 1340}, "yellow");
    StatusEffectCard goToJail = new StatusEffectCard("Go to Jail");
    Property regentStreet = new Property("Regent Street", new int[]{230, 320, 460, 700, 1400}, "green");
    SpecialEventCards specialEventCardFive = new SpecialEventCards();
    Property oxford = new Property("Oxford Street", new int[]{230, 320, 460, 700, 1400}, "green");
    Property bondStreet = new Property("Bond Street",  new int[]{250, 340, 480, 730, 1440}, "green");
    SpecialEventCards specialEventCardSix = new SpecialEventCards();
    Property parkLane = new Property("Park Lane", new int[]{270, 360, 510, 740, 1500}, "blue");
    LocationCard locationCardFour = new LocationCard();
    Property mayfair = new Property("Mayfair", new int[]{300, 400, 560, 810, 1600}, "blue");

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
}
