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

import java.util.Set;

import it.uniroma1.lcl.saga.api.AnnotationData;
import it.uniroma1.lcl.saga.api.DataType;
import it.uniroma1.lcl.saga.api.ImageAnnotationData;
import it.uniroma1.lcl.saga.api.MainThreadCallback;
import it.uniroma1.lcl.saga.api.SaGaConnector;
import it.uniroma1.lcl.saga.api.exceptions.AuthenticationRequiredException;
import it.uniroma1.sms.gamelogic.menu.PurposeMenuLogic;
import it.uniroma1.sms.object.buttons.ActionButton;
import it.uniroma1.sms.screen.playscreen.MainPlayScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

/**
 * The Class PurposeMenuScreen, used for the purpose screen, probably the most complicated class of the entire project.
 * @author  Ricciardino
 */
public class PurposeMenuScreen extends MenuScreen
{
	/* NON JAVADOC : ERRORS LEGEND 0=StartingError, 1=BuildingError, 2=PurposeError; ### number of the error, char or string to relate near errors : example 0x001A --> First error while starting game. */

	/** The annotation checker. */
	public boolean annotationChecker;

	/** The logic side of this class. */
	private PurposeMenuLogic logic;
	
	/** The saga connector instance saved as field. */
	private SaGaConnector sagaConnector;
	
	/** The Constant Image PURPOSE_BG background. */
	private static final Image PURPOSE_BG = new Image ( new Texture ( Gdx.files.internal("elements/purposeMenuBG1280.png") ) );

	/** The current stage. */
	private Stage currentStage;
	
	/** The current group. */
	private Group currentGroup;
	
	/** The resume button. */
	public ActionButton resumeButton;
	
	/** The annotation data field. */
	private AnnotationData annotationData;
	
	/** The label showed lemma (by server callback) . */
	private Label showedLemma;
	
	/** The label showed gloss (by server callback). */
	private Label showedGloss;
	
	/** The lemma string (by server callback). */
	private String lemmaString;
	
	/** The gloss string (by server callback). */
	private String glossString;
	
	/** The skin currently used. */
	private Skin skin;
	
	/** The first annotation image texture. */
	private Texture firstAnnImageTexture;
	
	/** The second annotation image texture. */
	private Texture secondAnnImageTexture;
	
	/** The third annotation image texture. */
	private Texture thirdAnnImageTexture;
	
	/** The first annotation image. */
	private Image firstAnnImage;
	
	/** The second annotation image. */
	private Image secondAnnImage;
	
	/** The third annotation image. */
	private Image thirdAnnImage;
	
	/** The first window. */
	public Window firstWindow;
	
	/** The second window. */
	public Window secondWindow;
	
	/** The third window. */
	public Window thirdWindow;
	
	/** The first image listener. */
	private ClickListener firstImageListener;
	
	/** The second image listener. */
	private ClickListener secondImageListener;
	
	/** The third image listener. */
	private ClickListener thirdImageListener;
	
	/** The annotation keys array. */
	private Object[] annotationsKeyArray;
	
	/**
	 * Main constructor of the class that instantiates a new purpose menu screen with the current stage, the main menu and the main play screen in use.
	 *
	 * @param currentStage the current stage.
	 * @param mainMenu the active main menu.
	 * @param mainPlay the active gameplay screen.
	 */
	public PurposeMenuScreen(Stage currentStage, MainMenuScreen mainMenu, MainPlayScreen mainPlay)
	{
		currentGroup = new Group();
		this.currentStage = currentStage;
		
		sagaConnector = mainMenu.getMain().sagaConnector;
		logic = new PurposeMenuLogic (this, mainPlay);
		skin = new Skin(Gdx.files.internal("UISkin.json"));
		
		resumeButton 		= new ActionButton("ACCEPT", skin, mainMenu.startGameButton);
		resumeButton.setBounds(Gdx.graphics.getWidth()/2-150, 20, resumeButton.getWidth()/1.6f, resumeButton.getHeight()/2);
		
		PURPOSE_BG.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		firstImageListener = new ClickListener();
		secondImageListener = new ClickListener();
		thirdImageListener = new ClickListener();
		
		firstWindow = new Window("Accept?", skin);
		secondWindow = new Window("Duuude", skin);
		thirdWindow = new Window("IMAGE THREE", skin);
		
		windowSetup(firstWindow);
		windowSetup(secondWindow);
		windowSetup(thirdWindow);

		int height = Gdx.graphics.getHeight()/5;
		
		firstWindow.setBounds(Gdx.graphics.getWidth()/2.69f, height, Gdx.graphics.getWidth()/3.87f, Gdx.graphics.getHeight()/1.64f); //windows are roundly 330x440 on 720p (16:9 aspect ratio = 1280W x 720H)
		secondWindow.setBounds(Gdx.graphics.getWidth()/15.9f, height, Gdx.graphics.getWidth()/3.87f, Gdx.graphics.getHeight()/1.64f); 
		thirdWindow.setBounds(Gdx.graphics.getWidth()/1.46f, height, Gdx.graphics.getWidth()/3.87f, Gdx.graphics.getHeight()/1.64f); 

		logic.act();
	}

