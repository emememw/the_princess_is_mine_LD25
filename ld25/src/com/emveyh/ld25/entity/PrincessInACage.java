package com.emveyh.ld25.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.emveyh.ld25.GameState;
import com.emveyh.ld25.Globals;
import com.emveyh.ld25.SpeechManager;
import com.emveyh.ld25.TextureManager;

public class PrincessInACage extends PlayerEntity{

	public PrincessInACage() {
		super(12, 6, "");
		this.hp = 20;
		this.setCurrentHp(this.hp);
		this.setAnimationTextures(new TextureRegion[]{TextureManager.getInstance().getSprites()[3][0]});
		this.setCurrentAnimationIndex(0);
		this.changeAnimationTexture(0);
		
	}
	
	@Override
	public void onSelection() {}
	
	@Override
	public void onDeath() {
		//System.out.println("game over");
		Globals.getInstance().changeGameState(GameState.GAME_OVER);
	}
	
	@Override
	public void tickLogic() {
		
	}

	@Override
	public void onHit(Entity entity) {
		if(entity instanceof BasicEnemy) {
			this.setCurrentHp(this.getCurrentHp()-1);
		}
	}
	
	

}
