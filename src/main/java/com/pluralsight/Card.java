package com.pluralsight;

public class Card {
    private final String suit;
    private final String value;
    private boolean faceUp;

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
        this.faceUp = false;
    }

    public String getSuit() {
        // only return the suit if the card is face up
        if (faceUp) {return suit;}
        else {return "#";}
    }

    public String getValue() {
        // only return the value if the card is face up
        if (faceUp) {return value;}
        else {return "#";}
    }

    public int getPointValue() {
        if (faceUp) {
         return switch (value) {
             case "A" -> 11;
             case "K", "Q", "J" -> 10;
             default -> Integer.parseInt(value);
         };
        } return 0;
    }

    //public boolean isFaceUp() {return faceUp;}

    public void flip() {faceUp = !faceUp;}
}
