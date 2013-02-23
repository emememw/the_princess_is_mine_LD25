package com.emveyh.ld25.map;

import com.emveyh.ld25.TextureManager;

public class WallTile extends Tile {

	public WallTile(int x, int y) {
		super(TextureManager.getInstance().getTiles()[0][0], false, x,y);
	}

}
