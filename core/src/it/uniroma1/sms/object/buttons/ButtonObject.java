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

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Abstract class of all the customized buttons, actually pretty unused.
 * @author  Ricciardino
 * @version 0.1, 5 August 2014
 * @see com.badlogic.gdx.Gdx
 *
 */
public abstract class ButtonObject extends TextButton 
{
	public ButtonObject(String text, Skin skin) { super(text, skin); }

	public ButtonObject(String text, TextButtonStyle style)  { super(text, style); }
	
	public ButtonObject(String text, Skin skin, String styleName) { super(text, skin, styleName); }
}
