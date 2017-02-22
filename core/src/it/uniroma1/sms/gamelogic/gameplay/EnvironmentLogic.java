/*************************************************************************************
 * Copyright (c) 2014 - 2017, Ricciardino (Riccardo Petricca)
 * 
 * This software is licensed under CC-BY-NC-SA license,
 * feel free to see my personal blog site at http://www.riccardopetricca.com , 
 * or to fork my project at https://github.com/petriccarcc
 * see the LICENSE file or http://creativecommons.org/licenses/by-nc-sa/4.0/
 * for further informations and details.
 *
 * See also Libgdx at : https://github.com/libGDX/libGDX
*************************************************************************************/
package it.uniroma1.sms.gamelogic.gameplay;

import it.uniroma1.sms.decorator.CardDecorator;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;

/**
 * 
 * Main game logic class, calculates the major part of the starting gamplay.
 * 
 * @author  Ricciardino
 * @version 0.1, 5 August 2014
 *
 */
public class EnvironmentLogic
{
	/** The screen width. */
	private int screenWidth;
	
	/** The screen height. */
	private int screenHeight;
	
	/** The basic x distance for the initialized images. */
	private float baseXDistance;
	
	/** The base y distance for the initialized images. */
	private float baseYDistance;

	/** The cards width. */
	private int cardWidth;
	
	/** The cards height. */
	private int cardHeight;
	
	/** The column (Xs) coordinates. */
	private LinkedList<Float> columnsXs;
	
	/** The column (Ys) coordinates. */
	private LinkedList<Float> columnsYs;
	
	/** The list of the cards on table (top side of the screen). */
	private LinkedList<CardDecorator> cardOnTableDecoratorList;
	
	/** The list of the cards on hand (bottom side of the screen). */
	private LinkedList<CardDecorator> cardOnHandDecoratorList;

	/** The main deck (to play with). */
	private LinkedList<Card> deck;
	
	/**
	 * This is the basic constructor of the class, it initializes the fixed distances on the axis between cards.
	 *
	 * @param screenWidth Is the screen width as Integer
	 * @param sceenHeight Is the screen height as Integer
	 * @param deck The deck of cards (as LinkedList<Card>)
	 */
	public EnvironmentLogic( int screenWidth, int sceenHeight, LinkedList<Card> deck)
	{
		this.deck = deck;
		setWidth(screenWidth);
		setHeight(sceenHeight);
		setXDistance(screenWidth/8);
		setYDistance(screenHeight/6);
		
		columnsXs = new LinkedList<Float>();
		columnsYs = new LinkedList<Float>();
		cardOnTableDecoratorList = new LinkedList<CardDecorator>();
		cardOnHandDecoratorList = new LinkedList<CardDecorator>();  
		
		cardWidth = screenWidth/16;
		cardHeight = screenHeight/7;
		buildSingleColumnCoordinates(screenWidth/2, 				  	columnsXs, 	columnsYs, false);
		buildSingleColumnCoordinates(screenWidth/2 - screenWidth/4, 	columnsXs,	columnsYs, false);
		buildSingleColumnCoordinates(screenWidth/2 + screenWidth/4, 	columnsXs, 	columnsYs, true );
	}

	/**
	 * This PRIVATE method creates a single column, just by the X coordinate of the first (from top) card and the HashMap which we are saving on.
	 *
	 * @param firstCardX X coordinate of the first card, from top.
	 * @param columnX the column x
	 * @param columnY the column y
	 * @param bool the bool
	 * @return HashMap with two floats, X and Y coordinates of each element (card) of the column. [Same as @param column]
	 */
	private void buildSingleColumnCoordinates(float firstCardX, LinkedList<Float> columnX , LinkedList<Float> columnY, boolean bool)
	{
		Float localX = firstCardX-cardWidth/2;
		Float localY = screenHeight/5 * 4f;
			
		// First row
		columnsXs.add(localX);
		columnsYs.add(localY);
		
		// Second row
		columnsXs.add(localX - cardWidth );
		columnsYs.add(localY -= cardHeight+Gdx.graphics.getHeight()/38);
		
		columnsXs.add(localX+cardWidth );
		columnsYs.add(localY );
		
		// Third row
		columnsXs.add(localX +1 );
		columnsYs.add(localY -= cardHeight+Gdx.graphics.getHeight()/38 );
		
		
		columnsXs.add(localX - (cardWidth+8) );
		columnsYs.add(localY );
		
		columnsXs.add(localX + (cardWidth+8));
		columnsYs.add(localY );
		
		if (bool) 
		{
			localX = screenWidth/8f - cardWidth/2.3f;
			columnsXs.add(localX + screenWidth/300f);
			columnsYs.add(localY -= cardHeight+Gdx.graphics.getHeight()/38 );
			
			for(int j = 0; j<9 ; j++)
			{
				columnsXs.add(localX+= cardWidth+ Gdx.graphics.getHeight()/30 );
				columnsYs.add(localY );
			}
		}
	}
	
