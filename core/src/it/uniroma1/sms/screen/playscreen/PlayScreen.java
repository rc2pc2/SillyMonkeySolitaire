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

import com.badlogic.gdx.Screen;
/**
 * Abstract class needed for future modifications of the mainplayscreen class, if needed. (Currently used just for the update method)
 * @author Ricciardino
 *
 */
public abstract class PlayScreen implements Screen 
{
	public abstract void update(float delta);
}
