package org.lwazimonopoly;

import java.util.ArrayList;

public class SpecialEventCards {

    // deckShuffled is used to determine whether the deck has been shuffled at the beginning of the game or not
    static boolean deckShuffled = false;

    // specialEventCardNames contains the Banking Event Card names, used for shuffleDeck()
    static String[] specialEventCardNames = new String[]{
            "STARGAZING", "GRAND DESIGNS", "CRIME DOWN", "WHAT A RIDE",
            "ON THE MAP", "ON THE RUN", "TORNADO ALLEY", "DEMOLISHED",
            "\'TIS THE SEASON", "PONG! WHAT A STINKER", "HOUSE PARTY",
            "PICK YOUR OWN", "LOVE IS IN THE AIR", "HIGHWAY TAX", "DEAL OF THE WEEK",
            "STOP THE PRESSES", "BOOM TOWN", "HAUNTED HOUSE", "IN THE MONEY", "TOTAL GRIDLOCK",
            "WIBBLE WOBBLE"
    };
    public SpecialEventCards() {

    }
    // Stores the Shuffled Deck
    static ArrayList<String> specialEventCards_shuffled = new ArrayList<>();

    // Randomly shuffles the deck, only gets called once
    public static boolean shuffleDeck(){
        if (!deckShuffled){
            ArrayList <String> arrayList = new ArrayList<>();
            for (String i : specialEventCardNames){
                arrayList.add(i);
            }
            // Not efficient, randomIndex could be a value that has already been added to special event cards
            int index = 0;
            while (index < arrayList.size()){
                int randomIndex = (int) (Math.random() * arrayList.size());
                if ( !specialEventCards_shuffled.contains(arrayList.get(randomIndex)) ){
                    specialEventCards_shuffled.add(arrayList.get(randomIndex));
                    index++;
                }
            }
            deckShuffled = true;
            return true;
        }else{
            return deckShuffled;
        }
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
