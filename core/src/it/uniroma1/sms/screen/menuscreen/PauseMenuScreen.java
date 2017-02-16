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

import it.uniroma1.sms.gamelogic.menu.PauseMenuLogic;
import it.uniroma1.sms.object.buttons.ActionButton;
import it.uniroma1.sms.screen.playscreen.MainPlayScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * This is the pause menu screen class, it creates a pause menu which will draw on the playscreen (or even on the purpose screen), without changing the screen "really" 
 * @author  Ricciardino
 */
public class PauseMenuScreen extends MenuScreen
{
	/** The logic side of the class. */
	private PauseMenuLogic logic;
	
	/** The Constant Image PAUSE_BG (background). */
	private static final Image PAUSE_BG = new Image ( new Texture ( Gdx.files.internal("bg/pause1920.png") ) );
	
	/** The current stage. */
	private Stage currentStage;
	
	/** The resume button. */
	public ActionButton resumeButton;
	
	/** The options menu button. */
	public ActionButton optionsMenuButton;
	
	/** The restart game button. */
	public ActionButton restartGameButton;
	
	/** The quit to menu button. */
	public ActionButton quitToMenuButton;
	
	/** The skin currently in use. */
	private Skin skin;
	
	/**
	 * Main constructor that instantiates a new pause menu screen by the current stage of the active screen (@see LGWLAPP method "setCurrentScreen"), the main menu and the main play screen.
	 *
	 * @param currentStage the current stage.
	 * @param mainMenu the active main menu. 
	 * @param mainPlay the main play screen.
	 */
	public PauseMenuScreen(Stage currentStage, MainMenuScreen mainMenu, MainPlayScreen mainPlay)
	{
		this.currentStage = currentStage;
		
		logic = new PauseMenuLogic (this, mainMenu, mainPlay);
		skin = new Skin(Gdx.files.internal("UISkin.json"));
		
		resumeButton 		= new ActionButton("Resume game", skin, mainMenu.startGameButton);
		optionsMenuButton 	= new ActionButton("Options", skin, mainMenu.optionsButton);
		restartGameButton 	= new ActionButton("New game", skin, mainMenu.recordButton);
		quitToMenuButton 	= new ActionButton("Main Menu", skin, mainMenu.exitButton);
		PAUSE_BG.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		logic.act();
	}

	/**
	 * This method is called when the pause menu screen needs to be active.
	 */
	public void act()
	{
		currentStage.addActor(PAUSE_BG);
		currentStage.addActor(optionsMenuButton);
		currentStage.addActor(resumeButton);
		currentStage.addActor(restartGameButton);
		currentStage.addActor(quitToMenuButton);
	}
	
	@Override
	public void render(float delta) {}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void show() {}

	@Override
	public void hide() { PAUSE_BG.remove(); }

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() 
	{
		PAUSE_BG.remove();
		resumeButton.remove();
		optionsMenuButton.remove();
		restartGameButton.remove();
		quitToMenuButton.remove();
	}
}