	/**
	 * This method is similar to the pause act one, it must be called when we want that the purpose become active.
	 */
	public void act()
	{
		currentGroup.addActor(PURPOSE_BG);
		currentGroup.addActor(resumeButton);
		currentStage.addActor(currentGroup);
		currentGroup.addActor(firstWindow);
		currentGroup.addActor(secondWindow);
		currentGroup.addActor(thirdWindow);
		getImageData();
	}
	
	/**
	 * PRIVATE METHOD : This method builds the purpose environment on this screen, setting up all the fields as needed.
	 * IT MUST BE CALLED after communications with the main server.
	 */
	private void buildPurposeEnvironment()
	{
		lemmaString.replace("_"," ");
		showedLemma = new Label(lemmaString, skin);
		showedLemma.setColor(Color.WHITE);
		showedLemma.setBounds(Gdx.graphics.getWidth()/2.3f, Gdx.graphics.getHeight()/8*7, firstWindow.getWidth()/1.1f, firstWindow.getWidth()/3.3f);
		
		glossString.trim();
		if ( glossString.length() >30) glossString.subSequence(0, 30);
		glossString+=" [...]";
		showedGloss = new Label(glossString, skin);
		showedGloss.setColor(Color.GRAY);
		showedGloss.setBounds(Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/9.3f, Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/7);
		
		firstAnnImage = new Image(firstAnnImageTexture);
		secondAnnImage = new Image(secondAnnImageTexture);
		thirdAnnImage = new Image(thirdAnnImageTexture);
		
		firstAnnImage.addListener(firstImageListener);
		secondAnnImage.addListener(secondImageListener);
		thirdAnnImage.addListener(thirdImageListener); 
		
		firstWindow.add(firstAnnImage);
		secondWindow.add(secondAnnImage);
		thirdWindow.add(thirdAnnImage);
		
		firstAnnImage.setBounds(		firstWindow.getWidth()/16.5f, firstWindow.getHeight()/4.16f,	firstWindow.getWidth()/1.1f, firstWindow.getHeight()/1.47f);
		secondAnnImage.setBounds(		firstWindow.getWidth()/16.5f, firstWindow.getHeight()/4.16f,	firstWindow.getWidth()/1.1f, firstWindow.getHeight()/1.47f);
		thirdAnnImage.setBounds(	firstWindow.getWidth()/16.5f, firstWindow.getHeight()/4.16f, 	firstWindow.getWidth()/1.1f, firstWindow.getHeight()/1.47f);
		
		currentGroup.addActor(showedLemma);
		currentGroup.addActor(showedGloss);
	}
	
	/**
	 * PRIVATE METHOD : This method is used to send annotation info to the main server.
	 *
	 * @param answer the answer given by the player.
	 * @param key the key of the answer given.
	 */
	private void sendAnnotation(boolean answer, String key)
	{
		try 
		{
			sagaConnector.sendAnnotation(annotationData, key, answer, new MainThreadCallback <Float>()
			{
				@Override
				public void onSuccessInMainThread(final Float result) {}
				
				@Override
				public void onFailureInMainThread(Throwable cause) { Gdx.app.error("2x004A", "ERROR 2x004A : Cannot send annotation to the main server.", cause);}
				
			});
		} catch (AuthenticationRequiredException e) { Gdx.app.error("2x004B", "ERROR 2x004B : Annotation sending error", e);	}
	}
	
