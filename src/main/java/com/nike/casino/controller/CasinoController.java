package com.nike.casino.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nike.casino.service.CasinoService;

@RestController
@RequestMapping("/api")
public class CasinoController {
	static final Logger logger = Logger.getLogger(CasinoController.class);

	@Autowired
	CasinoService casinoService;

	@RequestMapping(value = "/deck/{deckName}", method = RequestMethod.PUT)
	public ResponseEntity<String> createDeck(@PathVariable("deckName") String deckName) {
		if (StringUtils.isEmpty(deckName)) {
			return new ResponseEntity<>("DeckName must be provided ", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			try {
				return new ResponseEntity<>(casinoService.createDeck(deckName), HttpStatus.OK);
			} catch (JsonProcessingException e) {
				logger.error(e.getLocalizedMessage(), e);
				return new ResponseEntity<>("Error occured while creating the deck", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@RequestMapping(value = "/deck/{deckName}", method = RequestMethod.GET)
	public ResponseEntity<String> getDeck(@PathVariable("deckName") String deckName) {
		if (StringUtils.isEmpty(deckName)) {
			return new ResponseEntity<>("DeckName must be provided ", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			try {
				return new ResponseEntity<>(casinoService.getDeck(deckName), HttpStatus.OK);
			} catch (JsonProcessingException e) {
				logger.error(e.getLocalizedMessage(), e);
				return new ResponseEntity<>("Error occured while fetching the deck", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

	}

	@RequestMapping(value = "/decks", method = RequestMethod.GET)
	public ResponseEntity<String> getAllDecks() {
		try {
			return new ResponseEntity<>(casinoService.getAllDecks(), HttpStatus.OK);
		} catch (JsonProcessingException e) {
			logger.error(e.getLocalizedMessage(), e);
			return new ResponseEntity<>("Error occured while fetching the decks", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/deck/{deckName}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteDeck(@PathVariable("deckName") String deckName) {
		if (StringUtils.isEmpty(deckName)) {
			return new ResponseEntity<>("DeckName must be provided ", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			String deletedDeckName = casinoService.deleteDeck(deckName);
			if (deletedDeckName == deckName) {
				return new ResponseEntity<>(deletedDeckName + " Deleted Successfully", HttpStatus.OK);
			} else {
				logger.error("Error occured while deleting the deck " + " deletedDeckName : " + deletedDeckName
						+ " deckName : " + deckName);
				return new ResponseEntity<>("Error occured while deleting the deck", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@RequestMapping(value = "/deck/shuffle/{deckName}", method = RequestMethod.POST)
	public ResponseEntity<String> shuffleDeck(@PathVariable("deckName") String deckName) {
		if (StringUtils.isEmpty(deckName)) {
			return new ResponseEntity<>("DeckName must be provided ", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			try {
				String shuffledDeck = casinoService.shuffleDeck(deckName);
				if(null != shuffledDeck){
					return new ResponseEntity<>(shuffledDeck, HttpStatus.OK);
				} else {
					return new ResponseEntity<>("No information Found for Deck :"+deckName, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} catch (JsonProcessingException e) {
				logger.error(e.getLocalizedMessage(), e);
				return new ResponseEntity<>("Error occured while fetching the deck", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
}
