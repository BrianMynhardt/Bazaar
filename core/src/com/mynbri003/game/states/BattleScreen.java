package com.mynbri003.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mynbri003.game.BizarreBazaar;
import com.mynbri003.game.MusicPlayer;

public class BattleScreen implements Screen {
    final BizarreBazaar game;

    AssetManager manager = new AssetManager();

    private Stage stage;
    Texture BackGround;
    MusicPlayer music;

    public BattleScreen(final BizarreBazaar game,MusicPlayer music) {
        this.game = game;
        this.music = music;


    }
    public void loadAssets(){
        manager.load("Visual/FightBackGround.png", Texture.class);

        manager.finishLoading();
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);


        loadAssets();
        BackGround = manager.get("Visual/FightBackGround.png",Texture.class);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(BackGround,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
