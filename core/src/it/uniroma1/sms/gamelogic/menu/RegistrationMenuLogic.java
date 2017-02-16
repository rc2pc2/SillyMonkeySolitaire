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

import it.uniroma1.lcl.saga.api.MainThreadCallbackVoid;
import it.uniroma1.lcl.saga.api.SaGaConnector;
import it.uniroma1.sms.SMSMain;
import it.uniroma1.sms.screen.menuscreen.MainMenuScreen;
import it.uniroma1.sms.screen.menuscreen.RegistrationMenuScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * The RegistrationMenuLogic class, this is the logic side of the RegistrationMenu.
 * @author  Ricciardino
 */
public class RegistrationMenuLogic extends MenuLogic 
{
	/** The main of the game instance. */
	private SMSMain main;
	
	/** The registration menu that is calling this class instance. */
	private RegistrationMenuScreen registrationMenu;
	
	/** The saga connector instance. */
	private SaGaConnector sagaC;
	
	/** The username as String. */
	private String username;
	
	/** The password as String. */
	private String password;
	
	
	/**
	 * Main constructor of the class that instantiates a new registration menu logic instance for the given RegistrationMenu.
	 *
	 * @param main  The main of the game instance.
	 * @param registrationMenu The registration menu that is calling this class instance.
	 */
	public RegistrationMenuLogic ( SMSMain main , RegistrationMenuScreen registrationMenu)
	{
		this.main = main;
		this.registrationMenu = registrationMenu;
		
		registrationMenu.registerButton.addListener(new ClickListener()
			{
				@Override
			    public void touchUp (InputEvent event, float x, float y, int pointer, int button) { connect(); }
			});
	}
	
	/**
	 * PRIVATE METHOD that calls the connection of the registration with the server.
	 */
	private void connect () 
	{
		username = registrationMenu.textAreaID.getText();
		password = registrationMenu.textAreaPSW.getText();
		
		sagaC = SaGaConnector.getInstance();
		sagaC.register(username, password, new MainThreadCallbackVoid() 
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
				registrationMenu.textAreaID.setColor(Color.RED);
				registrationMenu.textAreaPSW.setColor(Color.RED);
			}
		});
	}
}
