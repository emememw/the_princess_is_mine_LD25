package com.emveyh.ld25.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.emveyh.ld25.Globals;
import com.emveyh.ld25.MapCoord;
import com.emveyh.ld25.map.MapManager;
import com.emveyh.ld25.map.Tile;

public class Entity extends Sprite {

	private TextureRegion[] animationTextures;
	private int currentAnimationIndex;
	protected float speed;

	private MapCoord currentDestination;
	private Array<MapCoord> currentPath;
	private int currentPathIndex = 0;

	private float attackTime = 0;
	private float attackTrigger = 1;
	protected boolean canAttack;

	private boolean moving = false;

	private boolean needNewPath;

	protected int hp;
	private int currentHp;
	protected int dmg;
	
	private double hpPercent;

	private boolean doneMoving = false;
	
	private boolean showHealthBar = true;

	public Entity(float x, float y, int hp, int dmg, TextureRegion... animationTextures) {
		this.setX(x * Globals.getInstance().getTileSize());
		this.setY(y * Globals.getInstance().getTileSize());
		this.setSize(Globals.getInstance().getTileSize(), Globals.getInstance().getTileSize());
		this.animationTextures = animationTextures;
		this.changeAnimationTexture(0);
		this.speed = 400f;
		this.hp = hp;
		this.setCurrentHp(hp);
		this.dmg = dmg;
	}

	protected void changeAnimationTexture(int index) {
		this.currentAnimationIndex = index;
		this.setRegion(animationTextures[index]);
	}

	public boolean moveHorizontal(float speed) {
		this.setX(this.getX() + (speed * Gdx.graphics.getDeltaTime()));
		moving = true;
		return true;
	}

	public boolean moveVertical(float speed) {
		this.setY(this.getY() + (speed * Gdx.graphics.getDeltaTime()));
		this.moving = true;
		return true;
	}

	public void moveToTile(int x, int y, float speed) {

		moving = false;

		Tile collisionTile = null;

		if (MapManager.getInstance().getMap()[x][y].occupiedByOtherEntity(this)) {
			// !!!
			needNewPath = true;

		} else {

			if (this.getX() < x * Globals.getInstance().getTileSize()) {
				collisionTile = MapManager.getInstance().detectCollision((int) (this.getX() + speed * Gdx.graphics.getDeltaTime()), (int) this.getY(),
						Globals.getInstance().getTileSize(), Globals.getInstance().getTileSize(), this);

				if (collisionTile == null) {
					moveHorizontal(speed);
					if (this.getX() + (speed * Gdx.graphics.getDeltaTime()) > x * Globals.getInstance().getTileSize()) {
						this.setX(x * Globals.getInstance().getTileSize());
					}

				} else {
					this.setX(collisionTile.getX() - Globals.getInstance().getTileSize());
				}
			} else if (this.getX() > x * Globals.getInstance().getTileSize()) {
				collisionTile = MapManager.getInstance().detectCollision((int) this.getX() - speed * Gdx.graphics.getDeltaTime(), (int) this.getY(),
						Globals.getInstance().getTileSize(), Globals.getInstance().getTileSize(), this);

				if (collisionTile == null) {
					moveHorizontal(-speed);
					if (this.getX() - (speed * Gdx.graphics.getDeltaTime()) < x * Globals.getInstance().getTileSize() + Globals.getInstance().getTileSize()) {
						// this.setX(x * Globals.getInstance().getTileSize());
					}
				} else {
					this.setX(collisionTile.getX() + Globals.getInstance().getTileSize());
				}
			}

			if (!moving) {
				if (this.getY() > y * Globals.getInstance().getTileSize()) {
					collisionTile = MapManager.getInstance().detectCollision((int) this.getX(), (int) this.getY() - speed * Gdx.graphics.getDeltaTime(),
							Globals.getInstance().getTileSize(), Globals.getInstance().getTileSize(), this);

					if (collisionTile == null) {
						moveVertical(-speed);
						if (this.getY() - speed * Gdx.graphics.getDeltaTime() < y * Globals.getInstance().getTileSize()) {
							this.setY(y * Globals.getInstance().getTileSize());
						}
					} else {
						this.setY(collisionTile.getY() + Globals.getInstance().getTileSize());
					}
				} else if (this.getY() < y * Globals.getInstance().getTileSize()) {
					collisionTile = MapManager.getInstance().detectCollision((int) this.getX(), (int) (this.getY() + speed * Gdx.graphics.getDeltaTime()),
							Globals.getInstance().getTileSize(), Globals.getInstance().getTileSize(), this);

					if (collisionTile == null) {
						moveVertical(speed);
						if (this.getY() + (speed * Gdx.graphics.getDeltaTime()) > y * Globals.getInstance().getTileSize()) {
							this.setY(y * Globals.getInstance().getTileSize());
						}
					} else {
						this.setY(collisionTile.getY() - Globals.getInstance().getTileSize());
					}
				}
			}
		}

	}

