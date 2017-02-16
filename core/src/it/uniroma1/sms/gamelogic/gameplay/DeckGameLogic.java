/*************************************************************************************
 * Copyright (c) 2014 - 2014, Ricciardino (Riccardo Petricca)
 * 
 * This software is licensed under CC-BY-NC-SA license,
 * feel free to see my personal blog site at http://www.petriccarcc.com , 
 * or to fork my project at https://github.com/Ricciardino
 * see the LICENSE file or http://creativecommons.org/licenses/by-nc-sa/4.0/
 * for further informations and details.
 *
 * See also Libgdx at : https://github.com/libGDX/libGDX
*************************************************************************************/
package it.uniroma1.sms.gamelogic.gameplay;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * This is the Deck game logic class, it calculates all the basic things that deck can offer in the gameplay.
 * 
 * @author  Ricciardino
 * @version 0.1, 5 August 2014
 * @see com.badlogic.gdx.Gdx
 * 
 */
public class DeckGameLogic
{
	 /** The Deck of the class, no order here  @see #getDeck() @see #getModdableDeck() */
	private ArrayList<Card> deck;
	 
	/** The Deck of the class, ordinated.  @see #getDeck() @see #getModdableDeck() */
	private LinkedList<Card> ordinatedDeck;
	
	/** Random index integer, needed to randomize and reset the deck. @see #resetGame() */
	private int randomSortIndex;
	
	/**  Main path folder as string of the card images */
	private String cardsTexteturePath = "cards/"; 
	
	/**
	 * Basic constructor of DeckGameLogic class, it inizializes all fields as new, {@link #resetGame()} is called;
	 * @see #resetGame()
	 */
	public DeckGameLogic() 
	{
		ordinatedDeck = new LinkedList<Card>(Arrays.asList(Card.values()));
		deck = new ArrayList<Card>();
		resetGame();
	}
	
	/**
	 * This method inizializes all fields just as constructor, to start a new game.
	 */
	public void resetGame() 
	{
		shuffle();
		randomSortIndex = -1;
	}
	
	/**
	 * PRIVATE method that is pretty much similar to Collections.Shuffle (not included in GWT)
	 * @param list The list (ArryList<Card>) of cards we need to shuffle.
	 */
	private void shuffle()
	{
		ArrayList<Card> deckToShuffle = new ArrayList<Card>(ordinatedDeck);
		ArrayList<Card> shuffledDeck = new ArrayList<Card>();
		
		for (int i = 0; i < ordinatedDeck.size(); i++)
		{
			int shuffleCounter = (int) (deckToShuffle.size()*Math.random());
			shuffledDeck.add(deckToShuffle.get(shuffleCounter));
			deckToShuffle.remove(shuffleCounter);
		}
		
		deck = new ArrayList<Card>(shuffledDeck);
	}
	
	/**
	 * This method is a getter for the ArrayList of cards 
	 * @return The entire deck, ordinated, but copied.
	 */
	public LinkedList<Card> getDeck() { return new LinkedList<Card>(deck); }
	
	/**
	 * This method is like the basic getter of the deck, but with moddable deck.
	 * @return	BE AWARE: The deck returned here is moddable, so every changes will take also place in the game.
	 */
	public LinkedList<Card> getModdableDeck() { return new LinkedList<Card>(deck); }
	
	/**
	 * This method shuffles the deck and gives back a random card.
	 * @return A random Card card.
	 */
	public Card pickACard()
	{
		if (deck.size() == 0)	return null;
		++randomSortIndex;
		return deck.get(randomSortIndex);
	}
	
	/**
	 * This method is similar to pickACard but it returns a String of the texture name.
	 * @return The texture name as String.
	 */
	public String getCardTexture(Card card)
	{
		String a = card+"";
		a = a.substring(1) + a.substring(0, 1);
		return cardsTexteturePath+a+".png";
	}	

	/**
	 * This is probably the most important method of the class, this is the kernel of the entire TriPeaks gameplay, it calculates where you will be able to put the chosen card. 
	 * @param chosenCard A random card.
	 * @return All the attachable cards to the given one (as an ArrayList<Card>)
	 */
	public LinkedList<Card> getRightChoices(Card chosenCard)
	{
		int ord = chosenCard.ordinal();
		LinkedList<Card> c = new LinkedList<Card>();
		Integer[] l = new Integer[]{ord-1,ord+1,ord-12,ord-14,ord-25,ord-27,ord-38,ord-40};
		
		for (int a : l)  
		{ 
			if 		(a>51)	c.add(ordinatedDeck.get(a-52));
			else if (a<0)  	c.add(ordinatedDeck.get(52+a));
			else 			c.add(ordinatedDeck.get(a));
		}
		return new LinkedList<Card>(c);
	}
	
	/**
	 * This is the basic setter of the path where the cards images are.
	 * @param cardsTexteturePath The path as string.
	 */
	public void setCardsTexturePath(String cardsTexteturePath)
	{	
		this.cardsTexteturePath = cardsTexteturePath;
	}
}

