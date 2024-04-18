package org.lwazimonopoly;

import java.util.ArrayList;

public class Player {
    private String playerName;
    private int playerCredit = 1500;
    private ArrayList <Property> playerProperties = new ArrayList<>();
    private int playerPosition = 0;
    private boolean playerBuyProperty = false;
    public Player(String playerName){
        this.playerName = playerName;
    }
    //TODO: If the values of both dice are equal to each other, prompt the player to roll again

    public int[] rollDice(){
        int dice1 = (int) (Math.random() * 6) + 1;
        int dice2 = (int) (Math.random() * 6) + 1;
        return new int[]{dice1, dice2};
    }

    //TODO: Print out the property name and it's current rent level for the method below, consider returning
    //TODO: the playerProperties list and do the for loop on line 24 where ever this method would be required
    public void getPlayerProperties() {
        System.out.println("Properties Owned by " + playerName);
        for (Property prop : playerProperties){
            System.out.println(prop.getPropertyName() + " - Rent Levels 1-5 " + prop.getPropertyRentLevels());
        }
    }

    public void setPlayerProperties(Property newPlayerProperty) {
        this.playerProperties.add(newPlayerProperty);
    }

    public int getPlayerCredit() {
        return playerCredit;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerPosition(int playerPosition) {
        this.playerPosition = playerPosition;
    }

    public int getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerCredit(int playerCredit) {
        this.playerCredit += playerCredit;
    }
     public void deductPlayerCredit(int playerCredit){
        this.playerCredit -= playerCredit;
     }
    public void setPlayerBuyProperty(boolean state){
        this.playerBuyProperty = state;
    }

    public boolean isPlayerBuyProperty() {
        return playerBuyProperty;
    }
    public ArrayList<Property> getPlayerPropertiesList(){
        return playerProperties;
    }
}
