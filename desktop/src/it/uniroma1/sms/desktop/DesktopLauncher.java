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
package it.uniroma1.sms.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import it.uniroma1.sms.SMSMain;

/**
 * This is the core LwjglApp launcher.
 * 
 * @author  Ricciardino
 * @version 0.1, 24 July 2014
 * @see     com.badlogic.gdx.backends.lwjgl.LwjglApplication
 */
public class DesktopLauncher 
{
	public static void main (String[] arg) 
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Silly Monkey Solitaire : www.petriccarcc.com";
		config.samples = 8;
		config.width = 1280;
	    config.height = 720;
	    config.fullscreen = false;
		config.vSyncEnabled = true;
		config.addIcon("SMSIcon.png", Files.FileType.Internal);
		new LwjglApplication(new SMSMain(), config);
	}
}
