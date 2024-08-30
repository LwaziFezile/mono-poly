package org.lwazimonopoly;


import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter No. of players in the game below [No. of players 2-4]: ");

        int noOfPlayers = scanner.nextInt();
        while (noOfPlayers > 4 || noOfPlayers < 2){
            System.out.println("Monopoly can have at min. 2 Players, and at max. 4 Players, Try Again: ");
            noOfPlayers = scanner.nextInt();
        }
        CardMachine cardMachine = new CardMachine(noOfPlayers);

        MonopolyBoard gameBoard = new MonopolyBoard();
        boolean shuffleDeck = SpecialEventCards.shuffleDeck();
        System.out.println(gameBoard.oldKentRoad.getCurrentRentLevel());

        }
    }