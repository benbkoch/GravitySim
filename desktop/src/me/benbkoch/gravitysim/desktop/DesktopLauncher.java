package me.benbkoch.gravitysim.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import me.benbkoch.gravitysim.GravitySim;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Gravity Simulator by Ben Koch";
		config.width = 1920;
		config.height = 1080;
		new LwjglApplication(new GravitySim(), config);
	}
}
