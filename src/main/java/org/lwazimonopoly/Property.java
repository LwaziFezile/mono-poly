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
        this.currentRentLevel += currentRentLevel;
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
    // STARGAZING, CRIME DOWN, ON THE MAP
    public static void increaseColorSetRentLevel(Player targetPlayer, MonopolyBoard board){
        if (targetPlayer.getPlayerPropertiesList().isEmpty()){
            System.out.println(targetPlayer.getPlayerName() + " currently has no purchased properties");
        } else {
            System.out.println("Please input the property you want to increase it's rent level");
            System.out.println(targetPlayer.getPlayerName() + "\'s Properties:");
            ArrayList < Property > properties = targetPlayer.getPlayerPropertiesList();
            for (int i = 0; i < properties.size(); i++) {
                int index = i + 1;
                System.out.println("[" + index + "] - " + properties.get(i));
            }
            int propertySize = properties.size();
            Scanner scanner = new Scanner(System.in);
            int playerDicePrompt = scanner.nextInt();
            while (playerDicePrompt > propertySize || playerDicePrompt < 1){
                System.out.println(
                        "Wrong input, Please input the property you want to increase it's rent level"
                );
                for (int i = 0; i < properties.size(); i++) {
                    int index = i + 1;
                    System.out.println("[" + index + "] - " + properties.get(i));
                }
                playerDicePrompt = scanner.nextInt();
            }
            Property selectedProperty = targetPlayer.getPlayerPropertiesList().get(playerDicePrompt);
            for (Property property : board.propertiesList){
                if (property.getColorGroup() == selectedProperty.getColorGroup()){
                    property.setCurrentRentLevel(1);
                }
            }
            selectedProperty.setCurrentRentLevel(1);
            System.out.println(
                    selectedProperty.getPropertyName() +
                    "\'s rent level was increased by 1 along with it's color group.");
        }
    }
    // WHAT A RIDE
    public static void increaseRentLevelSideBoard(Player targetPlayer){

    }
    // GRAND DESIGNS
    public static void increasePropertyMaxRent(Player targetPlayer){

    }
    // DEMOLISHED, TORNADO ALLEY
    public static void resetPropertyRentLevel(Player targetPlayer){

    }
    // 'TIS THE SEASON
    public static void decreaseColorSetRentLevel(Player targetPlayer){

    }
    // PONG! WHAT A STINKER
    public static void decreaseRentLevelSideBoard(Player targetPlayer , Player[] playerList){

    }
    // HAUNTED HOUSE, IN THE MONEY
    public static void exchangeProperties(Player targetPlayer, Player[] playerList) {
        if (targetPlayer.getPlayerPropertiesList().isEmpty()) {
            System.out.println(targetPlayer.getPlayerName() + " currently has no purchased properties");
        } else {
            exchangePropertiesFunction(targetPlayer, playerList);
        }
    }
    public static void exchangePropertiesFunction(Player targetPlayer, Player[] playerList){
        System.out.println("Please input the number of the property you want to exchange");
        System.out.println(targetPlayer.getPlayerName() + "'s Properties:");
        ArrayList<Property> properties = targetPlayer.getPlayerPropertiesList();

        for (int i = 0; i < properties.size(); i++) {
            int index = i + 1;
            System.out.println("[" + index + "] - " + properties.get(i));
        }
        int propertySize = properties.size();
        Scanner scanner = new Scanner(System.in);
        int playerPrompt = scanner.nextInt();
        while (playerPrompt > propertySize || playerPrompt < 1) {
            System.out.println(
                    "Wrong input, Please input the number of the property you want to exchange"
            );
            for (int i = 0; i < properties.size(); i++) {
                int index = i + 1;
                System.out.println("[" + index + "] - " + properties.get(i));
            }
            // Stores the property index to be exchanged for target player
            playerPrompt = scanner.nextInt();
        }
        System.out.println(" Please input the number for the player you want to exchange with");
        for (int i = 0; i < playerList.length; i++) {
            int index = i + 1;
            System.out.println("[" + index + "] - " + playerList[i].getPlayerName());
        }
        int players = playerList.length;
        int selectedPlayerIndex = scanner.nextInt();
        while (selectedPlayerIndex > players || selectedPlayerIndex < 1) {
            System.out.println(
                    "Wrong input, Please input the number of the player you want to exchange with:"
            );
            for (int i = 0; i < playerList.length; i++) {
                int index = i + 1;
                System.out.println("[" + index + "] - " + playerList[i].getPlayerName());
            }
            selectedPlayerIndex = scanner.nextInt();
        }
        Player selectedPlayer = playerList[selectedPlayerIndex];
        if (selectedPlayer.getPlayerPropertiesList().isEmpty()) {
            System.out.println(
                    selectedPlayer.getPlayerName() + " currently has no purchased properties. Please select" +
                            " another player or [0] to end your turn if no players own property."
            );
            while (selectedPlayerIndex > players || selectedPlayerIndex < 1) {
                System.out.println(
                        "Wrong input, Please input the number of the player you want to exchange with:"
                );
                for (int i = 0; i < playerList.length; i++) {
                    int index = i + 1;
                    System.out.println("[" + index + "] - " + playerList[i].getPlayerName());
                }
                selectedPlayerIndex = scanner.nextInt();
            }
            if (selectedPlayerIndex == 0){
                System.out.println("You have ended your turn");
            }
            else {
                System.out.println("Please input the number of the property you want to exchange");
                System.out.println(targetPlayer.getPlayerName() + "\'s Properties:");
                ArrayList<Property> selectedPlayerProperties =
                        playerList[selectedPlayerIndex].getPlayerPropertiesList();

                for (int i = 0; i < selectedPlayerProperties.size(); i++) {
                    int index = i + 1;
                    System.out.println("[" + index + "] - " + selectedPlayerProperties.get(i));
                }
                propertySize = selectedPlayerProperties.size();
                int selectedPlayerPrompt = scanner.nextInt();
                while (selectedPlayerPrompt > propertySize || selectedPlayerPrompt < 1) {
                    System.out.println(
                            "Wrong input, Please input the number of the property you want to exchange"
                    );
                    for (int i = 0; i < selectedPlayerProperties.size(); i++) {
                        int index = i + 1;
                        System.out.println("[" + index + "] - " + selectedPlayerProperties.get(i));
                    }
                    // Stores the property index to be exchanged for selected player
                    selectedPlayerPrompt = scanner.nextInt();
                }
                Property targetPlayerExchange = properties.remove(playerPrompt);
                Property selectedPlayerExchange = selectedPlayerProperties.remove(selectedPlayerPrompt);
                properties.add(selectedPlayerExchange);
                selectedPlayerProperties.add(targetPlayerExchange);
                System.out.println(
                        targetPlayer.getPlayerName() + " exchanged " +
                                targetPlayerExchange.getPropertyName() + " with " + selectedPlayer.getPlayerName()
                                + " for " + selectedPlayerExchange.getPropertyName()
                );
            }
        }
    }
    // DEAL OF THE WEEK, STOP THE PRESSES, BOOM TOWN
    public static void moveToPropertySpace(Player targetPlayer, MonopolyBoard board){
        // TODO: Circle back here once the Auctioning has been has been added
        Property[] propertyBoard = board.propertiesList;
        ArrayList <Property> targetProperties = targetPlayer.getPlayerPropertiesList();
        System.out.println("Select Property Number You Want to Move To");
        System.out.println(targetPlayer.getPlayerName() + "' Properties:");
        for (Property property : targetPlayer.getPlayerPropertiesList()){
            System.out.println(
                    "[" + targetPlayer.getPlayerPropertiesList().indexOf(property) +
                    "] - " + property.getPropertyName());
        }
        System.out.println("---------------------------------------------------------");
        System.out.println("Properties Available for Purchase:");
        for(int i = 0; i < propertyBoard.length; i++){
            if (propertyBoard[i].isPropertyOwnerNull()){
                System.out.println(
                        "["  + i +
                        "] - " + propertyBoard[i].getPropertyName()
                );
            }
        }
//        int targetPropertiesSize = targetProperties.size();
        Scanner scanner = new Scanner(System.in);
        int playerPrompt = scanner.nextInt();
        while (!propertyBoard[playerPrompt].isPropertyOwnerNull() &&
                propertyBoard[playerPrompt].getPropertyName() != targetPlayer.getPlayerName()
        ){
            System.out.println(
                    "Wrong input, You don't own this property. Please input the Property You Want to Move To:"
            );
            System.out.println("Select Property Number You Want to Move To");
            System.out.println(targetPlayer.getPlayerName() + "' Properties:");
            for (Property property : targetPlayer.getPlayerPropertiesList()){
                System.out.println(
                        "[" + targetPlayer.getPlayerPropertiesList().indexOf(property) +
                                "] - " + property.getPropertyName());
            }
            System.out.println("---------------------------------------------------------");
            System.out.println("Properties Available for Purchase:");
            for (Property property : targetPlayer.getPlayerPropertiesList()){
                System.out.println(
                        "[" + targetPlayer.getPlayerPropertiesList().indexOf(property) +
                                "] - " + property.getPropertyName());
            }
            playerPrompt = scanner.nextInt();
        }
        if (targetPlayer.getPlayerName() == propertyBoard[playerPrompt].getPropertyOwner()){
            System.out.println(
                    "You've selected " + propertyBoard[playerPrompt].getPropertyName() + ". Rent Level Increased " +
                    "By One."
            );
            propertyBoard[playerPrompt].setCurrentRentLevel(1);
        }else if (propertyBoard[playerPrompt].isPropertyOwnerNull()){
            System.out.println(
                    "You've selected " + propertyBoard[playerPrompt].getPropertyName() + " to purchase for" +
                    propertyBoard[playerPrompt].getPurchaseValue());
            propertyBoard[playerPrompt].setPropertyOwner(targetPlayer);
        }
    }
    // WIBBLE WOBBLE, LOVE IS IN THE AIR
    public static void increaseCreditBy200(Player targetPlayer, Player[] playerList){
        System.out.println("Please input the number for the player you want to credit $200 to:");
        for (int i = 0; i < playerList.length; i++){
            int index = i + 1;
            System.out.println("[" + index + "] - " + playerList[i].getPlayerName());
        }
        int players = playerList.length;
        Scanner scanner = new Scanner(System.in);
        int playerDicePrompt = scanner.nextInt();
        while (playerDicePrompt > players || playerDicePrompt < 1){
            System.out.println(
                    "Wrong input, Please input the number for the player you want to credit $200 to:"
            );
            for (int i = 0; i < playerList.length; i++){
                int index = i + 1;
                System.out.println("[" + index + "] - " + playerList[i].getPlayerName());
            }
            playerDicePrompt = scanner.nextInt();
        }
        System.out.println(
                targetPlayer.getPlayerName() + " and " + playerList[playerDicePrompt].getPlayerName()
                + " have been credited with $200"
        );
        targetPlayer.setPlayerCredit(200);
        playerList[playerDicePrompt].setPlayerCredit(200);
    }
    // PICK YOUR OWN
    public static void sendPlayerJail(Player [] playerList){
        System.out.println("Please input the number for the player you want to send to jail:");
        for (int i = 0; i < playerList.length; i++){
            int index = i + 1;
            System.out.println("[" + index + "] - " + playerList[i].getPlayerName());
        }
        int players = playerList.length;
        Scanner scanner = new Scanner(System.in);
        int playerDicePrompt = scanner.nextInt();
        while (playerDicePrompt > players || playerDicePrompt < 1){
            System.out.println(
                    "Wrong input, Please input the number for the player you want to send to jail"
            );
            for (int i = 0; i < playerList.length; i++){
                int index = i + 1;
                System.out.println("[" + index + "] - " + playerList[i].getPlayerName());
            }
            playerDicePrompt = scanner.nextInt();
        }
        System.out.println(playerList[playerDicePrompt].getPlayerName() + " has been sent to Jail");
        playerList[playerDicePrompt].setPlayerPosition(9);
    }
    // ON THE RUN
    public static void payLevelOneNextTwoTurns(Player [] playerList){

    }
    // TOTAL GRIDLOCK
    public static void moveAllPlayersToParking(Player [] playerList){
        for (Player player : playerList){
            player.setPlayerPosition(18);
        }
    }
    // HIGHWAY TAX
    public static void payTaxForEveryProperty(Player targetPlayer){
        int propertiesSize = targetPlayer.getPlayerPropertiesList().size();
        int rentToPay = 50 * propertiesSize;
        targetPlayer.deductPlayerCredit(rentToPay);
    }
    // HOUSE PARTY
    public static void increaseRentLevelDropForNeighbours(Player targetPlayer){

    }



}
