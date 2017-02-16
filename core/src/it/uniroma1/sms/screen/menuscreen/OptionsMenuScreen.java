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
import it.uniroma1.sms.gamelogic.menu.OptionsMenuLogic;
import it.uniroma1.sms.object.buttons.ActionButton;
import it.uniroma1.sms.screen.playscreen.MainPlayScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

/**
 * The OptionsMenuScreen class, this instantiates an entire new options menu and also its logic.
 * @author  Ricciardino
 */
public class OptionsMenuScreen implements Screen
{
	/** The main currently in use. */
	private SMSMain main;
	
	/** The logic side of this class. */
	private OptionsMenuLogic logic;
	
	/** The stage currently in use. */
	private Stage stage;
	
	/** The backgroup (baground group). */
	private Group backgroup;
	
	/** The foregroup (foregorund group). */
	private Group foregroup;
	
	/** The skin currently in use. */
	private Skin skin;
	
	/** The back button. */
	public ActionButton backButton;
	
	/** The save button. */
	public ActionButton saveButton;
	
	/** The volume slider. */
	public Slider volumeSlider;
	
	/** The volume label. */
	private Label volumelbl;
	
	/** The width of the screen. */
	private int width = Gdx.graphics.getWidth();
	
	/** The height of the screen. */
	private int height =  Gdx.graphics.getHeight();
	
	/** The buttons width. */
	private float buttonWidth;
	
	/** The buttons height. */
	private float buttonHeight;
	
	/** The buttons spacing. */
	private float buttonSpacing;
	
	/** The button x starting position. */
	private float buttonX;
	
	/** The button y starting position. */
	private float currentY;
	 
	/**
	 * Basic constructor that instantiates a new options menu screen, just with the main and the calling screen.
	 * To USE only if called by MAIN MENU
	 *
	 * @param main the main currently in use.
	 * @param callingScreen the calling screen of this class.
	 */
	public OptionsMenuScreen(SMSMain main, Screen callingScreen) { this(main, callingScreen, null); }

	/**
	 * More complicated constructor that instantiates a new options menu screen by the gameplay (MainPlayScreen) screen.
	 *
	 * @param main the main currently in use.
	 * @param callingScreen the calling screen of this class.
	 * @param mainPlay the main play screen wich the player is playing with.
	 */
	public OptionsMenuScreen(SMSMain main, Screen callingScreen, MainPlayScreen mainPlay)
	{
		this.main 			= main;
		logic = new OptionsMenuLogic(this, callingScreen, mainPlay);

		skin = new Skin(Gdx.files.internal("UISkin.json"));
		stage = new Stage();
		
		backgroup = new Group ();
		foregroup = new Group ();
		
		backButton = new ActionButton("Back", skin);
		saveButton = new ActionButton("Save settings", skin);
		volumelbl = new Label("Volume", skin);
		volumeSlider = new Slider(0f, 1f, .025f, false, skin);
		
		backgroup.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		foregroup.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage.addActor(backgroup);
		stage.addActor(foregroup);
		
		Image bg = new Image(new Texture(Gdx.files.internal("bg/bg1280.png")));
		backgroup.addActor(bg);
		
		logic.calculate();
		
		buttonWidth   = width  / 2.9f;
        buttonHeight  = height / 8f;
        buttonSpacing = height / 48f;
        currentY 	  = height / 1.6f;
        buttonX = ( width - buttonWidth/1.1f ) / 2;
        
        backButton.setBounds(buttonX, currentY, buttonWidth, buttonHeight);
	    foregroup.addActor( backButton );
	       
	   
	    volumelbl.setBounds(buttonX, currentY-=buttonHeight + buttonSpacing, buttonWidth, buttonHeight);
	    volumelbl.setAlignment(Align.center);
	    volumelbl.setScale(1.5f);
	    volumelbl.setColor(Color.BLACK);
	    foregroup.addActor(volumelbl);
	    
	    volumeSlider.setValue(main.getMusic().getVolume());
	    volumeSlider.setBounds(buttonX, currentY-=buttonHeight + buttonSpacing, buttonWidth, buttonHeight);
	    foregroup.addActor(volumeSlider);
	    
	    saveButton.setBounds(buttonX, currentY-=buttonHeight + buttonSpacing, buttonWidth, buttonHeight);
	    foregroup.addActor( saveButton );
	}
	
	/**
	 * This method returns the main of the entire game. 
	 * @return the main currently in use.
	 */
	public SMSMain getMain() { return main; }

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
}
