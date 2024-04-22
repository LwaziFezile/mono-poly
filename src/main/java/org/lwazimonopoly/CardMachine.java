package org.lwazimonopoly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


public class CardMachine {
//TODO: Card Machine potential game master(?) Probably maintains the game state and
//TODO: values?
    private Player player1, player2, player3, player4 = null;
    private Player[] players;
    public CardMachine(int numberOfPlayers){
        players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            int index = i + 1;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Player " + index + " Name: ");
            String name = scanner.nextLine();
            if (player1 == null){
                this.player1 = new Player(name);
                players[i] = player1;
            } else if (player2 == null) {
                this.player2 = new Player(name);
                players[i] = player2;
            } else if (player3 == null) {
                this.player3 = new Player(name);
                players[i] = player3;
            } else if (player4 == null) {
                this.player4 = new Player(name);
                players[i] = player4;
            }
        }

    }

    public Player[] getPlayers() {
        return players;
    }
    public void collect200(Player targetPlayer){
        targetPlayer.setPlayerCredit(200);
    }

    public void playerBuyProperty(
            Player targetPlayer, HashMap<Integer,
            Property> monopolyGameBoard, CardMachine cardMachine
    ) {
        int currentPosition = targetPlayer.getPlayerPosition();
        Property landedProperty = monopolyGameBoard.get(currentPosition);
        int propertyPrice = landedProperty.getPurchaseValue();
        if (currentPosition > 0 && landedProperty.isPropertyOwnerNull()) {
            System.out.println(landedProperty.getPropertyName() + " is available to purchase for $" + propertyPrice);
            System.out.println("Press [1] to purchase this property or [0] to end your turn");
            int playerDicePrompt = Main.scanner.nextInt();

            while (playerDicePrompt != 1 && playerDicePrompt != 0) {
                System.out.println("Press [1] to purchase this property or [0] to end your turn");
                playerDicePrompt = Main.scanner.nextInt();
            }
            if (playerDicePrompt == 1) {
                System.out.println(
                        "You Have Purchased " + landedProperty.getPropertyName()
                                + ". Starting Rent Level: " + landedProperty.getCurrentRentLevel()
                );
                landedProperty.setPropertyOwner(targetPlayer);
                targetPlayer.setPlayerProperties(landedProperty);
                targetPlayer.deductPlayerCredit(propertyPrice);
                for (Player player : cardMachine.getPlayers()) {
                    System.out.println(player.getPlayerName() + " - " + "$" + player.getPlayerCredit());
                }
            } else if (playerDicePrompt == 0) {
                System.out.println(targetPlayer.getPlayerName() + " ended their turn.");
            }

        }
    }


    // STARGAZING, CRIME DOWN, ON THE MAP
    public static void increaseColorSetRentLevel(Player targetPlayer, MonopolyBoard board){
        if (targetPlayer.getPlayerPropertiesList().isEmpty()){
            System.out.println(targetPlayer.getPlayerName() + " currently has no purchased properties");
        } else {
            System.out.println("Please input the property you want to increase it's rent level");
            System.out.println(targetPlayer.getPlayerName() + "\'s Properties:");
            ArrayList< Property > properties = targetPlayer.getPlayerPropertiesList();
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
    public static void increaseRentLevelSideBoard(Player targetPlayer, MonopolyBoard board){
        System.out.println("Please input a property to have its rent level INCREASED to 1 along with it's NEIGHBOURS");
        System.out.println(targetPlayer.getPlayerName() + "\'s Properties:");
        ArrayList<Property> properties = targetPlayer.getPlayerPropertiesList();
        for (int i = 0; i < properties.size(); i++) {
            int index = i + 1;
            System.out.println("[" + index + "] - " + properties.get(i));
        }
        int propertySize = properties.size();
        Scanner scanner = new Scanner(System.in);
        int playerDicePrompt = scanner.nextInt();
        while (playerDicePrompt > propertySize || playerDicePrompt < 1) {
            System.out.println(
                    "Wrong input, " +
                            "Please input a property to have its rent level INCREASED to 1 along with it's NEIGHBOURS"
            );
            for (int i = 0; i < properties.size(); i++) {
                int index = i + 1;
                System.out.println("[" + index + "] - " + properties.get(i));
            }
            playerDicePrompt = scanner.nextInt();
        }
        Property selectedProperty = targetPlayer.getPlayerPropertiesList().get(playerDicePrompt);
        ArrayList<Property> selectedPropertySide = null;
        ArrayList[] sides = new ArrayList[]{
                board.sideAProperties, board.sideBProperties, board.sideCProperties, board.sideDProperties
        };
        for (ArrayList side : sides) {
            if (side.contains(selectedProperty)) {
                selectedPropertySide = side;
                break;
            }
        }
        if (selectedPropertySide == null) {
            System.out.println("ERROR : SELECTED PROPERTY SIDE IS NULL");
        }else{
            for (Property property : selectedPropertySide){
                property.setCurrentRentLevel(1);
                System.out.println(property.getPropertyName() + "'s rent has been INCREASED by 1");
            }
        }
    }
    // GRAND DESIGNS
    public static void increasePropertyMaxRent(Player targetPlayer){
        System.out.println("Please input a property to have its rent level reset set to it's max value");
        System.out.println(targetPlayer.getPlayerName() + "\'s Properties:");
        ArrayList< Property > properties = targetPlayer.getPlayerPropertiesList();
        for (int i = 0; i < properties.size(); i++) {
            int index = i + 1;
            System.out.println("[" + index + "] - " + properties.get(i));
        }
        int propertySize = properties.size();
        Scanner scanner = new Scanner(System.in);
        int playerDicePrompt = scanner.nextInt();
        while (playerDicePrompt > propertySize || playerDicePrompt < 1){
            System.out.println(
                    "Wrong input, Please input a property to have its rent level reset set to it's max value"
            );
            for (int i = 0; i < properties.size(); i++) {
                int index = i + 1;
                System.out.println("[" + index + "] - " + properties.get(i));
            }
            playerDicePrompt = scanner.nextInt();
        }
        Property selectedProperty = targetPlayer.getPlayerPropertiesList().get(playerDicePrompt);
        selectedProperty.setMaxRentLevel();
        System.out.println(selectedProperty.getPropertyName() + " Rent Level: $" + selectedProperty.getCurrentRentLevel() );
    }
    // DEMOLISHED, TORNADO ALLEY
    public static void resetPropertyRentLevel(Player targetPlayer){
        System.out.println("Please input the property a property to have its rent level reset to 1");
        System.out.println(targetPlayer.getPlayerName() + "\'s Properties:");
        ArrayList< Property > properties = targetPlayer.getPlayerPropertiesList();
        for (int i = 0; i < properties.size(); i++) {
            int index = i + 1;
            System.out.println("[" + index + "] - " + properties.get(i));
        }
        int propertySize = properties.size();
        Scanner scanner = new Scanner(System.in);
        int playerDicePrompt = scanner.nextInt();
        while (playerDicePrompt > propertySize || playerDicePrompt < 1){
            System.out.println(
                    "Wrong input, Please input the property a property to have its rent level reset to 1"
            );
            for (int i = 0; i < properties.size(); i++) {
                int index = i + 1;
                System.out.println("[" + index + "] - " + properties.get(i));
            }
            playerDicePrompt = scanner.nextInt();
        }
        Property selectedProperty = targetPlayer.getPlayerPropertiesList().get(playerDicePrompt);
        selectedProperty.resetRentLevel();
        System.out.println(selectedProperty.getPropertyName() + " Rent Level: $" + selectedProperty.getCurrentRentLevel() );
    }
    // 'TIS THE SEASON
    public static void decreaseColorSetRentLevel(Player targetPlayer, MonopolyBoard board){
        System.out.println("Please input a property to have its rent level reset to 1 along with it's color set");
        System.out.println(targetPlayer.getPlayerName() + "\'s Properties:");
        ArrayList< Property > properties = targetPlayer.getPlayerPropertiesList();
        for (int i = 0; i < properties.size(); i++) {
            int index = i + 1;
            System.out.println("[" + index + "] - " + properties.get(i));
        }
        int propertySize = properties.size();
        Scanner scanner = new Scanner(System.in);
        int playerDicePrompt = scanner.nextInt();
        while (playerDicePrompt > propertySize || playerDicePrompt < 1){
            System.out.println(
                    "Wrong input, Please input a property to have its rent level reset to 1 along with it's color set"
            );
            for (int i = 0; i < properties.size(); i++) {
                int index = i + 1;
                System.out.println("[" + index + "] - " + properties.get(i));
            }
            playerDicePrompt = scanner.nextInt();
        }
        Property selectedProperty = targetPlayer.getPlayerPropertiesList().get(playerDicePrompt);
        for (Property property : board.propertiesList){
            if (!property.isPropertyColorGroupNull() && property.getColorGroup() == selectedProperty.getColorGroup()){
                property.decreaseRentLevel(1);
                System.out.println(
                        property.getPropertyName() + "'s rent level has been dropped by 1 level to $" +
                        property.getCurrentRentLevel()
                );
            }
        }
    }
    // PONG! WHAT A STINKER
    public static void decreaseRentLevelSideBoard(Player targetPlayer , MonopolyBoard board) {
        System.out.println("Please input a property to have its rent level DECREASED to 1 along with it's NEIGHBOURS");
        System.out.println(targetPlayer.getPlayerName() + "\'s Properties:");
        ArrayList<Property> properties = targetPlayer.getPlayerPropertiesList();
        for (int i = 0; i < properties.size(); i++) {
            int index = i + 1;
            System.out.println("[" + index + "] - " + properties.get(i));
        }
        int propertySize = properties.size();
        Scanner scanner = new Scanner(System.in);
        int playerDicePrompt = scanner.nextInt();
        while (playerDicePrompt > propertySize || playerDicePrompt < 1) {
            System.out.println(
                    "Wrong input, " +
                            "Please input a property to have its rent level DECREASED to 1 along with it's NEIGHBOURS"
            );
            for (int i = 0; i < properties.size(); i++) {
                int index = i + 1;
                System.out.println("[" + index + "] - " + properties.get(i));
            }
            playerDicePrompt = scanner.nextInt();
        }
        Property selectedProperty = targetPlayer.getPlayerPropertiesList().get(playerDicePrompt);
        ArrayList<Property> selectedPropertySide = null;
        ArrayList[] sides = new ArrayList[]{
                board.sideAProperties, board.sideBProperties, board.sideCProperties, board.sideDProperties
        };
        for (ArrayList side : sides) {
            if (side.contains(selectedProperty)) {
                selectedPropertySide = side;
                break;
            }
        }
        if (selectedPropertySide == null) {
            System.out.println("ERROR : SELECTED PROPERTY SIDE IS NULL");
        }else{
            for (Property property : selectedPropertySide){
                property.decreaseRentLevel(1);
                System.out.println(property.getPropertyName() + "'s rent has been decreased by 1");
            }
        }
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
    // HOUSE PARTY, IT'S A BOY
    public static void increaseRentLevelDropForNeighbours(Player targetPlayer, MonopolyBoard board){
        System.out.println("Please input a property to have its rent level increased to 1 along with it's color set");
        System.out.println(targetPlayer.getPlayerName() + "\'s Properties:");
        ArrayList< Property > properties = targetPlayer.getPlayerPropertiesList();
        for (int i = 0; i < properties.size(); i++) {
            int index = i + 1;
            System.out.println("[" + index + "] - " + properties.get(i));
        }
        int propertySize = properties.size();
        Scanner scanner = new Scanner(System.in);
        int playerDicePrompt = scanner.nextInt();
        while (playerDicePrompt > propertySize || playerDicePrompt < 1){
            System.out.println(
                    "Wrong input, Please input a property to have its rent level reset to 1 along with it's color set"
            );
            for (int i = 0; i < properties.size(); i++) {
                int index = i + 1;
                System.out.println("[" + index + "] - " + properties.get(i));
            }
            playerDicePrompt = scanner.nextInt();
        }
        Property selectedProperty = targetPlayer.getPlayerPropertiesList().get(playerDicePrompt);
        ArrayList <Property> selectedPropertySide = null;
        ArrayList[] sides = new ArrayList[]{
                board.sideAProperties, board.sideBProperties, board.sideCProperties, board.sideDProperties
        };
        for (ArrayList side : sides){
            if (side.contains(selectedProperty)){
                selectedPropertySide = side;
                break;
            }
        }
        if (selectedPropertySide == null){
            System.out.println("ERROR : SELECTED PROPERTY SIDE IS NULL");
        }else{
            for (Property property : selectedPropertySide){
                if (property == selectedProperty){
                    property.setCurrentRentLevel(1);
                    System.out.println(property.getPropertyName() + "'s rent has been increased by 1");
                }else{
                    property.decreaseRentLevel(1);
                    System.out.println(property.getPropertyName() + "'s rent has been decreased by 1");
                }
            }
        }
    }
    // ON THE RUN
    public static void payLevelOneNextTwoTurns(Player [] playerList){

    }
}
