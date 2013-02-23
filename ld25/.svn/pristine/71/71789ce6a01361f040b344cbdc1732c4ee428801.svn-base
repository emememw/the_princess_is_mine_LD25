package com.emveyh.ld25.entity;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.emveyh.ld25.AudioManager;
import com.emveyh.ld25.Globals;
import com.emveyh.ld25.MapCoord;
import com.emveyh.ld25.TextureManager;
import com.emveyh.ld25.map.MapManager;

public class BasicEnemy extends Entity {

	private MapCoord finalDestination = new MapCoord(12, 6);
	private MapCoord destination;
	
	private Entity huntedEntity;
	
	private Random random = new Random();
	
	public BasicEnemy(float x, float y, EnemyType enemyType) {
		super(x, y, 2, 2, TextureManager.getInstance().getSprites()[enemyType.getTextureX()][enemyType.getTextureY()]);
		this.speed = 64;
		this.setHp(enemyType.getHp());
		this.setCurrentHp(this.getHp());
		this.setDmg(enemyType.getDmg());
		destination = finalDestination;
		
		int randomTexture = new Random().nextInt(100);
		
		if(randomTexture < 5) {
			this.setAnimationTextures(new TextureRegion[]{TextureManager.getInstance().getSprites()[9][1]});
			this.changeAnimationTexture(0);
		} else if(randomTexture < 10) {
			this.setAnimationTextures(new TextureRegion[]{TextureManager.getInstance().getSprites()[0][1]});
			this.changeAnimationTexture(0);
		} else if(randomTexture < 20) {
			this.setAnimationTextures(new TextureRegion[]{TextureManager.getInstance().getSprites()[4][1]});
			this.changeAnimationTexture(0);
		}
		
	}

	@Override
	public void tickLogic() {
		Array<Entity> closeEntities = this.getSurroundingEntities(1, false);
		if (canAttack) {
			
			if (closeEntities.size > 0) {
				for (Entity entity : closeEntities) {
					if (entity instanceof PlayerEntity) {
						entity.onHit(this);
						AudioManager.getInstance().getEnemieAttack().play();
						break;
					}
				}
			}
		}
		
		
		Array<Entity> surroundingEntities = this.getSurroundingEntities(2, true);
		if(this.isNeedNewPath()) {
			this.huntedEntity = null;
			this.destination = finalDestination;
		}
		
		if(surroundingEntities.size > 0 && (huntedEntity == null || huntedEntity.getCurrentHp() <= 0 || (this.isDoneMoving() && closeEntities.size == 0))) {
		for(Entity entity : surroundingEntities) {
			if(entity instanceof PlayerEntity) {
				this.destination = new MapCoord((int)entity.getX()/Globals.getInstance().getTileSize(), (int)entity.getY()/Globals.getInstance().getTileSize());
				huntedEntity = MapManager.getInstance().getMap()[destination.getX()][destination.getY()].getEntity();
				break;
			}
		}
		} else {
			if(huntedEntity != null && surroundingEntities.size == 0) {
				huntedEntity = null;
				destination = finalDestination;
			}
		}
		
		
		
		if(destination != null) {
 			this.move(destination);
			destination = null;
		}
		

	}

	@Override
	public void onHit(Entity entity) {

		this.setCurrentHp(this.getCurrentHp()- entity.getDmg());
	}

	@Override
	public void onDeath() {
		EntityManager.getInstance().unregisterEntity(this);
		AudioManager.getInstance().getEnemieDeath().play();
	}

}
