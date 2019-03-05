package com.mynbri003.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mynbri003.game.BizarreBazaar;
import com.mynbri003.game.MusicPlayer;

public class OptionsScreen implements Screen {
    final BizarreBazaar game;

    //OrthographicCamera camera;
    AssetManager manager = new AssetManager();

    private Stage stage;
    MusicPlayer music;
    Button Save,Load,Back,Mute,Play;
    public OptionsScreen(final BizarreBazaar game,MusicPlayer music) {
        this.game = game;
        this.music = music;


}
    public void loadAssets(){
        manager.load("Visual/Buttons/Active/ActBtnSave.png", Texture.class);
        manager.load("Visual/Buttons/Active/ActBtnLoad.png", Texture.class);
        manager.load("Visual/Buttons/Active/ActBtnBack.png", Texture.class);

        manager.load("Visual/Buttons/Passive/PassBtnSave.png", Texture.class);
        manager.load("Visual/Buttons/Passive/PassBtnLoad.png", Texture.class);
        manager.load("Visual/Buttons/Passive/PassBtnBack.png", Texture.class);

        manager.load("Visual/Buttons/BtnMute.png", Texture.class);
        manager.load("Visual/Buttons/BtnPlayMusic.png", Texture.class);

        manager.load("Visual/OptionsBackGround.png", Texture.class);

        manager.load("Audio/btnClick.wav", Sound.class);
        manager.finishLoading();
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        loadAssets();
        createButtons();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(manager.get("Visual/OptionsBackGround.png",Texture.class),0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.end();
        stage.act();
        stage.draw();



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
        manager.dispose();
    }

    public void createButtons(){
        Texture upSave = manager.get("Visual/Buttons/Passive/PassBtnSave.png",Texture.class);
        Texture downSave = manager.get("Visual/Buttons/Active/ActBtnSave.png",Texture.class);
        Save = new Button(new TextureRegionDrawable(new TextureRegion(upSave)),new TextureRegionDrawable(new TextureRegion(downSave)));
        Save.setSize(upSave.getWidth(),upSave.getHeight());
        Save.setPosition(Math.round((Gdx.graphics.getWidth()/100)*42),Math.round((Gdx.graphics.getHeight()/100)*82));
        Save.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.prefs.flush();
                manager.get("Audio/btnClick.wav", Sound.class).play();
                return true;
            }
        });
        stage.addActor(Save);

        Texture upLoad = manager.get("Visual/Buttons/Passive/PassBtnLoad.png",Texture.class);
        Texture downLoad = manager.get("Visual/Buttons/Active/ActBtnLoad.png",Texture.class);
        Load = new Button(new TextureRegionDrawable(new TextureRegion(upLoad)),new TextureRegionDrawable(new TextureRegion(downLoad)));
        Load.setSize(upLoad.getWidth(),upLoad.getHeight());
        Load.setPosition(Math.round((Gdx.graphics.getWidth()/100)*42),Math.round((Gdx.graphics.getHeight()/100)*71));
        Load.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                manager.get("Audio/btnClick.wav", Sound.class).play();
                return true;
            }
        });
        stage.addActor(Load);

        Texture upMute = manager.get("Visual/Buttons/BtnMute.png",Texture.class);
        Texture downMute = manager.get("Visual/Buttons/BtnMute.png",Texture.class);
        Mute = new Button(new TextureRegionDrawable(new TextureRegion(upMute)),new TextureRegionDrawable(new TextureRegion(downMute)));
        Mute.setSize(upMute.getWidth(),upMute.getHeight());
        Mute.setPosition(Math.round((Gdx.graphics.getWidth()/100)*53.5),Math.round((Gdx.graphics.getHeight()/100)*60));
        Mute.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.prefs.putBoolean("music",false);

                music.play();
                manager.get("Audio/btnClick.wav", Sound.class).play();
                return true;
            }
        });
        stage.addActor(Mute);

        Texture upPlay = manager.get("Visual/Buttons/BtnPlayMusic.png",Texture.class);
        Texture downPlay = manager.get("Visual/Buttons/BtnPlayMusic.png",Texture.class);
        Play = new Button(new TextureRegionDrawable(new TextureRegion(upPlay)),new TextureRegionDrawable(new TextureRegion(downPlay)));
        Play.setSize(upPlay.getWidth(),upPlay.getHeight());
        Play.setPosition(Math.round((Gdx.graphics.getWidth()/100)*42),Math.round((Gdx.graphics.getHeight()/100)*60));
        Play.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.prefs.putBoolean("music",true);
                music.play();
                manager.get("Audio/btnClick.wav", Sound.class).play();
                return true;
            }
        });
        stage.addActor(Play);

        Texture upBack = manager.get("Visual/Buttons/Passive/PassBtnBack.png",Texture.class);
        Texture downBack = manager.get("Visual/Buttons/Active/ActBtnBack.png",Texture.class);
        Back = new Button(new TextureRegionDrawable(new TextureRegion(upBack)),new TextureRegionDrawable(new TextureRegion(downBack)));
        Back.setSize(upBack.getWidth(),upBack.getHeight());
        Back.setPosition(Math.round((Gdx.graphics.getWidth()/100)*42),Math.round((Gdx.graphics.getHeight()/100)*10));
        Back.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new ShopScreen(game,music));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                manager.get("Audio/btnClick.wav", Sound.class).play();
                return true;
            }
        });
        stage.addActor(Back);

    }
}
