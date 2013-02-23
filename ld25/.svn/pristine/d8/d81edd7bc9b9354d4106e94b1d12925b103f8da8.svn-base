package com.emveyh.ld25.entity;

import com.badlogic.gdx.Gdx;
import com.emveyh.ld25.AudioManager;
import com.emveyh.ld25.TextureManager;

public class DmgUpgrade extends Upgrade {

	private int upgradeAmount = 1;
	private float lifeTime = 0;
	private float deathTrigger = 10;

	public DmgUpgrade(float x, float y) {
		super(x, y, 1, 1, TextureManager.getInstance().getSprites()[10][0]);
		this.setShowHealthBar(false);
	}

	@Override
	protected void tickLogic() {

		if (this.lifeTime < deathTrigger) {
			this.lifeTime += Gdx.graphics.getDeltaTime();
		} else {
			this.upgradeAmount = 0;
			EntityManager.getInstance().unregisterEntity(this);
		}

	}

	@Override
	public void onHit(Entity entity) {

		if (entity instanceof PlayerEntity) {
			if (entity.getDmg() < 3) {
				entity.setDmg(entity.getDmg() + upgradeAmount);
			}
			AudioManager.getInstance().getUpgrade().play();
			this.upgradeAmount = 0;
			EntityManager.getInstance().unregisterEntity(this);

		}

	}

}
