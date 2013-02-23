package com.emveyh.ld25;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "ld25";
		cfg.useGL20 = true;
		cfg.width = 800;
		cfg.height = 480;
		cfg.title = "the princess is mine";
		
		new LwjglApplication(new GdxGame(), cfg);
	}
}
