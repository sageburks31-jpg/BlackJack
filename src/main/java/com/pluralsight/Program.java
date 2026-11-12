package com.pluralsight;

import java.util.*;

public class Program {
    public static ArrayList<Hand> playerList = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();
    public static int userPlayerId;

    // helper print methods
    private static void print(String text) {System.out.println(text);}
    private static void print(String text, boolean pushNextLine) {
        if (pushNextLine) {System.out.println(text);}
        else {System.out.print(text);}
    }

    public static void main(String[] args) {
        print(" ",true);

        for (int i = 0; i <= 1; i++) {
            print("Please enter name for player " + (i + 1) + " here -> ",false);
            buildPlayerToList(scanner.nextLine().trim());
        }

        Deck deck = new Deck();
        deck.shuffle();

        print(String.valueOf(deck.getSize()));
        for (int i = 0; i <= 1; i++) {
            playerList.get(i).deal(deck.deal());
            playerList.get(i).deal(deck.deal());
            print(playerList.get(i).getPlayerName() + " was dealt 2 cards");
        }

        print("Who is going first player 1 or 2 -> ",false);
        userPlayerId = Integer.parseInt(scanner.nextLine().trim()) - 1;
        print("your hand, power: " + playerList.get(userPlayerId).getValue() +
                " | cards: " + playerList.get(userPlayerId).getCardNames());

        int turnRng = random.nextInt(0,1);
        int cpuTurn = turnRng;
        print("coin flipped " + playerList.get(turnRng).getPlayerName() + " is going first");

        do {
            runCpuHand(cpuTurn, deck);
            turnRng = userPlayerId;

            print(playerList.get(userPlayerId).getPlayerName());
            runUserHand(deck);
            turnRng -= 1;
        } while (playerList.get(cpuTurn).isFold() == playerList.get(userPlayerId).isFold());

        if (playerList.get(cpuTurn).getValue() == playerList.get(userPlayerId).getValue()) {print("tie");}
        else if (playerList.get(cpuTurn).getValue() > playerList.get(userPlayerId).getValue()) {
            print(playerList.get(cpuTurn).getPlayerName()+ " wins");
        } else if (playerList.get(cpuTurn).getValue() < playerList.get(userPlayerId).getValue()) {
            print(playerList.get(userPlayerId).getPlayerName()+ " wins");
        }

        scanner.close();
    }

    private static void runCpuHand(int turnRng, Deck deck) {
        playerList.get(turnRng).setFold(true);
        int handPower = playerList.get(turnRng).getValue();
        int playDecision = random.nextInt(0,1);
        if (handPower < 10) {
            print("deal");
            playerList.get(turnRng).deal(deck.deal());
            playerList.get(turnRng).setFold(false);
        }
        else {
            if (handPower < 20) {
                if (playDecision == 0) {
                    print("deal");
                    playerList.get(turnRng).deal(deck.deal());
                    playerList.get(turnRng).setFold(false);
                }
            }
        }
        if (!playerList.get(turnRng).isFold()) {
            print(playerList.get(turnRng).getPlayerName() + " chose to hit");
        } else {
            print(playerList.get(turnRng).getPlayerName() + " chose to fold");
        }
    }

    private static void runUserHand(Deck deck) {
        playerList.get(userPlayerId).setFold(true);
        print("cards in hand " + playerList.get(userPlayerId).getCardNames() + "\n" +
                "enter d to deal\n" +
                "enter f to fold");
        String answer;

        do {
            print("enter here -> ",false);
            answer = scanner.nextLine().toLowerCase().trim();

            if (answer.equals("d")) {
                playerList.get(userPlayerId).deal(deck.deal());
                playerList.get(userPlayerId).setFold(false);

            } else if (!answer.equals("f")) {print("invalid command");}

        } while (!answer.equals("d") && !answer.equals("f"));

        if (!playerList.get(userPlayerId).isFold()) {
            print(playerList.get(userPlayerId).getPlayerName() + " chose to hit");

        } else {
            print(playerList.get(userPlayerId).getPlayerName() + " chose to fold");
        }
    }

    private static void buildPlayerToList(String name) {
        Hand hand = new Hand(name);
        playerList.add(hand);
    }
}
