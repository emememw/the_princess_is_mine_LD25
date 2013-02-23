package com.emveyh.ld25;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.emveyh.ld25.entity.EntityManager;
import com.emveyh.ld25.map.MapManager;

public class Globals {

	private static final Globals INSTANCE = new Globals();

	public static Globals getInstance() {
		return Globals.INSTANCE;
	}

	private Globals() {
	}

	private int tileSize = 32;

	private BitmapFont font;

	private GameState state = GameState.PLAYING;
	
	private boolean musicOn = true;

	public int getTileSize() {
		return tileSize;
	}

	public GameState getState() {
		return state;
	}

	public void startNewGame() {
		EntityManager.getInstance().init();
		MapManager.getInstance().init();
		SpeechManager.getInstance().init();
	}

	public BitmapFont getFont() {
		return font;
	}

	public void setFont(BitmapFont font) {
		this.font = font;
	}

	public void changeGameState(GameState state) {
		if (this.state != state) {
			this.state = state;
			
			SpeechManager.getInstance().clear();
			if(this.state == GameState.PLAYING) {
				startNewGame();
			} else if (this.state == GameState.GAME_OVER) {
				
				SpeechManager.getInstance().registerSpeech(new Speech(font, "the princess is save now ... you lost!", 50, 450, 700, 1f,Color.WHITE, 0.05f));
			} else if (this.state == GameState.WON) {
				SpeechManager.getInstance().registerSpeech(new Speech(font, "the princess is yours ... congratulations! you won!", 50, 450, 700, 1f,Color.WHITE, 0.05f));
			}
		}

	}

	public boolean isMusicOn() {
		return musicOn;
	}

	public void setMusicOn(boolean musicOn) {
		this.musicOn = musicOn;
	}
	
	public void toggleMusic() {
		this.musicOn = !musicOn;
		if(this.musicOn) {
			AudioManager.getInstance().getMusic().setVolume(1f);
		} else {
			AudioManager.getInstance().getMusic().setVolume(0);
		}
	}

}
