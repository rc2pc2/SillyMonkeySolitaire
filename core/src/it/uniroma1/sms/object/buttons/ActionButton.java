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
package it.uniroma1.sms.object.buttons;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * This is a customized version of a basic button, ActionButton means that the button have to do something after being pressed.
 * 
 * @author  Ricciardino
 * @version 0.1, 5 August 2014
 * @see     com.badlogic.gdx.scenes.scene2d.ui.TextButton
 */
public class ActionButton extends ButtonObject  
{
	/**
	 * The name, or title, of the action button instance.
	 */
	public String name;
	
	/**
	 * Basic constructor of the class
	 * @param text It will be the name of the created action button
	 * @param skin this will be the skin used by the button
	 */
	public ActionButton(String text, Skin skin) 
	{
		super(text, skin);
		name = text;
	}
	
	/**
	 * Alternative constructor of the class, that allows to replace a given button with a new one, taking its positioning and dimensions.
	 * @param text It will be the name of the created action button
	 * @param skin this will be the skin used by the button
	 * @param toReplaceButton the button to replace
	 */
	public ActionButton (String text, Skin skin, Button toReplaceButton)
	{
		this(text, skin);
		this.setBounds(toReplaceButton.getX(), toReplaceButton.getY(), toReplaceButton.getWidth(), toReplaceButton.getHeight());
	}
}
