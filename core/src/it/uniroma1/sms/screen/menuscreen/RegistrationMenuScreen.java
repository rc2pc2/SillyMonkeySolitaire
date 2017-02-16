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
package it.uniroma1.sms.screen.menuscreen;

import it.uniroma1.sms.SMSMain;
import it.uniroma1.sms.gamelogic.menu.RegistrationMenuLogic;
import it.uniroma1.sms.object.buttons.ActionButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

/**
 * The Class RegistrationMenu.
 *  
 *
 * @author  Ricciardino
 * @version 0.1 , 1-ott-2014
 * @see 
 */
public class RegistrationMenuScreen extends MenuScreen 
{
	/** The logic side of the class. */
	private RegistrationMenuLogic logic;
	
	/** The login menu that is calling this registration menu instance. */
	private LoginMenuScreen loginMenu;
	
	/** The text area of the username. */
	public TextField textAreaID;
	
	/** The text area of the password (hidden with "*" chars). */
	public TextField textAreaPSW;
	
	/** The register button. */
	public ActionButton registerButton;
	
	/**
	 * Main constructor of the class that instantiates a new registration menu with the main and the login menu currently active.
	 *
	 * @param main the main of the game instance.
	 * @param loginMenu the login menu that is calling this instance.
	 */
	public RegistrationMenuScreen (SMSMain main, LoginMenuScreen loginMenu)
	{
		this.loginMenu = loginMenu;
		
		textAreaID  = loginMenu.textAreaID;
		textAreaPSW = loginMenu.textAreaPSW;
		registerButton = loginMenu.BTRegister;
		registerButton.setBounds(loginMenu.BTLogin.getX(), loginMenu.BTLogin.getY(), loginMenu.BTLogin.getWidth(), loginMenu.BTLogin.getHeight());
		loginMenu.BTLogin.remove();
		
		logic = new RegistrationMenuLogic(main, this); 
	}

	@Override
	public void render(float delta) { loginMenu.render(delta); }

	@Override
	public void resize(int width, int height) { loginMenu.resize(width, height);}

	@Override
	public void show() { loginMenu.show(); }

	@Override
	public void hide() { loginMenu.hide(); }

	@Override
	public void pause() { loginMenu.pause(); }

	@Override
	public void resume() { loginMenu.resume(); }
	
	@Override
	public void dispose() {	loginMenu.dispose(); }
}
