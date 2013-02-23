package com.emveyh.ld25;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class SpeechManager {

	private static final SpeechManager INSTANCE = new SpeechManager();
	
	public static SpeechManager getInstance() {
		return SpeechManager.INSTANCE;
	}
	
	private SpeechManager() {}
	private BitmapFont font;
	
	private Speech overlordSpeech;
	
	public void init() {
		speeches.clear();
		this.font = Globals.getInstance().getFont();
		overlordSpeech = new Speech(font, OverlordSpeeches.values()[new Random().nextInt(OverlordSpeeches.values().length)].getText(), 315, 460, 400, 0.5f, Color.WHITE, 0.05f);
		this.registerSpeech(overlordSpeech);
	}
	
	private Array<Speech> speeches = new Array<Speech>();
	
	public void registerSpeech(Speech speech) {
		speeches.add(speech);
	}
	
	public void unregisterSpeech(Speech speech) {
		speeches.removeValue(speech, true);
	}
	
	public void tick() {
		
		if(overlordSpeech != null && overlordSpeech.getLifeTime() > 10) {
			overlordSpeech.setNewText(OverlordSpeeches.values()[new Random().nextInt(OverlordSpeeches.values().length)].getText());
		}
		
		
		for(Speech speech : speeches) {
			speech.tick();
		}
	}
	
	public void render(SpriteBatch batch)  {
		for(Speech speech : speeches) {
			speech.render(batch);
		}
	}
	
	public void clear() {
		speeches.clear();
	}

	public Array<Speech> getSpeeches() {
		return speeches;
	}

	public void setSpeeches(Array<Speech> speeches) {
		this.speeches = speeches;
	}
	
	public boolean allSpeechesDone() {
		boolean result = true;
		for(Speech speech : speeches) {
			if(!speech.isEndOfText()) {
				result = false;
				break;
			}
		}
		return result;
	}
	
	
}
