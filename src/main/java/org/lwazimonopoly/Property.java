package org.lwazimonopoly;

import java.util.Arrays;

public class Property {
    //Property class attributes for propertyName, propertyRentLevel and currentRentLevel
    private String propertyName;
    public int[] propertyRentLevels = new int[5];
    private int currentRentLevel = 0;
    private Player propertyOwner = null;
    private final String colorGroup;
    private static boolean deckShuffled = false;
    private Property[] bankingEventCards = new Property[6];


    public Property(String propertyName, int[] propertyRentLevel, String colorGroup){
        this.propertyName = propertyName;
        this.colorGroup = colorGroup;
        for (int i = 0; i < propertyRentLevel.length; i++){
            this.propertyRentLevels[i] = propertyRentLevel[i];
        }
    }
    public static void shuffleDeck(){
//        int index = 0;
//        if (!deckShuffled){
//            while (index < )
//
//            deckShuffled = true;
//        }
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
        this.currentRentLevel = currentRentLevel;
    }

    public String getPropertyOwner() {
        return propertyOwner.getPlayerName();
    }

    public void setPropertyOwner(Player propertyOwner) {
        this.propertyOwner = propertyOwner;
    }

    public static void increaseColorSetRentLevel(Player targetPlayer , Property targetProperty){

    }
    public static void increaseRentLevelSideBoard(Player targetPlayer , Property targetProperty){

    }
    public static void increasePropertyMaxRent(Player targetPlayer , Property targetProperty){

    }
    public static void resetPropertyRentLevel(Player targetPlayer , Property targetProperty){

    }
    public static void decreaseColorSetRentLevel(Player targetPlayer , Property targetProperty){

    }
    public static void decreaseRentLevelSideBoard(Player targetPlayer , Property targetProperty){

    }

}
