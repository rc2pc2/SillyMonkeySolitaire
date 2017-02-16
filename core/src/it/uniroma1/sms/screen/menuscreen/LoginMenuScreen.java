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
import it.uniroma1.sms.gamelogic.menu.LoginMenuLogic;
import it.uniroma1.sms.object.buttons.ActionButton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

/**
 * The LoginMenuScreen class, used to build a login menu to access to the main server. 
 * @author  Ricciardino
 */
public class LoginMenuScreen extends MenuScreen 
{
	/** The logic side of this class. */
	private LoginMenuLogic logic;

	/** The height of the screen. */
	private float height;
	
	/** The width of the screen. */
	private float width;
	
	/** The stage of this class. */
	private Stage stage;
	
	/** The backgroup (background group). */
	private Group backgroup;
	
	/** The midgroup (midground group). */
	private Group midgroup;
	
	/** The foregroup (foreground group). */
	private Group foregroup;
	
	/** The skin used in this class. */
	private Skin skin;
	
	/** The label of the username. */
	private Label lblID;
	
	/** The label of the password. */
	private Label lblPSW;
	
	/** The text area for the username string. */
	public TextField textAreaID;
	
	/** The text area for the password string (hidden in public by "*" char). */
	public TextField textAreaPSW;
	
	/** The login button (managed by logic). */
	public ActionButton BTLogin;
	
	/** The register button (managed by logic). */
	public ActionButton BTRegister;
	
	/**
	 * Main constructor that instantiates a new login menu just with the main of the game.
	 * @param main The main class (SMSMain) of this game
	 */
	public LoginMenuScreen(SMSMain main)
	{
		stage = new Stage();
		backgroup = new Group();
		midgroup = new Group();
		foregroup = new Group(); 
		
		width  = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
		backgroup.setBounds(0, 0, width, height);
		foregroup.setBounds(0, 0, width, height);
		midgroup.setBounds(0, 0, width, height);
		
		stage.addActor(backgroup);
		stage.addActor(midgroup);
		stage.addActor(foregroup);
		
		Image bg = new Image(new Texture(Gdx.files.internal("bg/login1280.png")));
		backgroup.addActor(bg);
		
		skin = new Skin(Gdx.files.internal("UISkin.json"));
		
		float xArea = width/2 - (width/5)/2 ;
		float yArea = height/1.7f ;
				
		lblID = new Label("Username", skin);
		lblID.setBounds(xArea, yArea + height/7, width/5, height/16);
		lblID.setColor(Color.WHITE);
		lblID.setAlignment(Align.center);
		textAreaID  = new TextField("", skin);
		textAreaID.setBounds( xArea,  yArea + height/14, width/5 , height/16);
		
		lblPSW = new Label("Password", skin);
		lblPSW.setBounds(xArea, yArea, width/5, height/14);
		lblPSW.setAlignment(Align.center);
		lblPSW.setColor(Color.WHITE);
		textAreaPSW = new TextField("", skin);
		textAreaPSW.setPasswordMode(true);
		textAreaPSW.setPasswordCharacter('*');
		textAreaPSW.setBounds(xArea, yArea  -= height/14, width/5, height/16);
		
		BTLogin = new ActionButton("Login", skin);
		BTLogin.setBounds(xArea, yArea  -= height/10, width/5, height/16);
		BTRegister = new ActionButton("Register", skin);
		BTRegister.setBounds(xArea, yArea  -= height/10, width/5, height/16);
		
		BTLogin.setColor(Color.GRAY);
		BTRegister.setColor(Color.GRAY);
		
		foregroup.addActor(textAreaID);
		foregroup.addActor(textAreaPSW);
		foregroup.addActor(lblID);
		foregroup.addActor(lblPSW);
		foregroup.addActor(BTLogin);
		foregroup.addActor(BTRegister);
		
		logic = new LoginMenuLogic(main, this); 
	}

	/**
	 * Gets the stage currently in use.
	 * @return the stage in use.
	 */
	public Stage getStage() { return stage; }
	
	@Override
	public void render(float delta) 
	{
		stage.draw();
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void resize(int width, int height)  { stage.getViewport().update(width, height, true); }

	@Override
	public void show() {}

	@Override
	public void hide() {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}
	
	@Override
	public void dispose()  { stage.dispose(); }
}
