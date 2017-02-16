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
package it.uniroma1.sms.gamelogic.menu;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

import it.uniroma1.sms.object.buttons.ButtonInputListener;
import it.uniroma1.sms.screen.menuscreen.PurposeMenuScreen;
import it.uniroma1.sms.screen.playscreen.MainPlayScreen;

/**
 * This is the logic side (class) of the PurposeMenuScreen class, it manages all listeners and a part of the purpose screen images.
 *
 * @author  Ricciardino
 */
public class PurposeMenuLogic extends MenuLogic 
{
	/** The purpose menu screen that is calling this instance. */
	private PurposeMenuScreen menuScreen;
	
	/** The main play screen wich the player is playing. */
	private MainPlayScreen mainPlay;
	
	/** The Constant image of the YES_BUTTON answer. */
	private static final Image YES_BUTTON = new Image ( new Texture ( Gdx.files.internal("elements/acceptUNCHECKED.png") ) );
	
	/** The Constant image of the NO_BUTTON answer. */
	private static final Image NO_BUTTON = new Image ( new Texture ( Gdx.files.internal("elements/declineUNCHECKED.png") ) );
	
	/** The Constant image of the YES_BUTTON_PRESSED. */
	private static final Image YES_BUTTON_PRESSED = new Image ( new Texture ( Gdx.files.internal("elements/accept.png") ) );
	
	/** The Constant image of the NO_BUTTON_PRESSED. */
	private static final Image NO_BUTTON_PRESSED = new Image ( new Texture ( Gdx.files.internal("elements/decline.png") ) );
	
	/** The first button group. */
	private ButtonGroup firstButtonGroup;
	
	/** The second button group. */
	private ButtonGroup secondButtonGroup;
	
	/** The third button group. */
	private ButtonGroup thirdButtonGroup;
	
	/** The first annotation. */
	private boolean annotationOne;
	
	/** The second annotation. */
	private boolean annotationTwo;
	
	/** The third annotation. */
	private boolean annotationThree;
	
	/** The positive answers list. */
	private	LinkedList<ImageButton> positiveAnswers;
	
	/** The negative answers list. */
	private LinkedList<ImageButton> negativeAnswers;
	
	/**
	 * Main constructor that initializes all fields and instantiates a new purpose menu logic instance.
	 *
	 * @param purposeMenuScreen The purpose menu screen that is calling this instance.
	 * @param mainPlay The main play screen wich the player is playing.
	 */
	public PurposeMenuLogic(PurposeMenuScreen purposeMenuScreen, MainPlayScreen mainPlay)
	{
		this.menuScreen 	= purposeMenuScreen;
		this.mainPlay		= mainPlay;
	}
	
	/**
	 * This method allows the logic to act on the Screen class.
	 */
	public void act()
	{
		positiveAnswers = new LinkedList<ImageButton>();
		negativeAnswers = new LinkedList<ImageButton>();
		
		for (int i=0 ; i<3 ; i++)
		{
			positiveAnswers.add(new ImageButton(YES_BUTTON.getDrawable(), YES_BUTTON_PRESSED.getDrawable(), YES_BUTTON_PRESSED.getDrawable()));
			negativeAnswers.add(new ImageButton(NO_BUTTON.getDrawable(), NO_BUTTON_PRESSED.getDrawable(), NO_BUTTON_PRESSED.getDrawable()));
		}
		
		for (ImageButton yes : positiveAnswers) 
		{
			yes.setBounds(20, 5, 100, 100);
			yes.setName("Y");
		}
		
		for (ImageButton no : negativeAnswers)
		{
			no.setBounds(210, 5, 100, 100);
			no.setName("N");
		}
		
		firstButtonGroup = new ButtonGroup (positiveAnswers.get(0), negativeAnswers.get(0));
		secondButtonGroup = new ButtonGroup (positiveAnswers.get(1), negativeAnswers.get(1));
		thirdButtonGroup = new ButtonGroup (positiveAnswers.get(2), negativeAnswers.get(2));
		
		menuScreen.firstWindow.add(positiveAnswers.get(0), negativeAnswers.get(0));
		menuScreen.secondWindow.add(positiveAnswers.get(1), negativeAnswers.get(1));
		menuScreen.thirdWindow.add(positiveAnswers.get(2), negativeAnswers.get(2));
		
		menuScreen.resumeButton.addCaptureListener(new ButtonInputListener()
		{
			@Override
		    public void touchUp (InputEvent event, float x, float y, int pointer, int button) 
		    {
				if (firstButtonGroup.getChecked()!=null && secondButtonGroup.getChecked()!=null && thirdButtonGroup.getChecked()!=null)
				{
					saveAnnotationAnswers();
					firstButtonGroup.uncheckAll();
					secondButtonGroup.uncheckAll();
					thirdButtonGroup.uncheckAll();
					menuScreen.dispose(annotationOne, annotationTwo, annotationThree);
			    	mainPlay.resume();
				}
		    }
		});
	}

	/**
	 * PRIVATE METHOD that stores annotation answers.
	 */
	private void saveAnnotationAnswers()
	{
		if (firstButtonGroup.getChecked().getName().equals("Y")) annotationOne=true;
		if (secondButtonGroup.getChecked().getName().equals("Y")) annotationTwo=true;
		if (thirdButtonGroup.getChecked().getName().equals("Y")) annotationThree=true;
	}
}
