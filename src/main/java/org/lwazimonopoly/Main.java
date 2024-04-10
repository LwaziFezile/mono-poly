package org.lwazimonopoly;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter No. of players in the game below [No. of players 2-4]: ");
        Scanner scanner = new Scanner(System.in);
        int noOfPlayers = scanner.nextInt();
        while (noOfPlayers > 4 || noOfPlayers < 2){
            System.out.println("Monopoly can have at min. 2 Players, and at max. 4 Players, Try Again: ");
            noOfPlayers = scanner.nextInt();
        }
        CardMachine cardMachine = new CardMachine(noOfPlayers);

//        System.out.println(Arrays.toString(player1.rollDice()) + " Player Properties: " + player1.getPlayerProperties());
        }
    }

    /*
    * Player player1 = new Player("Helicopter");
        MonopolyBoard monopolyBoard = new MonopolyBoard();
        monopolyBoard.mayfair.setPropertyOwner(player1);
        monopolyBoard.mayfair.setPropertyOwner(player1);
        player1.setPlayerProperties(monopolyBoard.mayfair);
        System.out.println(monopolyBoard.mayfair.getCurrentRentLevel());*/