package com.pluralsight;

import java.util.ArrayList;

public class Hand {
    private final String playerName;
    private final ArrayList<Card> cards;

    public Hand(String playerName) {
        cards = new ArrayList<>();
        this.playerName = playerName;
    }

    public void deal(Card card) {cards.add(card);}

    public int getSize() {return cards.size();}

    public String getPlayerName() {return playerName;}

    public int getValue() {
        int value = 0;

        for (Card card : cards) {
            card.flip(); // turn the card over to see the value
            value = value + card.getPointValue();
            card.flip(); // hide the card again
        } return value;
    }
}