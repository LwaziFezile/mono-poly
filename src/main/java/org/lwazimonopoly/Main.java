package org.lwazimonopoly;


import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Monopoly Game!");
        System.out.println("Enter No. of players in the game below [No. of players 2-4]: ");

        int noOfPlayers = scanner.nextInt();
        while (noOfPlayers > 4 || noOfPlayers < 2){
            System.out.println("Monopoly can have at min. 2 Players, and at max. 4 Players, Try Again: ");
            noOfPlayers = scanner.nextInt();
        }
        CardMachine cardMachine = new CardMachine(noOfPlayers);

        MonopolyBoard gameBoard = new MonopolyBoard();
        boolean shuffleDeck = SpecialEventCards.shuffleDeck();

        while (cardMachine.checkPlayerCredit()){
            for(Player player : cardMachine.getPlayers()){
                int playerInput = consoleDisplay(player);
                switch (playerInput){
                    case 1:
                        gameBoard.movePlayerOnBoard(player);
                }
            }
        }
        }

        public static int consoleDisplay(Player player){
            System.out.println("Options (" + player.getPlayerName() + "):");
            System.out.println("1. Roll Dice");
            System.out.println("2. Check Player Credit");
            System.out.println("3. Buy Property");
            System.out.println("4. Check Property Information");
            System.out.println("5. Terminate Game");
            int input = scanner.nextInt();
            while (!new ArrayList<>(Arrays.asList(1,2,3,4,5)).contains(input)){
                System.out.println("!!!Invalid Option!!!");
                System.out.println("1. Roll Dice");
                System.out.println("2. Check Player Credit");
                System.out.println("3. Buy Property");
                System.out.println("4. Check Property Information");
                System.out.println("5. Terminate Game");
            }
            return input;
        }
    }