	/**
	 * PRIVATE METHOD : This method gets the image data from the server.
	 * THIS IS CALLED EVERY TIME THAT UNDO IS PRESSED (As the entire class)
	 * @return the image data as ImageAnnotationData type.
	 */
	private void getImageData()
	{
		try 
		{
			sagaConnector.getImageData(DataType.IMAGE, 3, new MainThreadCallback<ImageAnnotationData>()
					{
						@Override
						public void onSuccessInMainThread(ImageAnnotationData result) { setData(result); }

						@Override 
						public void onFailureInMainThread(Throwable cause) { Gdx.app.error("2x001A", "NO REPLY RECEIVED FROM SERVER : Please check your internet connection", cause);	}
					});
		} catch (AuthenticationRequiredException e) { Gdx.app.error("2x001B", "NO REPLY RECEIVED FROM SERVER : Please check your internet connection; ", e);}
	}
	
	/**
	 *PRIVATE METHOD : Sets the data for each image given by the server, like names, glosses and informations about the images given.
	 * @param data the data just received (@see getImageData() method) }.
	 */
	private void setData( final ImageAnnotationData data)
	{
		Set<String> keys = data.getKeys();
		annotationsKeyArray = keys.toArray();

		for (int i=0 ; i<3; i++)
		{
			final int integer = i;
			String key = annotationsKeyArray[i].toString();
			data.getImageFromKey(key, new MainThreadCallback<Pixmap>()
				{
					@Override
					public void onSuccessInMainThread(final Pixmap result) 
					{
						Timer.schedule(new Task() 
						{
							@Override
							public void run() 
							{
								annotationData = data;
								if (integer == 0) firstAnnImageTexture	= new Texture (result);
								if (integer == 1) secondAnnImageTexture = new Texture (result);
								else			  thirdAnnImageTexture 	= new Texture (result);
								lemmaString = data.getLemma();
								glossString = data.getGloss();
							}
						}, 1f);
					}
					
					@Override 
					public void onFailureInMainThread(Throwable cause) 	{ Gdx.app.error("2x002A", "ERROR 2x002A : No image received by the server, couldn't build purpose menu", cause); }
				});
			
			Timer.schedule(new Task() 
			{
				@Override
				public void run() { buildPurposeEnvironment(); }
			}, 2f);
		}
	}

	@Override
	public void render(float delta) {}
	
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

	/**
	 * This method is an OVERLOAD of the basic dispose method (of the Screen type), the difference is that requires the answers of the annotation datas (this method is called by logic when submitting annotations).
	 * @param annotationsAnswers the array of annotations answers.
	 */
	public void dispose(boolean ... annotationsAnswers)
	{
		try { sagaConnector.sendStart(annotationData); }catch (AuthenticationRequiredException e) { Gdx.app.error("2x003A", "ERROR 2x003A : Annotation starting error", e); }

		for (int i=0 ; i<3 ; i++ ) sendAnnotation(annotationsAnswers[i], annotationsKeyArray[i].toString());
		
		Timer.schedule(new Task() 
		{
			@Override
			public void run() 
			{
				try { sagaConnector.sendEnd(annotationData); } catch (AuthenticationRequiredException e) { Gdx.app.error("2x005A", "ERROR 2x005A : Annotation ending error", e); }
				dispose();
			}
		}, 1.1f);
	}

	@Override
	public void dispose() 
	{
		if (firstAnnImage!=null && secondAnnImage!=null && thirdAnnImage!=null)
		{	
			firstAnnImage.clear();
			secondAnnImage.clear();
			thirdAnnImage.clear();
		}
		currentGroup.clear();
		currentGroup.remove();
	}
	
	/**
	 * PRIVATE METHOD : This method setups the windows, is called by the constructor to build the three windows, setting them unmovable, unresizable and without a specific layout.
	 * @param window the window to set up.
	 */
	private void windowSetup(Window window)
	{
		window.setMovable(false);
		window.setResizable(false);
		window.setLayoutEnabled(false);
	}
}
