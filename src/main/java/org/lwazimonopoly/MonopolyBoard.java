package org.lwazimonopoly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MonopolyBoard {
    //Shuffle Banking Event Card Deck
    public boolean shuffleDeck = Property.shuffleDeck();
    public ArrayList<String> deck = Property.specialEventCards_shuffled;

    //TODO: Special cells (eg. Free Parking, Jail) not populated
    //TODO:
    Property start = new Property("Start");
    Property oldKentRoad = new Property("Old Kent Road", new int[]{70, 130, 220, 370, 750}, "brown", 60);
    Property whitechapelRoad = new Property("Whitechapel Road", new int[]{70, 130, 220, 370, 750}, "brown", 60);
    Property angelIslington = new Property("The Angel, Islington", new int[]{80, 140, 240, 410, 800}, "light blue", 100);
    Property euston = new Property("Euston Road", new int[]{80, 140, 240, 410, 800}, "light blue", 100);
    Property pentonville = new Property("Pentonville Road", new int[]{100, 160, 260, 440, 860}, "light blue", 120);
    Property pallMall = new Property("Pall Mall", new int[]{110, 180, 290, 460, 900}, "pink", 140);
    Property whitehall = new Property("Whitehall", new int[]{110, 180, 290, 460, 900}, "pink", 140);
    Property northhumbAve = new Property("Northumberland Avenue", new int[]{130, 200, 310, 490, 980}, "pink", 160);
    Property bowStreet = new Property("Bow Street", new int[]{140, 210, 330, 520, 1000}, "orange", 180);
    Property  marlborough = new Property("Marlborough Street", new int[]{140, 210, 330, 520, 1000}, "orange", 180);
    Property vineStreet = new Property("Vine Street", new int[]{160, 230, 350, 550, 1100}, "orange", 200);
    Property strand = new Property("Strand", new int[]{170, 250, 380, 5580, 1160}, "red", 220);
    Property fleetStreet = new Property("Fleet Street", new int[]{170, 250, 380, 5580, 1160}, "red", 220);
    Property trafalgar = new Property("Trafalgar Square", new int[]{190, 270, 400, 610, 1200}, "red", 240);
    Property leicester = new Property("Leicester Square", new int[]{200, 280, 420, 640, 1300}, "yellow", 260);
    Property coventry = new Property("Coventry Street", new int[]{200, 280, 420, 640, 1300}, "yellow", 260);
    Property picadilly = new Property("Picadilly", new int[]{220, 300, 440, 670, 1340}, "yellow", 280);
    Property regentStreet = new Property("Regent Street", new int[]{230, 320, 460, 700, 1400}, "green", 300);
    Property oxford = new Property("Oxford Street", new int[]{230, 320, 460, 700, 1400}, "green", 300);
    Property bondStreet = new Property("Bond Street",  new int[]{250, 340, 480, 730, 1440}, "green", 320);
    Property parkLane = new Property("Park Lane", new int[]{270, 360, 510, 740, 1500}, "blue", 250);
    Property mayfair = new Property("Mayfair", new int[]{300, 400, 560, 810, 1600}, "blue", 400);
    Property bankingEventSpecialCard = new Property("Banking Event Card");
    Property moveToProperty = new Property("Location");
    Property jail = new Property("Jail");
    Property parking = new Property("Free Parking");
    Property goToJail = new Property("Go To Jail");
    public Property[] propertiesList = new Property[]{
            start,
            oldKentRoad, bankingEventSpecialCard,
            whitechapelRoad, bankingEventSpecialCard,
            angelIslington, euston,
            moveToProperty, pentonville,
            jail, pallMall, bankingEventSpecialCard, whitehall,
            northhumbAve, bowStreet, marlborough,
            moveToProperty, vineStreet, parking,
            strand, bankingEventSpecialCard, fleetStreet,
            trafalgar, leicester, coventry,
            moveToProperty, picadilly, goToJail,
            regentStreet, bankingEventSpecialCard, oxford,
            bondStreet, bankingEventSpecialCard, parkLane,
            moveToProperty, mayfair

    };

    public ArrayList<Property> sideAProperties = new ArrayList<>(Arrays.asList(
        oldKentRoad, whitechapelRoad, angelIslington,
            euston, pentonville
    ));
    public ArrayList<Property> sideBProperties = new ArrayList<>(Arrays.asList(
            pallMall, whitehall, northhumbAve,bowStreet,
            marlborough, vineStreet
    ));
    public ArrayList<Property> sideCProperties = new ArrayList<>(Arrays.asList(
            strand, fleetStreet, trafalgar, leicester,
            coventry, picadilly
    ));
    public ArrayList<Property> sideDProperties = new ArrayList<>(Arrays.asList(
            regentStreet, oxford, bondStreet, parkLane, mayfair
    ));



}
