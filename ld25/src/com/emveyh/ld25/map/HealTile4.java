package com.emveyh.ld25.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.emveyh.ld25.TextureManager;
import com.emveyh.ld25.entity.PlayerEntity;

public class HealTile4 extends Tile {

	public HealTile4(int x, int y) {
		super(TextureManager.getInstance().getTiles()[3][1], true, x, y);
	}
	
	private float healTime = 0;
	private float healTrigger = 1;
	
	@Override
	protected void tickLogic() {
		if(healTime < healTrigger) {
			healTime += Gdx.graphics.getDeltaTime();
		} else {
			healTime = 0;
			
			if(this.getEntity() != null && this.getEntity() instanceof PlayerEntity) {
				((PlayerEntity)this.getEntity()).heal(5);
				
			}
		}
		
	}

}