	public void tick() {

		if (this.getCurrentHp() > 0) {

			needNewPath = false;

			if (attackTime < attackTrigger) {
				canAttack = false;
				attackTime += Gdx.graphics.getDeltaTime();
			} else {
				canAttack = true;
				attackTime = 0;
			}

			if (currentPath != null && currentPath.size > 0 && currentDestination == null) {
				currentDestination = currentPath.get(currentPathIndex);
				if (currentPathIndex == currentPath.size - 1) {
					currentPath = null;
				} else {
					currentPathIndex++;
				}
			}

			if (currentDestination != null) {
				doneMoving = false;
				this.moveToTile(currentDestination.getX(), currentDestination.getY(), this.speed);

				if (this.getX() == currentDestination.getX() * Globals.getInstance().getTileSize()
						&& this.getY() == currentDestination.getY() * Globals.getInstance().getTileSize()) {
					currentDestination = null;
					if (currentPath == null) {
						doneMoving = true;
					}

				}
			}

			MapManager.getInstance().getTileAtFloatPoint(this.getX(), this.getY()).setEntity(this);

			tickLogic();
		} else {
			onDeath();
		}
	}

	public void move(MapCoord destination) {
		this.currentPath = AiManager.getInstance().aStar(
				new MapCoord((int) this.getX() / Globals.getInstance().getTileSize(), (int) this.getY() / Globals.getInstance().getTileSize()), destination,
				this);
		this.currentPathIndex = 0;
	}

	protected void tickLogic() {

	}

	public Array<Tile> getSurroundingTiles(int radius, boolean filled) {

		Array<Tile> surroundingTiles = new Array<Tile>();
		if (!filled) {
			if ((int) this.getX() / Globals.getInstance().getTileSize() - radius >= 0) {
				surroundingTiles.add(MapManager.getInstance().getMap()[(int) (this.getX() / Globals.getInstance().getTileSize()) - radius][(int) this.getY()
						/ Globals.getInstance().getTileSize()]);
			}
			if ((int) this.getX() / Globals.getInstance().getTileSize() + radius < MapManager.getInstance().getMap().length) {
				surroundingTiles.add(MapManager.getInstance().getMap()[(int) (this.getX() / Globals.getInstance().getTileSize()) + radius][(int) this.getY()
						/ Globals.getInstance().getTileSize()]);
			}
			if ((int) this.getY() / Globals.getInstance().getTileSize() - radius >= 0) {
				surroundingTiles.add(MapManager.getInstance().getMap()[(int) (this.getX() / Globals.getInstance().getTileSize())][(int) (this.getY() / Globals
						.getInstance().getTileSize()) - radius]);
			}
			if ((int) this.getY() / Globals.getInstance().getTileSize() + radius < MapManager.getInstance().getMap()[0].length) {
				surroundingTiles.add(MapManager.getInstance().getMap()[(int) (this.getX() / Globals.getInstance().getTileSize())][(int) (this.getY() / Globals
						.getInstance().getTileSize()) + radius]);
			}
		} else {

			int startX = (int) this.getX() / Globals.getInstance().getTileSize() - radius;
			int startY = (int) (this.getY() / Globals.getInstance().getTileSize()) + radius;

			int endX = (int) this.getX() / Globals.getInstance().getTileSize() + radius;
			int endY = (int) this.getY() / Globals.getInstance().getTileSize() - radius;

			if (startX < 0) {
				startX = 0;
			}
			if (startY < 0) {
				startY = 0;
			}

			if (endX > MapManager.getInstance().getMap().length - 1) {
				endX = MapManager.getInstance().getMap().length - 1;
			}
			if (endY > MapManager.getInstance().getMap()[0].length - 1) {
				endY = MapManager.getInstance().getMap()[0].length - 1;
			}

			for (int sx = startX; sx <= endX; sx++) {
				for (int sy = startY; sy >= endY; sy--) {
					if (sx >= 0 && sy >= 0 && sx < MapManager.getInstance().getMap().length && sy < MapManager.getInstance().getMap()[0].length) {
						if (sx != (int) this.getX() / Globals.getInstance().getTileSize() || sy != (int) this.getY() / Globals.getInstance().getTileSize()) {
							surroundingTiles.add(MapManager.getInstance().getMap()[sx][sy]);
						}
					}

				}
			}

		}

		return surroundingTiles;
	}

	public Array<Entity> getSurroundingEntities(int radius, boolean filled) {

		Array<Entity> surroundingEntities = new Array<Entity>();

		Array<Tile> surroundingTiles = getSurroundingTiles(radius, filled);
		for (Tile tile : surroundingTiles) {
			if (tile.getEntity() != null && tile.getEntity() != this) {
				surroundingEntities.add(tile.getEntity());
			}
		}

		return surroundingEntities;
	}

	public Array<MapCoord> getCurrentPath() {
		return currentPath;
	}

	public void onSelection() {

	}

	public MapCoord getCurrentDestination() {
		return currentDestination;
	}

	public boolean isMoving() {
		return moving;
	}

	public boolean isNeedNewPath() {
		return needNewPath;
	}

	public int getCurrentAnimationIndex() {
		return currentAnimationIndex;
	}

	public void setCurrentAnimationIndex(int currentAnimationIndex) {
		this.currentAnimationIndex = currentAnimationIndex;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		this.currentHp = currentHp;
		this.hpPercent = this.currentHp * 100 / this.hp;
	}

	public int getDmg() {
		return dmg;
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}

	public void onHit(Entity entity) {

	}

	public void onDeath() {

	}

	public boolean isDoneMoving() {
		return doneMoving;
	}

	public TextureRegion[] getAnimationTextures() {
		return animationTextures;
	}

	public void setAnimationTextures(TextureRegion[] animationTextures) {
		this.animationTextures = animationTextures;
	}

	public double getHpPercent() {
		return hpPercent;
	}

	public boolean isShowHealthBar() {
		return showHealthBar;
	}

	public void setShowHealthBar(boolean showHealthBar) {
		this.showHealthBar = showHealthBar;
	}
	
	
	
	

}
