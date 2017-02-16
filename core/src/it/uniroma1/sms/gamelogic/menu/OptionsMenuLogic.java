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

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

import it.uniroma1.sms.object.buttons.ButtonInputListener;
import it.uniroma1.sms.screen.menuscreen.OptionsMenuScreen;
import it.uniroma1.sms.screen.menuscreen.PauseMenuScreen;
import it.uniroma1.sms.screen.playscreen.MainPlayScreen;

/**
 * The Class OptionsMenuLogic, it calculates the logic of the OptionsMenuScreen class.
 *
 * @author  Ricciardino
 * @version 0.1 , 30-set-2014
 * @see 
 */
public class OptionsMenuLogic extends MenuLogic  
{
	/** The options menu that is calling this logic class. */
	private OptionsMenuScreen menu;
	
	/** The calling screen of the options menu. */
	private Screen callingScreen;
	
	/** The main play screen that the player is playing. */
	private MainPlayScreen mainPlay;
	
	
	/**
	 * Basic constructor of a new options menu logic.
	 *
	 * @param menu  The options menu that is calling this logic instance.
	 * @param callingScreen The calling screen of the options menu.
	 */
	public OptionsMenuLogic( OptionsMenuScreen menu, Screen callingScreen) { this(menu, callingScreen, null); }
	
	/**
	 * More detailed constructor of a new options menu logic.
	 *
	 * @param menu  The options menu that is calling this logic instance.
	 * @param callingScreen The calling screen of the options menu.
	 * @param mainPlay The main play screen that the player is playing.
	 */
	public OptionsMenuLogic ( OptionsMenuScreen menu, Screen callingScreen, MainPlayScreen mainPlay)
	{
		this.menu = menu;
		this.callingScreen = callingScreen;
		this.mainPlay = mainPlay;
	}
	
	/**
	 * PRIVATE METHOD : It calculates needed listeners for the buttons of the options menu (calling).
	 */
	public void calculate()
	{
			menu.backButton.addListener(new ButtonInputListener()
			{
			    public void touchUp (InputEvent event, float x, float y, int pointer, int button) 
			    {
			    	if (callingScreen instanceof PauseMenuScreen && mainPlay!=null) 
			    	{
			    		PauseMenuScreen pauseScreen = (PauseMenuScreen) callingScreen; 
			    		pauseScreen.dispose();
			    		mainPlay.resume();
			    		menu.getMain().setCurrentScreen(mainPlay);
			    	}
			    	else menu.getMain().setCurrentScreen(callingScreen);
			    	menu.dispose();
			    }
			});
			
			menu.saveButton.addListener(new ButtonInputListener()
			{
			    public void touchUp (InputEvent event, float x, float y, int pointer, int button) 
			    {
			    	menu.getMain().getMusic().setVolume(menu.volumeSlider.getVisualValue());
			    }
			});
	}
}
