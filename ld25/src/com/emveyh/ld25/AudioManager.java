package com.emveyh.ld25;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioManager {

	private static final AudioManager INSTANCE = new AudioManager();
	
	public static AudioManager getInstance() {
		return AudioManager.INSTANCE;
	}
	
	private AudioManager() {}
	
	private Sound enemieAttack;
	private Sound enemieDeath;
	private Sound nextWave;
	private Sound playerDeath;
	private Sound playerAttack;
	private Sound upgrade;
	private Sound upgradeSpawn;
	
	private Music music;

	
	public void init() {
		
		enemieAttack = Gdx.audio.newSound(Gdx.files.internal("data/enemieAttack.wav"));
		enemieDeath = Gdx.audio.newSound(Gdx.files.internal("data/enemieDeath.wav"));
		nextWave = Gdx.audio.newSound(Gdx.files.internal("data/nextWave.wav"));
		playerDeath = Gdx.audio.newSound(Gdx.files.internal("data/playerDeath.wav"));
		playerAttack = Gdx.audio.newSound(Gdx.files.internal("data/playerAttack.wav"));
		upgrade = Gdx.audio.newSound(Gdx.files.internal("data/upgrade.wav"));
		upgradeSpawn = Gdx.audio.newSound(Gdx.files.internal("data/upgradeSpawn.wav"));
		
		music = Gdx.audio.newMusic(Gdx.files.internal("data/music.ogg"));
		music.setLooping(true);
		music.setVolume(0.7f);
		
	}

	public Sound getEnemieAttack() {
		return enemieAttack;
	}

	public Sound getEnemieDeath() {
		return enemieDeath;
	}

	public Sound getNextWave() {
		return nextWave;
	}

	public Sound getPlayerDeath() {
		return playerDeath;
	}

	public Sound getPlayerAttack() {
		return playerAttack;
	}

	public Sound getUpgrade() {
		return upgrade;
	}

	public Sound getUpgradeSpawn() {
		return upgradeSpawn;
	}

	public Music getMusic() {
		return music;
	}
	
	
	
	
	
}
