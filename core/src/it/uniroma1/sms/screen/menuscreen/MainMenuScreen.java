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
import it.uniroma1.sms.gamelogic.menu.MainMenuLogic;
import it.uniroma1.sms.object.buttons.ActionButton;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * This is the main menu class, it will be called at the beginning of the game.
 * @author Ricciardino
 *
 */
public class MainMenuScreen extends MenuScreen
{
	/** The main of the game currently in use. */
	private SMSMain main;
	
	/** The logic side class of this menu. */
	private MainMenuLogic menuLogic;
	
	/** The backgroup (background group). */
	private Group backgroup;
	
	/** The foregroup (foreground group). */
	private Group foregroup;
	
	/** The stage currently in use. */
	private Stage stage;
	
	/** The skin currently in use. */
	private Skin skin;
	
	/** The width of the screen. */
	private int width = Gdx.graphics.getWidth();
	
	/** The height of the screen. */
	private int height =  Gdx.graphics.getHeight();
	
	/** The background image of the main menu. */
	private Image bg;
	
	/** The welcome label. */
	public Label welcomeLabel;
	
	/** The start game button. */
	public ActionButton startGameButton;
	
	/** The options button. */
	public ActionButton optionsButton;
	
	/** The record button. */
	public ActionButton recordButton;
	
	/** The exit button. */
	public ActionButton exitButton;

	/** The button width. */
	private float buttonWidth;
	
	/** The button height. */
	private float buttonHeight;
	
	/** The button spacing. */
	private float buttonSpacing;
	
	/** The button x. */
	private float buttonX;
	
	/** The current y. */
	private float currentY;
	 
	
	/**
	 * This is the main menu constructor that instantiates the MainMenuScreen
	 * @param main The main of the game currently in use.
	 */
	public MainMenuScreen(SMSMain main)
	{
		this.main = main;
        menuLogic = new MainMenuLogic(this);
        
		skin = new Skin(Gdx.files.internal("UISkin.json"));
		
		stage = new Stage();
		backgroup = new Group ();
		foregroup = new Group ();
		
		backgroup.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		foregroup.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		stage.addActor(backgroup);
		stage.addActor(foregroup);
		
		bg = new Image(new Texture(Gdx.files.internal("bg/bg1280.png")));
		backgroup.addActor(bg);
		 		
		welcomeLabel = new Label( "Welcome to the Silly Monkey Solitaire!!", getSkin() );
        startGameButton = new ActionButton( "Start game", getSkin() );
        optionsButton = new ActionButton( "Options", getSkin() );
        recordButton = new ActionButton( "Records", getSkin() );
        exitButton = new ActionButton( "Exit", getSkin() );
        
        menuLogic.calculate();
        
        buttonWidth   = width  / 2.9f;
        buttonHeight  = height / 8f;
        buttonSpacing = height / 48f;
        currentY 	  = height / 1.6f;
        buttonX = ( width - buttonWidth/1.1f ) / 2;
		 
	    welcomeLabel.setX ( buttonX );
	    welcomeLabel.setY ( currentY + 100 );

	    startGameButton.setBounds(buttonX, currentY, buttonWidth, buttonHeight);
	    foregroup.addActor( startGameButton );
	       
	    optionsButton.setBounds(buttonX, currentY-=buttonHeight + buttonSpacing, buttonWidth, buttonHeight);
	    foregroup.addActor( optionsButton );

	    recordButton.setBounds(buttonX, currentY-=buttonHeight + buttonSpacing, buttonWidth, buttonHeight);
	    foregroup.addActor( recordButton );
	       
	    exitButton.setBounds(buttonX, currentY-=buttonHeight + buttonSpacing, buttonWidth, buttonHeight);
	    foregroup.addActor( exitButton );	
	}
	
	@Override
	public void render(float delta) 
	{
		stage.draw();
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void show() {}

	@Override
	public void hide() {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() { stage.dispose(); }

	/**
	 * This method gets the main in use.
	 * @return the main currently in use.
	 */
	public SMSMain getMain() { return main; }
	
	/**
	 * This method is a simple getter of the Skin used for the main menu.
	 * @return Main menu skin.
	 */
	public Skin getSkin() { return skin; }
}
