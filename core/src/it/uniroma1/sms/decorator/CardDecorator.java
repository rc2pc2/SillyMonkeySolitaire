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
package it.uniroma1.sms.decorator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import it.uniroma1.sms.gamelogic.gameplay.Card;
import it.uniroma1.sms.gamelogic.gameplay.DeckGameLogic;

/**
 * This CardDecorator class has the role to manage every single card used in the game, it contains the image of the card and also its back image.
 *
 * @author  Ricciardino
 * @version 0.1 , 29-ago-2014
 */
public class CardDecorator  
{
	/** The card object (by the Card enum). */
	private Card card;
	
	/** The card name as string, used in .equals between two cards to return differences. */
	private String cardName;
	
	/** The displayed image as its status (covered or not). @see {@link #getDisplayedImage()} */
	private Image displayedImage;
	
	/** The card face image. */
	private Image cardImage;
	
	/** The back of the card as Image. **CONSTANT** */
	private static final Image backImage =  new Image(new Texture(Gdx.files.internal("cards/cover.png")));
	
	/** The card listener. @see it.uniroma1.sms.gamelogic.gameplay.CardsClickListener @see {@link #getCardListener()} */
	private ClickListener cardCListener;
	
	/** The boolean that tell us if the card is covered. TRUE means that it is, FALSE otherwise. @see {@link #isCovered()} */
	private boolean isCovered;
	
	/** The boolean that tell us if the card is flipped. TRUE means that it is, FALSE otherwise. @see {@link #isFlipped()} */
	private boolean isFlipped;

	/**  The x-coordinate of the card @see {@link #getX()}. */
	private float x;
	
	/**  The y-coordinate of the card @see {@link #getY()}. */
	private float y;
	
	/**  The width of the card @see {@link #getWidth()}. */
	private float width;
	
	/**  The height of the card @see {@link #getHeight()}. */
	private float height;
	
	/**
	 * Main constructor of the class, it instantiates a new card decorator with the given card as basic field.	 *
	 *
	 * @param card the card
	 */
	public CardDecorator( Card card)
	{
		this.card = card;
		cardName 		= Card.valueOf(card+"")+"";
		cardImage 		= new Image( new Texture ( Gdx.files.internal( new DeckGameLogic().getCardTexture(card) ) ) );
		displayedImage	= new Image( new Texture ( Gdx.files.internal( new DeckGameLogic().getCardTexture(card) ) ) );;
		cardCListener 	= new ClickListener();
	}
	
	/**
	 * Other constructor of the class, it instantiate a new card decorator with the given card and replace the image given with the image of the card.
	 *
	 * @param card The card to display
	 * @param x the x
	 * @param y the y
	 * @param width the width
	 * @param height the height
	 */
	public CardDecorator (Card card, float x, float y, float width, float height)
	{
		this(card);
		this.x		= x;
		this.y		= y;
		this.width 	= width;
		this.height = height;
		
		displayedImage.setBounds(x, y, width, height);
		displayedImage.addCaptureListener(cardCListener);
	}
		
	/**
	 * This method uncovers our card, setting displayedImage as its real face.
	 *
	 * @return the image
	 * @ the displayed image, uncovered obviously.
	 */
	public Image uncoverCard()
	{
		if (isCovered)
		{
			displayedImage.setDrawable(cardImage.getDrawable());
			isCovered = false;
		}
		return getDisplayedImage();
	}
	
	/**
	 * This method covers our card, setting displayedImage as its back face. @see #backImage field
	 * @return the displayed image, covered obviously.
	 */
	public Image coverCard()
	{
		if (!isCovered) 
		{
			displayedImage.setDrawable(backImage.getDrawable());
			isCovered = true;
		}
		return getDisplayedImage();
	}
	
	/**
	 * Checks if the card is covered or not.
	 * @return true, if is covered
	 */
	public boolean isCovered() { return isCovered; }
	
	/**
	 * Gets the card.
	 * @return the card itself
	 */
	public Card getCard() { return card; }
	
	/**
	 * Gets the displayed image:
	 * Card's face if is uncovered,
	 * the card's back otherwise.
	 *
	 * @return the current displayed image
	 */
	public Image getDisplayedImage() 
	{
		return displayedImage; 
	}
	
	/**
	 * Sets the bounds.
	 *
	 * @param x the x
	 * @param y the y
	 * @param width the width
	 * @param height the height
	 */
	public void setBounds (float x, float y, float width, float height)
	{
		displayedImage.setBounds(x, y, width, height);
	}
	
	/**
	 * Gets the card name.
	 *
	 * @return the card name
	 */
	public String getCardName()
	{
		return cardName;
	}
	
	/**
	 * Gets the x coordinate of the card.
	 *
	 * @return the x
	 */
	public float getX(){ return x; }
	
	/**
	 * Gets the y coordinate of the card.
	 *
	 * @return the y
	 */
	public float getY(){ return y; }
	
	/**
	 * Gets the width of the card.
	 *
	 * @return the width
	 */
	public float getWidth(){ return width; }
	
	/**
	 * Gets the height of the card.
	 *
	 * @return the height
	 */
	public float getHeight(){ return height; }
	
	/**
	 * Gets the card click listener.
	 *
	 * @return the card click listener
	 */
	public ClickListener getCardListener() { return cardCListener;	}
	
	/**
	 * Gets the back image of the card.
	 *
	 * @return the back image
	 */
	public static Image getBackImage()
	{
		return backImage;
	}

	/**
	 * Gets the front image of the card.
	 * 
	 * @return the front image.
	 */
	public Image getFrontImage()
	{
		return cardImage;
	}
	
	/**
	 * Flips the card.
	 */
	public void flip() { isFlipped=true; }
	
	/**
	 * Checks if the card is flipped
	 * @return If the card is flipped true, false otherwise.
	 */
	public boolean isFlipped() { return isFlipped; }
	
	/* (NON JAVADOC)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object other)
	{
		if ( other == null || !(other instanceof CardDecorator) ) return false;
		if ( other == this ) return true;
		CardDecorator deco = (CardDecorator) other;
		if (deco.getCardName().equals(cardName)) return true;
		return false;
	}
}
