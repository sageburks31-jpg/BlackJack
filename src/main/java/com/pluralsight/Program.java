package com.pluralsight;

import java.util.*;
import static java.lang.Thread.*;

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

    public static void main(String[] args) throws InterruptedException {
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

        print("which challenger are you going to play as? player 1 or 2 -> ",false);
        userPlayerId = Integer.parseInt(scanner.nextLine().trim()) - 1;
        int cpuTurn;

        if (userPlayerId == 1) {cpuTurn = 0;}
        else cpuTurn = 1;

        sleep(500);
        print(playerList.get(cpuTurn).getPlayerName() + " is set as dealer");
        sleep(1500);
        displayPlayerHands(cpuTurn);
        sleep(500);

        do {
            if (!playerList.get(cpuTurn).isFold() || playerList.get(cpuTurn).getValue() < 22) {
                print(playerList.get(cpuTurn).getPlayerName());
                sleep(800);
                runCpuHand(cpuTurn, deck);
            }
            print(" ");
            if (!playerList.get(cpuTurn).isFold() || playerList.get(cpuTurn).getValue() < 22) {
                print(playerList.get(userPlayerId).getPlayerName());
                sleep(800);
                runUserHand(deck);
            }

        } while ((!playerList.get(cpuTurn).isFold() && !playerList.get(userPlayerId).isFold()) || (playerList.get(cpuTurn).getValue() > 21 && playerList.get(cpuTurn).getValue() > 21));

        String[] winnerAndLoserList = {checkPlayerHand(playerList.get(userPlayerId).getValue()), checkPlayerHand(playerList.get(cpuTurn).getValue())};

        if (winnerAndLoserList[0].equals(winnerAndLoserList[1])) {
            if (winnerAndLoserList[0].equals("bust")) {
                print("double bust");
            } else {
                if (playerList.get(userPlayerId).getValue() > playerList.get(cpuTurn).getValue()){
                    print(playerList.get(userPlayerId).getPlayerName() + " " + winnerAndLoserList[0]);
                    print(playerList.get(cpuTurn).getPlayerName() + " lost");

                } else if (playerList.get(userPlayerId).getValue() < playerList.get(cpuTurn).getValue()) {
                    print(playerList.get(cpuTurn).getPlayerName() + " " + winnerAndLoserList[1]);
                    print(playerList.get(userPlayerId).getPlayerName() + " lost");

                } else print("tie");
            }
        }
        else {
            print(playerList.get(userPlayerId).getPlayerName() + " " + winnerAndLoserList[0]);
            print(playerList.get(cpuTurn).getPlayerName() + " " + winnerAndLoserList[1]);
        }

        displayPlayerHands(cpuTurn);
        scanner.close();
    }

    private static void displayPlayerHands(int cpuTurn) {
        print("your hand has, power: " + playerList.get(userPlayerId).getValue() +
                " | cards: " + playerList.get(userPlayerId).getCardNames());
        print("Cpus hand has, power: " + playerList.get(cpuTurn).getValue() +
                " | cards: " + playerList.get(cpuTurn).getCardNames());
    }

    private static String checkPlayerHand(int prompt) {
        if (prompt <= 21) return "win";
        else return "bust";
    }

    private static void runCpuHand(int turnRng, Deck deck) {
        playerList.get(turnRng).setFold(true);
        int handPower = playerList.get(turnRng).getValue();
        int playDecision = random.nextInt(0,3);
        if (handPower < 15) {
            playerList.get(turnRng).deal(deck.deal());
            playerList.get(turnRng).setFold(false);
        }
        else {
            if (handPower < 20) {

                if (playDecision == 0) {
                    playerList.get(turnRng).deal(deck.deal());
                    playerList.get(turnRng).setFold(false);
                } else if (playDecision == 2) playerList.get(turnRng).setFold(false);

            } else if (handPower == 21) {
                playerList.get(turnRng).setFold(false);
                playDecision = 1;
            }
        }
        if (!playerList.get(turnRng).isFold()) {
            if (playDecision == 0) print(playerList.get(turnRng).getPlayerName() + " chose to hit");
            else print(playerList.get(turnRng).getPlayerName() + " chose to stay");
        } else {
            print(playerList.get(turnRng).getPlayerName() + " chose to fold");
        }
    }

    private static void runUserHand(Deck deck) {
        playerList.get(userPlayerId).setFold(true);
        print("cards in hand: " + playerList.get(userPlayerId).getCardNames() + "\n" +
                "enter d to deal\n" +
                "enter s to stay\n"+
                "enter f to fold");
        String answer;

        do {
            print("enter here -> ",false);
            answer = scanner.nextLine().toLowerCase().trim();

            if (answer.equals("d")) {
                playerList.get(userPlayerId).deal(deck.deal());
                playerList.get(userPlayerId).setFold(false);

            } else if (answer.equals("s")) {playerList.get(userPlayerId).setFold(false);}
            else if (!answer.equals("f")) {print("invalid command");}

        } while (!answer.equals("d") && !answer.equals("f") && !answer.equals("s"));

        if (!playerList.get(userPlayerId).isFold()) {
            if (answer.equals("d")) print(playerList.get(userPlayerId).getPlayerName() + " chose to hit");
            else print(playerList.get(userPlayerId).getPlayerName() + " chose to stay");
        } else {
            print(playerList.get(userPlayerId).getPlayerName() + " chose to fold");
        }
    }

    private static void buildPlayerToList(String name) {
        Hand hand = new Hand(name);
        playerList.add(hand);
    }
}
