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

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import it.uniroma1.lcl.saga.api.Language;
import it.uniroma1.lcl.saga.api.MainThreadCallbackVoid;
import it.uniroma1.lcl.saga.api.SaGaConnector;
import it.uniroma1.sms.SMSMain;
import it.uniroma1.sms.screen.menuscreen.LoginMenuScreen;
import it.uniroma1.sms.screen.menuscreen.MainMenuScreen;
import it.uniroma1.sms.screen.menuscreen.RegistrationMenuScreen;

/**
 * The Class LoginMenuLogic.
 *  
 *
 * @author  Ricciardino
 * @version 0.1 , 12-set-2014
 */
public class LoginMenuLogic extends MenuLogic 
{
	/** The main. */
	private SMSMain main;
	
	/** The saga connector field */
	private SaGaConnector sagaC;
	
	/** The username string */
	private String user;
	
	/** The password string. */
	private String psw;
	
	/** The language type */
	private Language lang;
	
	/** The login menu used as reference. */
	private LoginMenuScreen loginMenu;
	
	/**
	 * Instantiates a new login menu logic for the LoginMenu class that is calling it.
	 *
	 * @param main The main of the game instance
	 * @param loginMenu The login menu that is calling this logic class. 
	 */
	public LoginMenuLogic( SMSMain main , LoginMenuScreen loginMenu)
	{
		this.main = main;
		this.loginMenu = loginMenu;
		sagaC = main.sagaConnector;
		
		loginMenu.BTLogin.addListener(new ClickListener()
		{
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) { connect(); }
		});
		
		loginMenu.BTRegister.addListener(new ClickListener()
		{
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) { register(); }
		});
	}
	
	/**
	 * PRIVATE METHOD that calls the registration menu.
	 */
	private void register() { main.setCurrentScreen(new RegistrationMenuScreen (main, loginMenu)); }
	
	/**
	 * PRIVATE METHOD that calls the connection of the login.
	 */
	private void connect () 
	{
		user = loginMenu.textAreaID.getText();
		psw = loginMenu.textAreaPSW.getText();
		lang = Language.IT;
		
		sagaC.login(user, psw, lang, new MainThreadCallbackVoid() 
		{
			@Override
			public void onSuccessInMainThread() 
			{
				main.getCurrentScreen().dispose();
				main.setCurrentScreen(new MainMenuScreen (main));
			}

			@Override
			public void onFailureInMainThread(Throwable cause) 
			{
				loginMenu.BTRegister.setColor(Color.RED);
			}
		});
	}
}