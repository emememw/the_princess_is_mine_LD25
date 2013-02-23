package com.emveyh.ld25.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.emveyh.ld25.Globals;
import com.emveyh.ld25.MapCoord;
import com.emveyh.ld25.Renderable;
import com.emveyh.ld25.entity.Entity;

public class Tile extends Rectangle implements Renderable {
	
	private TextureRegion texture;
	private boolean accessible;
	private float size;
	private MapCoord mapCoord;
	private Entity entity;
	
	
	public Tile(TextureRegion texture, boolean accessible, int x, int y) {
		this.texture = texture;
		this.accessible = accessible;
		this.size = Globals.getInstance().getTileSize();
		this.setWidth(size);
		this.setHeight(size);
		this.setX(x*size);
		this.setY(y*size);
		mapCoord = new MapCoord(x,y);
		
	}
	
	@Override
	public void render(SpriteBatch batch, float x, float y) {
		batch.draw(texture, x, y, size, size);
	}

	public boolean isAccessible() {
		return accessible;
	}

	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}

	public TextureRegion getTexture() {
		return texture;
	}
	
	public MapCoord getMapCoord() {
		return this.mapCoord;
				
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	public void tick() {
		
		if(entity != null && MapManager.getInstance().getTileAtFloatPoint(entity.getX(), entity.getY()) != this) {
			entity = null;
		}
		
		
		
		tickLogic();
	}
	
	protected void tickLogic() {
		
	}
	
	public boolean occupiedByOtherEntity(Entity askingEntity) {
		boolean result = false;
		if(this.entity != null && this.entity != askingEntity) {
			result = true;
		}
		return result;
	}
	
	public Array<Tile> getSurroundingTiles(int radius) {
		
		Array<Tile> surroundingTiles = new Array<Tile>();
		if (this.getX()-radius > 0) {
			surroundingTiles.add(MapManager.getInstance().getMap()[this.mapCoord.getX() - radius][this.mapCoord.getY()]);
		}
		if (this.getX()+radius < MapManager.getInstance().getMap().length * Globals.getInstance().getTileSize()) {
			surroundingTiles.add(MapManager.getInstance().getMap()[this.mapCoord.getX() + radius][this.mapCoord.getY()]);
		}
		if (this.getY()-radius > 0) {
			surroundingTiles.add(MapManager.getInstance().getMap()[this.mapCoord.getX()][this.getMapCoord().getY() - radius]);
		}
		if (this.getY()+radius < MapManager.getInstance().getMap()[0].length * Globals.getInstance().getTileSize()) {
			surroundingTiles.add(MapManager.getInstance().getMap()[this.getMapCoord().getX()][this.getMapCoord().getY() + radius]);
		}
		
		return surroundingTiles;
	}
	
	
	
	
	
	

}
