package org.lwazimonopoly;

import java.util.Arrays;

public class Property {
    //Property class attributes for propertyName, propertyRentLevel and currentRentLevel
    private String propertyName;
    public int[] propertyRentLevels = new int[5];
    private int currentRentLevel = 0;
    private Player propertyOwner = null;
    public Property(String propertyName, int[] propertyRentLevel){
        this.propertyName = propertyName;
        for (int i = 0; i < propertyRentLevel.length; i++){
            this.propertyRentLevels[i] = propertyRentLevel[i];
        }
    }

    public String getPropertyName() {
        return propertyName;
    }

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
}
