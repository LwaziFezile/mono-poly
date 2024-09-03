package org.lwazimonopoly;

import java.util.ArrayList;
import java.util.Arrays;

public class Property {
    //Property class attributes for propertyName, propertyRentLevel, currentRentLevel, propertyOwner and colorGroup
    private String propertyName;

    private int[] propertyRentLevels = new int[5];

    private int currentRentLevel = 0;

    private Player propertyOwner = null;

    private final String colorGroup;
    private int listedPrice;

    // Property Constructor, initializes the local attributes for this class
    public Property(String propertyName,int listedPrice, int[] propertyRentLevel, String colorGroup){
        this.propertyName = propertyName;
        this.colorGroup = colorGroup;
        this.listedPrice = listedPrice;
        // Refer to the instantiation of Property in class MonopolyBoard, Populates the propertyRentLevel to the local
        // propertyRentLevels Array
        for (int i = 0; i < propertyRentLevel.length; i++){
            this.propertyRentLevels[i] = propertyRentLevel[i];
        }
    }

    public String getPropertyName() {
        return propertyName;
    }
    public String getColorGroup(){ return colorGroup; }

    public String getPropertyRentLevels() {
        return Arrays.toString(propertyRentLevels);
    }

    public int getCurrentRentLevel() {
        return propertyRentLevels[currentRentLevel];
    }

    public void increaseCurrentRentLevel() {
        if (currentRentLevel < 5){
            currentRentLevel++;
            System.out.println(this.propertyName + " current rent - R" + propertyRentLevels[currentRentLevel]);
        }else {
            System.out.println("Max Rent for " + this.propertyName);
        }

    }

    public Player getPropertyOwner() {
        return propertyOwner;
    }

    public void setPropertyOwner(Player propertyOwner) {
        this.propertyOwner = propertyOwner;
        currentRentLevel = propertyRentLevels[0];
    }

    public boolean isPropertyOwned(){
        return propertyOwner != null;
    }


    public int getListedPrice() {
        return listedPrice;
    }

}