	/**
	 * Gets the list of the card decorators on table.
	 * @return the list of the card decorators on table
	 */
	public LinkedList<CardDecorator> getCardsOnTableDecorators()
	{
		for (int i = 0 ; i<columnsXs.size()   ; i++)
		{
			cardOnTableDecoratorList.add( new CardDecorator(deck.get(i), columnsXs.get(i), columnsYs.get(i), cardWidth, cardHeight) ) ;
			if (i<columnsXs.size()-10) cardOnTableDecoratorList.get(i).coverCard();
		}
		return cardOnTableDecoratorList;
	}
	
	/**
	 * Gets the list of the card decorators on hand.
	 * @return the list of the card decorators on hand
	 */
	public LinkedList<CardDecorator> getCardsOnHandDecorators()
	{
		for (int i = columnsXs.size(); i<deck.size()-1; i++)
		{ 
			CardDecorator deco = new CardDecorator(deck.get(i),Gdx.graphics.getWidth()/2.1f - cardWidth, Gdx.graphics.getHeight()/8, cardWidth, cardHeight );
			cardOnHandDecoratorList.add(deco);
		}
		cardOnHandDecoratorList.add(new CardDecorator(deck.get(deck.size()-1), Gdx.graphics.getWidth()/1.7f - cardWidth, Gdx.graphics.getHeight()/8, cardWidth, cardHeight ));
		return cardOnHandDecoratorList;
	}
	
	/**
	 * Basic getter for the HashMap of the coordinates of each card that must be displayed on the game field.
	 * @return The HashMap with the coordinates of each card.
	 */
	public LinkedList<Float> getDeckColumnsX() { return new LinkedList<Float>(columnsXs); }
	
	/**
	 * Basic getter for the HashMap of the coordinates of each card that must be displayed on the game field.
	 * @return The HashMap with the coordinates of each card.
	 */
	public LinkedList<Float> getDeckColumnsY() { return new LinkedList<Float>(columnsYs); }
	
	/**
	 * Basic getter of the deck used.
	 *
	 * @return The screen weight in pixels (integer)
	 */
	public LinkedList<Card> getDeck() { return deck; }
	
	/**
	 * Basic setter for the gameplay screen width.
	 *
	 * @param screenWidth The screen width in pixels (integer)
	 */
	public void setWidth( int screenWidth ){ this.screenWidth = screenWidth; }
	
	/**
	 * Basic setter for the gameplay screen height.
	 *
	 * @param screenHeight The screen height in pixels (integer)
	 */
	public void setHeight( int screenHeight){ this.screenHeight = screenHeight; }
	
	/**
	 * Basic getter of the gameplay screen width.
	 *
	 * @return The screen weight in pixels (integer)
	 */
	public int getWidth() { return screenWidth; }
	
	/**
	 * Basic getter of the gameplay screen height.
	 *
	 * @return The screen height in pixels (integer)
	 */
	public int getHeight() { return screenHeight; }
	
	/**
	 * This is the basic setter of the X coordinate distances between cards.
	 * @param XDistance is the distance you want to set between each card.
	 */
	public void setXDistance( float XDistance )
	{
		this.baseXDistance = XDistance;
	}

	/**
	 * This is the basic setter of the Y coordinate distances between cards.
	 * @param YDistance is the distance you want to set between each card.
	 */
	public void setYDistance( float YDistance )
	{
		this.baseYDistance = YDistance;
	}
	
	/**
	 * This is the basic getter of the Y coordinate distances between cards.
	 * @return The current distance between each card on the Y coordinate.
	 */
	public float getYDistance()
	{
		return baseYDistance;
	}

	/**
	 * This is the basic getter of the X coordinate distances between cards.
	 * @return The current distance between each card on the X coordinate.
	 */
	public float getXDistance()
	{ 
		return baseXDistance; 
	}
	
	/**
	 * Checks if the given card (as decorator) is on hand.
	 *
	 * @param deco The card decorator to check
	 * @return true if the card is on hand, false otherwise
	 */
	public boolean isOnHand ( CardDecorator deco)
	{
		for (CardDecorator decoFor : cardOnHandDecoratorList) if (decoFor.equals(deco)) return true;
		return false;
	}
	
	/**
	 * Checks if the given card (as decorator) is on table.
	 *
	 * @param deco The card decorator to check
	 * @return true if the card is on table, false otherwise
	 */
	public boolean isOnTable ( CardDecorator deco)
	{
		for (CardDecorator decoFor : cardOnTableDecoratorList) if (decoFor.equals(deco)) return true;
		return false;
	}
	
}
