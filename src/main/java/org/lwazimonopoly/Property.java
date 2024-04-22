package org.lwazimonopoly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Property {
    //Property class attributes for propertyName, propertyRentLevel, currentRentLevel, propertyOwner and colorGroup
    private String propertyName;
    public int[] propertyRentLevels = new int[5];
    private int currentRentLevel = 0;
    private Player propertyOwner = null;
    private final String colorGroup;
    private int purchaseValue;

    // deckShuffled is used to determine whether the deck has been shuffled at the beginning of the game or not
    private static boolean deckShuffled = false;
    // specialEventCardNames contains the Banking Event Card names, used for shuffleDeck()
    private static String[] specialEventCardNames = new String[]{
            "STARGAZING", "GRAND DESIGNS", "CRIME DOWN", "WHAT A RIDE",
            "ON THE MAP", "ON THE RUN", "TORNADO ALLEY", "DEMOLISHED",
            "\'TIS THE SEASON", "PONG! WHAT A STINKER", "HOUSE PARTY",
            "PICK YOUR OWN", "LOVE IS IN THE AIR", "HIGHWAY TAX", "DEAL OF THE WEEK",
            "STOP THE PRESSES", "BOOM TOWN", "HAUNTED HOUSE", "IN THE MONEY", "TOTAL GRIDLOCK",
            "WIBBLE WOBBLE"
    };
    // Stores the Shuffled Deck
    public static ArrayList <String> specialEventCards_shuffled = new ArrayList<>();

    // Property Constructor, initializes the local attributes for this class
    public Property(String propertyName, int[] propertyRentLevel, String colorGroup, int purchaseValue){
        this.purchaseValue = purchaseValue;
        this.propertyName = propertyName;
        this.colorGroup = colorGroup;

        // Refer to the instantiation of Property in class MonopolyBoard, Populates the propertyRentLevel to the local
        // propertyRentLevels Array
        for (int i = 0; i < propertyRentLevel.length; i++){
            this.propertyRentLevels[i] = propertyRentLevel[i];
        }
    }
    public Property(){
        this.colorGroup = null;
    }
    public Property(String propertyName){
        this.propertyName = propertyName;
        this.colorGroup = null;
    }
    // Randomly shuffles the deck
    public static boolean shuffleDeck(){
        if (!deckShuffled){
            ArrayList <String> arrayList = new ArrayList<>();
            for (String i : specialEventCardNames){
                arrayList.addLast(i);
            }
            int index = 0;
            while (index < arrayList.size()){
                int randomIndex = (int) (Math.random() * arrayList.size());
                if ( !specialEventCards_shuffled.contains(arrayList.get(randomIndex)) ){
                    specialEventCards_shuffled.addLast(arrayList.get(randomIndex));
                    index++;
                }
            }
            deckShuffled = true;
            return deckShuffled;
        }
        return deckShuffled;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public String getColorGroup(){ return colorGroup; }
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyRentLevels() {
        return Arrays.toString(propertyRentLevels);
    }

    public int getCurrentRentLevel() {
        return propertyRentLevels[currentRentLevel];
    }

    public void setCurrentRentLevel(int currentRentLevel) {
        if (currentRentLevel < 4){
            this.currentRentLevel += currentRentLevel;
        }else{
            System.out.println("Rent Level Max");
        }

    }
    public void decreaseRentLevel(int currentRentLevel) {
        if (currentRentLevel > 0){
            this.currentRentLevel -= currentRentLevel;
        }else{
            System.out.println("Rent Level Min");
        }
    }
    public void resetRentLevel(){
        this.currentRentLevel = 0;
    }
    public void setMaxRentLevel(){
        this.currentRentLevel = 4;
    }
    public int getPurchaseValue() {
        return purchaseValue;
    }

    public String getPropertyOwner() {
        return propertyOwner.getPlayerName();
    }

    public boolean isPropertyOwnerNull(){
        return this.propertyOwner == null;
    }
    public boolean isPropertyColorGroupNull(){
        return this.colorGroup == null;
    }
    public void setPropertyOwner(Player propertyOwner) {
        this.propertyOwner = propertyOwner;
    }




}
