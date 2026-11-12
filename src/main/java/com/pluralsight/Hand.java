package com.pluralsight;

import java.util.ArrayList;

public class Hand {
    private final String playerName;
    private boolean fold;

    private final ArrayList<Card> cards;

    public Hand(String playerName) {
        cards = new ArrayList<>();
        this.playerName = playerName;
        this.fold = true;
    }
    public void deal(Card card) {cards.add(card);}

    public boolean isFold() {return fold;}
    public void setFold(boolean fold) {this.fold = fold;}

    public int getSize() {return cards.size();}
    public int getValue() {
        int value = 0;

        for (Card card : cards) {
            card.flip(); // turn the card over to see the value
            value = value + card.getPointValue();
            card.flip(); // hide the card again
        } return value;
    }

    public String getPlayerName() {return playerName;}
    public String getCardNames(){
        StringBuilder name = new StringBuilder();

        for (Card card : cards) {
            card.flip(); // turn the card over to see the value
            name.append(card.getValue()).append(" ");
            card.flip(); // hide the card again
        } return name.toString();
    }

    }