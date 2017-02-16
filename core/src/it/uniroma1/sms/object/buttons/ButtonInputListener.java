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

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * This is the listener used for the buttons in this entire game, it is not customized yet, but it allows new programmers to do that.
 * 
 * The listener interface for receiving buttonInput events.
 * The class that is interested in processing a buttonInput
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addButtonInputListener<code> method. When
 * the buttonInput event occurs, that object's appropriate
 * method is invoked.
 *
 * @see ButtonInputEvent
 */
public class ButtonInputListener extends ClickListener
{
	/**
	 * Main constructor that instantiates a new button input listener.
	 */
	public ButtonInputListener() { super(); }
	
	@Override
	public void clicked (InputEvent event, float x, float y) {}
	
	@Override
    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)  { return true; }
}
