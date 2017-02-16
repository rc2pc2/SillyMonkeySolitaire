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

import it.uniroma1.sms.object.buttons.ButtonInputListener;
import it.uniroma1.sms.screen.menuscreen.MainMenuScreen;
import it.uniroma1.sms.screen.menuscreen.OptionsMenuScreen;
import it.uniroma1.sms.screen.playscreen.MainPlayScreen;

import com.badlogic.gdx.scenes.scene2d.InputEvent;

/**
 * Logic part for any MainMenuScreen instance.
 * @author Ricciardino
 *
 */
public class MainMenuLogic extends MenuLogic 
{
	/** The main menu that is calling this logic instance */
	private MainMenuScreen menu;
	
	/**
	 * Basic constructor of the class, taking the main menu and saving it as field "menu".
	 * @param menu MainMenuScreen instance that has called the instance of this class.
	 */
	public MainMenuLogic(MainMenuScreen menu)
	{
		this.menu = menu;
	}
	
	/**
	 * Logical constructor, it turns on the MainMenuScreen logic side from here.
	 */
	public void calculate()
	{
			menu.startGameButton.addListener(new ButtonInputListener()
			{
			    public void touchUp (InputEvent event, float x, float y, int pointer, int button) 
			    {
			    	menu.dispose();
			    	startNewGame();
			    }
			});

			menu.optionsButton.addListener(new ButtonInputListener()
			{
			    public void touchUp (InputEvent event, float x, float y, int pointer, int button) { optionsMenu(); }
			});
			menu.recordButton.addListener(new ButtonInputListener());

			menu.exitButton.addListener(new ButtonInputListener()
				{
				    public void touchUp (InputEvent event, float x, float y, int pointer, int button) { close(); }
				});
	}
	
	/**
	 * PRIVATE METHOD: Game play restarting method, it just creates another instance of the MainPlayScreen class.
	 */
	private void startNewGame() { new MainPlayScreen(menu.getMain(), menu); }
	
	/**
	 * PRIVATE METHOD: Game play options menu calling method, it just creates another instance of the OptionsMenuScreen class.
	 */
	private void optionsMenu() { menu.getMain().setCurrentScreen(new OptionsMenuScreen(menu.getMain(), menu)); }
	
	/**
	 * PRIVATE METHOD: This method allows to quit the game.
	 */
	private void close() {menu.getMain().dispose();}
}
