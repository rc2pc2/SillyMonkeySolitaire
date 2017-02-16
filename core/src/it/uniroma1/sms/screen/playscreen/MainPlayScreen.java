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
package it.uniroma1.sms.screen.playscreen;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import it.uniroma1.lcl.saga.api.Callback;
import it.uniroma1.lcl.saga.api.SaGaConnector;
import it.uniroma1.lcl.saga.api.exceptions.AuthenticationRequiredException;
import it.uniroma1.sms.SMSMain;
import it.uniroma1.sms.decorator.CardDecorator;
import it.uniroma1.sms.gamelogic.gameplay.Card;
import it.uniroma1.sms.gamelogic.gameplay.DeckGameLogic;
import it.uniroma1.sms.gamelogic.gameplay.EnvironmentLogic;
import it.uniroma1.sms.gamelogic.gameplay.ScoreManager;
import it.uniroma1.sms.screen.menuscreen.MainMenuScreen;
import it.uniroma1.sms.screen.menuscreen.PauseMenuScreen;
import it.uniroma1.sms.screen.menuscreen.PurposeMenuScreen;

/**
 * This is the main play screen class, it will arrange the general visive part of the gameplay screen.
 * Note that for specified decorators and logical algorithms there are several different classes,
 * feel free to check them if there is some hard thing to understand.
 * 
 * @author  Ricciardino
 * @version 0.1, 19 August 2014
 *
 */
public class MainPlayScreen extends PlayScreen 
{
	public ScoreManager scoreManager;
	public boolean isPlayerPlaying;
	
	private SaGaConnector sagaConnector;
	
	private SMSMain main;
	private MainMenuScreen mainMenu;
	private PauseMenuScreen pausedMenu;
	private PurposeMenuScreen purposeMenu;
	private boolean isPaused;
	private boolean isPurposeActive;
	
	private Stage stage;
	private Music flipSound;
	
	private Skin skin;
	
	private EnvironmentLogic gameLogic;
	private DeckGameLogic deckLogic;

	private LinkedList<CardDecorator> tableCardsDecorators;
	private LinkedList<CardDecorator> handCardsDecorators;
	private int deckCounter;
	private CardDecorator deckDeco;

	private Group backgroundG;
	private Group foregroundG;
	private Group foregroundG2;
	private Image bg;
	
	private Label scoreLabel;
	private Label highScoreLabel;
	
	private Card handCard;
	private Image handCardImage;
	
	private LinkedList<Card> deck;
	private LinkedList<CardDecorator> historyList;
	
	private Image newGameImage;  
	private Image purposeImage;
	private ClickListener purposeListener;
	
	private final static Image DECK_END = 			new Image (new Texture ( Gdx.files.internal("cards/deckEnd.png")));
	private final static Texture BG_TEXTURE = 		new Texture (Gdx.files.internal("elements/bg1.jpg"));
	private final static Texture NG_TEXTURE = 		new Texture (Gdx.files.internal("elements/bg1.jpg")); // TO CHANGE
	private final static Texture PURPOSE_TEXTURE = 	new Texture (Gdx.files.internal("elements/undoImage250.png"));
	
	/**
	 * Basic constructor of MainPlayScreen, just to initialize variables and to set this screen as main one.
	 * @param main Giving to the class the SMSMain class to change the current screen.
	 */
	public MainPlayScreen(SMSMain main, MainMenuScreen mainMenu)
	{
		this.main = main;
		this.mainMenu = mainMenu;
		sagaConnector = main.sagaConnector;
		
		flipSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/flip.mp3"));
		
		scoreManager = new ScoreManager();
		skin = new Skin(Gdx.files.internal("UISkin.json"));
		scoreLabel = new Label("SCORE : " + scoreManager.getScore(), skin);
		highScoreLabel = new Label("HIGHSCORE : ", skin);

		deckLogic = new DeckGameLogic();
		deck = deckLogic.getDeck();
		gameLogic = new EnvironmentLogic( Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), deck);
		historyList = new LinkedList<CardDecorator>();
		
		stage = new Stage();
		backgroundG = new Group();
		foregroundG = new Group();
		foregroundG2 = new Group();
		
		backgroundG.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		foregroundG.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		foregroundG2.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		stage.addActor(backgroundG);
		stage.addActor(foregroundG);
		stage.addActor(foregroundG2);
		foregroundG.addActor(scoreLabel);
		foregroundG.addActor(highScoreLabel);
		
		deckLogic.resetGame();
		decoSetup();
		
		purposeImage = new Image ( PURPOSE_TEXTURE );
		purposeListener = new ClickListener();
		purposeImage.addCaptureListener(purposeListener);
		
		purposeImage.setBounds(Gdx.graphics.getWidth()/9, Gdx.graphics.getHeight() / 100 , 250, 221);
		
		newGameImage = new Image( NG_TEXTURE );
		newGameImage.addCaptureListener(new ClickListener());
		newGameImage.setBounds(Gdx.graphics.getWidth()/2 - 270, Gdx.graphics.getHeight() / 5 , 270, 90);
		
