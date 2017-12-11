package com.nike.casino.model;

public class Card {

	private String cardVal;
	private String suit;
	private int cardPriority;
	private int suitPriority;

	public Card(String cardVal, String suit, int cardPriority, int suitPriority) {
		this.cardVal = cardVal;
		this.suit = suit;
		this.cardPriority = cardPriority;
		this.suitPriority = suitPriority;
	}

	public String getCardVal() {
		return cardVal;
	}

	public void setCardVal(String cardVal) {
		this.cardVal = cardVal;
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public int getCardPriority() {
		return cardPriority;
	}

	public void setCardPriority(int cardPriority) {
		this.cardPriority = cardPriority;
	}

	public int getSuitPriority() {
		return suitPriority;
	}

	public void setSuitPriority(int suitPriority) {
		this.suitPriority = suitPriority;
	}
}
