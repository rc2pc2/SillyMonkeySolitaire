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
package it.uniroma1.sms;
import it.uniroma1.lcl.saga.api.SaGaConnector;
import it.uniroma1.sms.screen.menuscreen.LoginMenuScreen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;

/**
 * This is the main class of the entire Silly Monkey Solitaire project.
 * @author  Ricciardino
 * @version 0.1, 24 September 2014
 * @see     com.badlogic.gdx.ApplicationListener
 */
public class SMSMain implements ApplicationListener 
{
	/** The main logo path. */
	public final String MAIN_LOGO_PATH = "monkeylogo.png";
	
	/** The saga connector. */
	public SaGaConnector sagaConnector;
	
	/** The music currently used as background music. */
	private Music backgroundMusic;
	
	/** The current screen. */
	private Screen currentScreen;

	/**
	 * Basic and also empty constructor of the Main class, everything that needs to be initialized is in the create() method for stability reasons [@see libgdx wiki for further informations].
	 */
	public SMSMain() {}

	@Override
	public void create () 
	{
		sagaConnector = SaGaConnector.getInstance();
		currentScreen = new LoginMenuScreen(this);		
		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music/et__-_02_-_Kopeika.mp3"));	
		backgroundMusic.play(); 
		backgroundMusic.setVolume(.15f);
	}
	
	/**
	 * Basic render method, it renders with basic engine delta (float) time, not given as argument.
	 */
	public void render () { currentScreen.render(Gdx.graphics.getDeltaTime());} 
	
	@Override
	public void dispose()
	{
		backgroundMusic.dispose();
		Gdx.app.exit();
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}
	
	/**
	 * This method is the basic getter of the music field.
	 * @return the music
	 */
	public Music getMusic(){ return backgroundMusic; }
	
	/**
	 * Basic getter of the Screen currentScreen field.
	 * @return Screen currentScreen (the screen currently in use)
	 */
	public Screen getCurrentScreen () { return currentScreen; }
	
	/**
	 * Basic setter of the Screen used.
	 * @param screen The screen you want to be used by this class.
	 */
	public void setCurrentScreen( Screen screen) { currentScreen = screen; }
}
