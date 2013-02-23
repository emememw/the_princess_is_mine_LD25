package com.emveyh.ld25.entity;

import com.badlogic.gdx.Gdx;
import com.emveyh.ld25.AudioManager;
import com.emveyh.ld25.TextureManager;

public class HealthUpgrade extends Upgrade {

	private int upgradeAmount = 2;
	private float lifeTime = 0;
	private float deathTrigger = 10;
	
	public HealthUpgrade(float x, float y) {
		super(x, y, 1, 1, TextureManager.getInstance().getSprites()[11][0]);
		this.setShowHealthBar(false);
	}

	
	
	@Override
	protected void tickLogic() {
		
		if(this.lifeTime < deathTrigger) {
			this.lifeTime += Gdx.graphics.getDeltaTime();
		} else {
			this.upgradeAmount = 0;
			EntityManager.getInstance().unregisterEntity(this);
		}
		
	}



	@Override
	public void onHit(Entity entity) {

		if(entity instanceof PlayerEntity) {
			entity.setHp(entity.getHp()+upgradeAmount);
			AudioManager.getInstance().getUpgrade().play();
			this.upgradeAmount = 0;
			EntityManager.getInstance().unregisterEntity(this);
			
		}
		
	}
	
}
