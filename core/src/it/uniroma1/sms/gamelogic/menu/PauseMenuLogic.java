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

import com.badlogic.gdx.scenes.scene2d.InputEvent;

import it.uniroma1.sms.object.buttons.ButtonInputListener;
import it.uniroma1.sms.screen.menuscreen.MainMenuScreen;
import it.uniroma1.sms.screen.menuscreen.OptionsMenuScreen;
import it.uniroma1.sms.screen.menuscreen.PauseMenuScreen;
import it.uniroma1.sms.screen.playscreen.MainPlayScreen;

/**
 * The class PauseMenuLogic is the logic side of the PauseMenuScreen class, it manages all its buttons and listeners.
 *
 * @author  Ricciardino
 * @version 0.1 , 24-set-2014
 */
public class PauseMenuLogic extends MenuLogic 
{
	/** The pause menu that is calling this class instance. */
	private PauseMenuScreen pauseScreen;
	
	/** The main menu instance needed getting the player back. */
	private MainMenuScreen mainMenu;
	
	/** The main play screen session wich the player is currently playing. */
	private MainPlayScreen mainPlay;

	/**
	 * Main constructor of a new pause menu logic instance for the calling PauseMenuScreen instance, it initializes all needed fields.
	 *
	 * @param pauseScreen The pause menu that is calling this class instance.
	 * @param mainMenu The main menu instance needed getting the player back.
	 * @param mainPlay The main play screen session wich the player is currently playing.
	 */
	public PauseMenuLogic(PauseMenuScreen pauseScreen, MainMenuScreen mainMenu, MainPlayScreen mainPlay)
	{
		this.pauseScreen 	= pauseScreen;
		this.mainMenu 		= mainMenu;
		this.mainPlay		= mainPlay;
	}
	
	/**
	 * This method lets the logic "act" on the calling menu's listeners.
	 */
	public void act()
	{
		pauseScreen.resumeButton.addListener(new ButtonInputListener()
		{
			@Override
		    public void touchUp (InputEvent event, float x, float y, int pointer, int button) 
		    {
		    	pauseScreen.dispose();
		    	mainPlay.resume();
		    }
		});
		
		pauseScreen.restartGameButton.addListener(new ButtonInputListener()
		{
			@Override
		    public void touchUp (InputEvent event, float x, float y, int pointer, int button) 
		    {
		    	startNewGame();
		    	pauseScreen.dispose();
		    }
		});
		
		pauseScreen.quitToMenuButton.addListener(new ButtonInputListener()
		{
			@Override
		    public void touchUp (InputEvent event, float x, float y, int pointer, int button) 
		    {
		    	goToMainMenu();
		    	pauseScreen.dispose();
		    }
		});
		
		pauseScreen.optionsMenuButton.addListener(new ButtonInputListener()
		{	
			@Override	
		    public void touchUp (InputEvent event, float x, float y, int pointer, int button) { optionsMenu();}
		});
	}
	
	/**
	 *PRIVATE METHOD : This method is used to restart the game.
	 */
	private void startNewGame() { new MainPlayScreen(mainMenu.getMain(), mainMenu); }

	/**
	 *PRIVATE METHOD : This method allows the player to go to main menu.
	 */
	private void goToMainMenu()
	{
		mainPlay.dispose();
    	mainMenu.getMain().setCurrentScreen(new MainMenuScreen(mainMenu.getMain()));
	}
	
	/**
	 * PRIVATE METHOD : This method takes the player to the options menu.
	 */
	private void optionsMenu() { mainMenu.getMain().setCurrentScreen(new OptionsMenuScreen(mainMenu.getMain(), pauseScreen, mainPlay)); }

}
