package com.nike.casino.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nike.casino.model.Card;
import com.nike.casino.model.Deck;

@Service
public class CasinoService {

	ObjectMapper mapper = new ObjectMapper();
	HashMap<String,Deck<Card>> decksMap = new HashMap<>(0);
	
	public List<Card> setupDeck() {
		List<Card> llist = new LinkedList<>();
		String[] faceCards = { "J", "Q", "K", "A" };
		String[] suits = { "spades", "clubs", "diamonds", "hearts" };

		for (int i = 2; i <= 10; i++) {
			for (int j = 1; j <= 4; j++) {
				llist.add(new Card(Integer.toString(i), suits[j - 1], j, i));
			}
		}
		for (int k = 1; k <= 4; k++) {
			for (int l = 1; l <= 4; l++) {
				llist.add(new Card(faceCards[k - 1], suits[l - 1], l, k + 10));
			}
		}
		return llist;
	}

	public String createDeck(String deckName) throws JsonProcessingException {
		//TODO fetch the 
		String shuffleAlgorithm = "quarterShuffle";
		Deck<Card> deck = new Deck<>(setupDeck(),shuffleAlgorithm);
		deck.setDeckName(deckName);
		decksMap.put(deckName, deck);
		return mapper.writeValueAsString(deck);
	}

	public String getDeck(String deckName) throws JsonProcessingException {
		Deck<Card> deck = decksMap.get(deckName);
		if(deck == null){
			return null;
		} else {
			return mapper.writeValueAsString(deck);
		}
	}
	
	public String getAllDecks() throws JsonProcessingException {
		List<Deck<Card>> decks = new ArrayList<>(decksMap.values());
		return mapper.writeValueAsString(decks);
	}

	public String deleteDeck(String deckName) {
		if(decksMap.containsKey(deckName)){
			decksMap.remove(deckName);
			return deckName;
		} else {
			return null;
		}
	}

	public String shuffleDeck(String deckName) throws JsonProcessingException {
		Deck<Card> deckToShuffle = decksMap.get(deckName);
		if(null != deckToShuffle){
			return mapper.writeValueAsString(deckToShuffle.shuffleDeck());
		} else {
			return null;
		}
	}
	
}