		bg = new Image ( BG_TEXTURE );
		bg.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		backgroundG.addActor(bg);
		
		scoreLabel.setBounds(Gdx.graphics.getWidth()/15, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/14, Gdx.graphics.getWidth()/8,  Gdx.graphics.getHeight()/12);
		highScoreLabel.setBounds(Gdx.graphics.getWidth()-Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/14, Gdx.graphics.getWidth()/8,  Gdx.graphics.getHeight()/12);
		
		Gdx.input.setInputProcessor(stage);
		main.setCurrentScreen(this);
		
		pausedMenu = new PauseMenuScreen(stage, mainMenu, this);
		purposeMenu = new PurposeMenuScreen(stage, mainMenu, this);
	}
	
	@Override
	public void render(float delta) { update(delta); }

	/**
	 * This is one of the most important methods of any Screen-able class, it allows the render to update it with a limited number of FramesPerSecond (FPS),
	 * BE AWARE that modifying this method could cause giant problems in terms of RAM and CPU usage, please read Libgdx tutorials and some stuff about the buffering of OpenGL before doing it.
	 */
	public void update(float delta) 
	{
		stage.act(delta);
		stage.draw();
		
		if (!isPaused || !isPurposeActive)
		{
			deckUpdate();
			tableUpdate();
			checkRows();
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE) || Gdx.input.isKeyPressed(Input.Keys.P))  pause();
		if (purposeListener.isPressed()) 
		{
			purposeShow();
			undoEngine(); 
		}  
	}
	
	private void decoSetup()
	{
		tableCardsDecorators = gameLogic.getCardsOnTableDecorators(); 
		handCardsDecorators = gameLogic.getCardsOnHandDecorators();
		
		for (CardDecorator deco : tableCardsDecorators)	foregroundG2.addActor(deco.getDisplayedImage());
		
		handCard = handCardsDecorators.getLast().getCard();
		handCardImage = handCardsDecorators.getLast().getDisplayedImage();
			
		foregroundG2.addActor(handCardImage);
		deckDeco = new CardDecorator(handCard, handCardsDecorators.getFirst().getX(), handCardsDecorators.getFirst().getY(), handCardsDecorators.getFirst().getWidth(), handCardsDecorators.getFirst().getHeight());
		deckDeco.coverCard();
		foregroundG2.addActor(deckDeco.getDisplayedImage());
		highScoreGetter();
	}
	

	private void deckUpdate()
	{
		if (handCardsDecorators.size() == deckCounter) 
		{
			highScoreSender();
			deckCounter = 0;
			handCardImage.clearListeners();
			deckDeco.coverCard().setDrawable(DECK_END.getDrawable());
			deckDeco.getDisplayedImage().setDrawable(DECK_END.getDrawable());
			
	    	MainPlayScreen newGame = new MainPlayScreen (main, mainMenu);
	    	newGame.setScore(scoreManager.getScore());
	    	newGame.scoreLabel.setText("SCORE: "+ scoreManager.getScore());
	    	main.setCurrentScreen(newGame);
	    	this.dispose();
		}
		else if (deckDeco.getCardListener().isPressed()  && !flipSound.isPlaying()) //  
		{
			turnCard(handCardsDecorators.get(deckCounter));
			deckCounter++;
			scoreManager.resetMulti();
		}
	}

	private void tableUpdate()
	{
		for (CardDecorator deco : tableCardsDecorators)
		{
			if (deco.getCardListener().isPressed() && !deco.isCovered())
			{
				LinkedList<Card> rightChoices = deckLogic.getRightChoices(handCard);
				if(rightChoices.contains(Card.valueOf(deco.getCard()+"")))
				{
					turnCard(deco);
					deco.flip();
					addScore();
					deco.getDisplayedImage().setVisible(false);
					flipSound.play();
				}
			}
		}
	}

	private void turnCard(CardDecorator deco)
	{
		historyList.add(new CardDecorator(handCard));
		historyList.add(deco);
		handCard = deco.getCard();
		handCardImage.setDrawable(deco.getDisplayedImage().getDrawable());
		flipSound.play();
	}
	
	private void undoEngine()
	{
		CardDecorator oldReplacedDeco = historyList.get(historyList.size()-1);
		CardDecorator oldHandCard = historyList.get(historyList.size()-2);
		
		oldReplacedDeco.getDisplayedImage().setVisible(true);
		handCard = oldHandCard.getCard();
		handCardImage.setDrawable(oldHandCard.getDisplayedImage().getDrawable());
		
		flipSound.play();
	}

	private void addScore()
	{
		if (!isPlayerPlaying) 
			{
				foregroundG2.addActor(purposeImage);
				isPlayerPlaying = true;
			}
		
		scoreManager.addScore();
		scoreManager.setHighScore(scoreManager.getScore());
		scoreLabel.setText("SCORE: "+ scoreManager.getScore());
		highScoreLabel.setText("HIGHSCORE: "+ scoreManager.getHighScore());
	}
	
	/**
	 * Basic Skin type getter
	 * @return the skin used in the entire framework
	 */
	public Skin getSkin() { return skin; }

	@Override
	public void resize(int width, int height) {}

	@Override
	public void hide() {}

	@Override
	public void pause() 
	{
		isPaused = true;
		pausedMenu.act();
	}

	@Override
	public void resume() 
	{
		isPaused = false;
		pausedMenu.hide();
		Gdx.input.setInputProcessor(stage);
	}
	
	public void purposeShow()
	{
		isPurposeActive = true;
		purposeMenu.act();
	}
	
	public void purposeHide()
	{
		isPurposeActive = false;
		purposeMenu.hide();
		Gdx.input.setInputProcessor(stage);
	}

	public DeckGameLogic getGameLogic() { return deckLogic; }
	
	@Override
	public void dispose() 
	{
		stage.dispose();
		pausedMenu.dispose();
		purposeMenu.dispose();
	}

	@Override
	public void show() {}
	
	public void setScore(int score) { this.scoreManager.setScore(score); }
	
	private void highScoreSender()
	{
		try {sagaConnector.sendUserHighscore(scoreManager.getHighScore());
		} catch (AuthenticationRequiredException e)  {  Gdx.app.error("2x007A", "NO REPLY RECEIVED FROM SERVER : Cannot send highscore. ", e); }
	}
	
	private void highScoreGetter()
	{
		try 
		{ 
			sagaConnector.getUserHighscore(new Callback<Integer>()
					{
						@Override
						public void onSuccess(final Integer result) 
						{
							Timer.schedule(new Task() 
							{
								@Override
								public void run() 
								{ 
									scoreManager.setHighScore(result);
									highScoreLabel.setText("HIGHSCORE : " + scoreManager.getHighScore());
								}
							}, 0.8f);
						}
						@Override
						public void onFailure(Throwable cause) { Gdx.app.error("2x006A", "Can't receive highscore from server", cause); }
			});
		} catch (AuthenticationRequiredException e)  {  Gdx.app.error("2x006B", "NO REPLY RECEIVED FROM SERVER : HIGHSCORE request denied. ", e); }
	}
	
	private void checkRows()
	{
		if (tableCardsDecorators.get(18).isFlipped() && tableCardsDecorators.get(19).isFlipped() && tableCardsDecorators.get(20).isFlipped() && tableCardsDecorators.get(21).isFlipped())
		{
			tableCardsDecorators.get(9).uncoverCard();
			tableCardsDecorators.get(10).uncoverCard();
			tableCardsDecorators.get(11).uncoverCard();
		}
		if (tableCardsDecorators.get(21).isFlipped() && tableCardsDecorators.get(22).isFlipped() && tableCardsDecorators.get(23).isFlipped() && tableCardsDecorators.get(24).isFlipped())
		{
			tableCardsDecorators.get(3).uncoverCard();
			tableCardsDecorators.get(4).uncoverCard();
			tableCardsDecorators.get(5).uncoverCard();
		}
		if (tableCardsDecorators.get(24).isFlipped() && tableCardsDecorators.get(25).isFlipped() && tableCardsDecorators.get(26).isFlipped() && tableCardsDecorators.get(27).isFlipped())
		{
			tableCardsDecorators.get(15).uncoverCard();
			tableCardsDecorators.get(16).uncoverCard();
			tableCardsDecorators.get(17).uncoverCard();
		}
		if (tableCardsDecorators.get(3).isFlipped() && tableCardsDecorators.get(4).isFlipped() && tableCardsDecorators.get(5).isFlipped())
		{
			tableCardsDecorators.get(1).uncoverCard();
			tableCardsDecorators.get(2).uncoverCard();
		}
		if (tableCardsDecorators.get(9).isFlipped() && tableCardsDecorators.get(10).isFlipped() && tableCardsDecorators.get(11).isFlipped())
		{
			tableCardsDecorators.get(7).uncoverCard();
			tableCardsDecorators.get(8).uncoverCard();
		}
		if (tableCardsDecorators.get(15).isFlipped() && tableCardsDecorators.get(16).isFlipped() && tableCardsDecorators.get(17).isFlipped())
		{
			tableCardsDecorators.get(13).uncoverCard();
			tableCardsDecorators.get(14).uncoverCard();
		}
		if (tableCardsDecorators.get(1).isFlipped() && tableCardsDecorators.get(2).isFlipped()) 		tableCardsDecorators.get(0).uncoverCard();
		if (tableCardsDecorators.get(7).isFlipped() && tableCardsDecorators.get(8).isFlipped())	 	tableCardsDecorators.get(6).uncoverCard();
		if (tableCardsDecorators.get(13).isFlipped() && tableCardsDecorators.get(14).isFlipped()) 		tableCardsDecorators.get(12).uncoverCard();
	}
}
