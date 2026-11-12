package com.pluralsight;

import java.util.*;

public class Program {
    public static ArrayList<Hand> playerList = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);

    // helper print methods
    private static void print(String text) {System.out.println(text);}
    private static void print(String text, boolean pushNextLine) {
        if (pushNextLine) {System.out.println(text);}
        else {System.out.print(text);}
    }

    public static void main(String[] args) {
        print(" ",true);
        String answer;
        for (int i = 0; i <= 1 ; i++) {
            print("Please enter name for player " +(i+1)+ " here -> ",false);
            answer = scanner.nextLine().trim();
            buildPlayerToList(answer);
        }

        Deck deck = new Deck();
        deck.shuffle();
        print(String.valueOf(deck.getSize()));
        print(""+playerList);
    }

    private static void buildPlayerToList(String name) {
        Hand hand = new Hand(name);
        playerList.add(hand);
    }
}
