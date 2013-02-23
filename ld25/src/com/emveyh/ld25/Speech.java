package com.emveyh.ld25;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Speech {

	private BitmapFont font;
	private String text = "";
	private String currentText = "";
	private int currentIndex = -1;
	
	private float x;
	private float y;
	private float width;
	
	private float textTime = 0;
	private float textTrigger = 0.5f;
	
	private float lifeTime = 0;
	
	private float scale;
	private Color color;
	
	public Speech(BitmapFont font, String text, float x, float y, float width, float scale, Color color, float speed) {
		
		this.font = font;
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.scale = scale;
		this.color = color;
		this.textTrigger = speed;
	}
	
	public void tick() {
		lifeTime += Gdx.graphics.getDeltaTime();
		
		
		if(textTime < textTrigger) {
			textTime += Gdx.graphics.getDeltaTime();
		} else if(currentIndex < text.length()-1) {
			textTime = 0;
			currentIndex++;
			if(currentIndex < text.length()) {
				currentText += text.toCharArray()[currentIndex];
			}
		}
		
	}
	
	public void render(SpriteBatch batch) {
		font.setScale(scale);
		font.setColor(color);
		font.drawWrapped(batch, currentText, x, y, width);
		
	}
	
	public void setNewText(String newText) {
		this.currentText = "";
		this.currentIndex = -1;
		this.text = newText;
		this.lifeTime = 0;
	}
	
	public boolean isEndOfText() {
		boolean result = false;
		if(currentIndex == text.length()-1) {
			result = true;
		}
		return result;
	}

	public float getLifeTime() {
		return lifeTime;
	}
	
	
	
}
