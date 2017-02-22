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

import java.util.LinkedList;

/**
 * Simple class to manage the in-game score.
 * @author Ricciardino
 *
 */
public class ScoreManager 
{
	/** The basic score of the actual game session. */
	private int score;
	
	/** The multiplier of the score. */
	private float multiplier;
	
	/** The annotation score |UNUSED|. */
	private float annotationScore = 2f;
	
	/** The high score of the player. */
	private int highScore;
	
	/** The list of the annotation score history. */
	private LinkedList<Float> annotationScoreHistory;
	
	/**
	 * Main and unique constructor of the class, it initializes fields.
	 */
	public ScoreManager()
	{
		setScore(0);
		resetMulti(); 
		annotationScoreHistory = new LinkedList<Float>();
	}
	
	/**
	 * This method adds score automatically, it must be called when you add successfully a card.
	 */
	public void addScore()
	{
		multiplier+=.5f;
		score += 1000*multiplier;
	}
	
	/**
	 * Basic getter of the field score.
	 * @return The score as integer.
	 */
	public int getScore() { return score; }
	
	/**
	 * Basic setter of the score. Mostly unused.
	 * @param score The score you want to set as integer.
	 */
	public void setScore(int score) { this.score = score; }
	
	/**
	 * This method resets the (implicit) multiplier, it must be called when you cycle cards by deck.
	 */
	public void resetMulti() { multiplier = 1; }
	
	/**
	 * Sets the high score.
	 *
	 * @param highScore the new high score
	 */
	public void setHighScore(int highScore) { if (highScore>this.highScore) this.highScore = highScore;	}
	
	/**
	 * Gets the high score.
	 *
	 * @return the high score
	 */
	public int getHighScore() { return highScore; }
	
	/**
	 * Adds the annotation score.
	 *
	 * @param annotationScore The annotation score
	 */
	public void addAnnotationScore(Float annotationScore)
	{
		annotationScoreHistory.add(annotationScore);
		if (annotationScoreHistory.size()==9) updateAnnotationScore();
	}
	
	/**
	 * Update the annotation score, everything is calculated |UNUSED|.
	 */
	private void updateAnnotationScore()
	{
		Float currentScore = 0f;
		for ( Float f : annotationScoreHistory) currentScore+=f;
		currentScore/=annotationScoreHistory.size();
		
		if (annotationScore == 2f) annotationScore = currentScore;
		else 
		{
			annotationScore+=currentScore;
			annotationScore/=2;
		}
	}
	
	/**
	 * Gets the annotation score.
	 *
	 * @return The annotation score
	 */
	public float getAnnotationScore(){ return annotationScore; }

}
