package com.emveyh.ld25;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TitleScreen {

	private static final TitleScreen INSTANCE = new TitleScreen();
	
	public static TitleScreen getInstance() {
		return TitleScreen.INSTANCE;
	}
	
	private TitleScreen() {}
	
	private int hoveredIndex = -1;
	private boolean showInstructions = false;
	private boolean canClose = false;
	
	public void render(SpriteBatch batch) {
		
		if(!showInstructions) {
		Globals.getInstance().getFont().setColor(Color.WHITE);
		Globals.getInstance().getFont().setScale(1f);
		Globals.getInstance().getFont().draw(batch, "- THE PRINCESS IS MINE -", 10, 400);
		Globals.getInstance().getFont().setScale(0.5f);
		
		if(hoveredIndex == 1) {
			Globals.getInstance().getFont().setColor(Color.ORANGE);
		} else {
			Globals.getInstance().getFont().setColor(Color.WHITE);
		}
		Globals.getInstance().getFont().draw(batch, "start new game", 100, 300);
		if(hoveredIndex == 2) {
			Globals.getInstance().getFont().setColor(Color.ORANGE);
		} else {
			Globals.getInstance().getFont().setColor(Color.WHITE);
		}
		Globals.getInstance().getFont().draw(batch, "instructions", 100, 250);
		if(hoveredIndex == 3) {
			Globals.getInstance().getFont().setColor(Color.ORANGE);
		} else {
			Globals.getInstance().getFont().setColor(Color.WHITE);
		}
		Globals.getInstance().getFont().draw(batch, "toggle music", 100, 200);
		if(hoveredIndex == 4) {
			Globals.getInstance().getFont().setColor(Color.ORANGE);
		} else {
			Globals.getInstance().getFont().setColor(Color.WHITE);
		}
		Globals.getInstance().getFont().draw(batch, "quit", 100, 150);
		
		batch.draw(TextureManager.getInstance().getSprites()[2][1], 500, 160, 32, 32);
		batch.draw(TextureManager.getInstance().getSprites()[5][1], 515, 125, 32, 32);
		batch.draw(TextureManager.getInstance().getSprites()[7][1], 470, 145, 32, 32);
		batch.draw(TextureManager.getInstance().getSprites()[10][1], 485, 100, 32, 32);
		
		batch.draw(TextureManager.getInstance().getSprites()[3][0], 650, 150, 32, 32);
		batch.draw(TextureManager.getInstance().getSprites()[0][0], 690, 160, 32, 32);
		batch.draw(TextureManager.getInstance().getSprites()[0][0], 680, 125, 32, 32);
		batch.draw(TextureManager.getInstance().getSprites()[8][0], 660, 190, 32, 32);
		
		Globals.getInstance().getFont().setColor(Color.WHITE);
		Globals.getInstance().getFont().setScale(0.5f);
		Globals.getInstance().getFont().draw(batch, "Ludum Dare 25 - by emveyh - December 2012", 60, 20);
		canClose = false;
		} else {
			canClose = true;
			batch.draw(TextureManager.getInstance().getInstructionTexture(), 0,0);
			Globals.getInstance().getFont().setColor(Color.WHITE);
			Globals.getInstance().getFont().setScale(0.5f);
			Globals.getInstance().getFont().drawMultiLine(batch, "protect\nher!", 270, 130);
			Globals.getInstance().getFont().drawMultiLine(batch, "click on a minion to \nselect it, then click to walk", 100, 320);
			Globals.getInstance().getFont().drawMultiLine(batch, "kill these by \nwalking next\nto them", 20, 130);
			Globals.getInstance().getFont().drawMultiLine(batch, "move next to\nupgrades to\ntake them", 600, 300);
			Globals.getInstance().getFont().setColor(Color.GREEN);
			Globals.getInstance().getFont().drawMultiLine(batch, "< back to title screen", 10, 470);
		}
		
	}
	
	public void tick() {
		
	}
	
	public void setHoveredIndex(int index){
		this.hoveredIndex = index;
	}

	public int getHoveredIndex() {
		return hoveredIndex;
	}
	
	public void newGameClicked() {
		Globals.getInstance().changeGameState(GameState.PLAYING);
	}
	
	public void toggleMusicClicked() {
		Globals.getInstance().toggleMusic();
	}
	
	public void quitClicked() {
		Gdx.app.exit();
	}
	
	public void instructionClicked() {
		showInstructions = true;
	}

	public boolean isShowInstructions() {
		return showInstructions;
	}

	public void setShowInstructions(boolean showInstructions) {
		this.showInstructions = showInstructions;
	}

	public boolean isCanClose() {
		return canClose;
	}

	public void setCanClose(boolean canClose) {
		this.canClose = canClose;
	}
	
	
	
}
