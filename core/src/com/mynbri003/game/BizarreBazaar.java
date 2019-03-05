package com.mynbri003.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mynbri003.game.states.BattleScreen;
import com.mynbri003.game.states.ShopScreen;


public class BizarreBazaar extends Game {

	public static final String TITLE = "Bizarre Bazaar";

	public SpriteBatch batch;

	public Preferences prefs;

	MusicPlayer music;


	
	@Override
	public void create () {
		batch = new SpriteBatch();
		prefs = Gdx.app.getPreferences("My Preferences");
		music = new MusicPlayer(this);
		music.setMain(0);
		music.play();

		this.setScreen(new ShopScreen(this,this.music));

	}

	@Override
	public void render () {
		super.render();


	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}

	public void fight(String Enemy){
		this.setScreen(new BattleScreen(this,this.music));

	}




}
