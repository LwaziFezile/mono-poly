package org.lwazimonopoly;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Enter No. of players in the game below [No. of players 2-4]: ");
//        Scanner scanner = new Scanner(System.in);
//        int noOfPlayers = scanner.nextInt();
//        while (noOfPlayers > 4 || noOfPlayers < 2){
//            System.out.println("Monopoly can have at min. 2 Players, and at max. 4 Players, Try Again: ");
//            noOfPlayers = scanner.nextInt();
//        }
//        CardMachine cardMachine = new CardMachine(noOfPlayers);

        MonopolyBoard gameBoard = new MonopolyBoard();
        System.out.println(gameBoard.oldKentRoad.getCurrentRentLevel());

        }
    }