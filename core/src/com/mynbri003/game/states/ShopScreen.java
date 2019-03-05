package com.mynbri003.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mynbri003.game.BizarreBazaar;
import com.mynbri003.game.MusicPlayer;

public class ShopScreen implements Screen {
    final BizarreBazaar game;

    //OrthographicCamera camera;
    AssetManager manager = new AssetManager();

    private Stage stage;
    MusicPlayer music;
    float buttonWidth = Math.round(Gdx.graphics.getWidth()/100*1.5);


    ImageButton Credits,Inventory,Market,Mercenaries,Rival,Status,Options,Exit;

    public ShopScreen(final BizarreBazaar game,MusicPlayer music) {
        this.game = game;
        this.music = music;
    }
    public void loadAssets(){
        manager.load("Visual/Buttons/Active/ActBtnCredits.png",Texture.class);
        manager.load("Visual/Buttons/Active/ActBtnInventory.png",Texture.class);
        manager.load("Visual/Buttons/Active/ActBtnMarket.png",Texture.class);
        manager.load("Visual/Buttons/Active/ActBtnMercenaries.png",Texture.class);
        manager.load("Visual/Buttons/Active/ActBtnRival.png",Texture.class);
        manager.load("Visual/Buttons/Active/ActBtnStatus.png",Texture.class);

        manager.load("Visual/Buttons/Passive/PassBtnCredits.png",Texture.class);
        manager.load("Visual/Buttons/Passive/PassBtnInventory.png",Texture.class);
        manager.load("Visual/Buttons/Passive/PassBtnMarket.png",Texture.class);
        manager.load("Visual/Buttons/Passive/PassBtnMercenaries.png",Texture.class);
        manager.load("Visual/Buttons/Passive/PassBtnRival.png",Texture.class);
        manager.load("Visual/Buttons/Passive/PassBtnStatus.png",Texture.class);

        manager.load("Visual/Buttons/BtnOptions.png",Texture.class);
        manager.load("Visual/Buttons/BtnX.png",Texture.class);
        manager.load("Visual/Main_FrameWork.png",Texture.class);

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
        //camera.update();

        //game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(manager.get("Visual/Main_FrameWork.png",Texture.class),0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        game.batch.draw(manager.get("Visual/Buttons/Passive/PassBtnInventory.png",Texture.class),30,800);
//        game.batch.draw(manager.get("Visual/Buttons/Passive/PassBtnMarket.png",Texture.class),30,690);
//        game.batch.draw(manager.get("Visual/Buttons/Passive/PassBtnMercenaries.png",Texture.class),30,580);
//        game.batch.draw(manager.get("Visual/Buttons/Passive/PassBtnRival.png",Texture.class),30,470);
//        game.batch.draw(manager.get("Visual/Buttons/Passive/PassBtnStatus.png",Texture.class),30,360);
//        game.batch.draw(manager.get("Visual/Buttons/Passive/PassBtnCredits.png",Texture.class),30,250);
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
        stage.dispose();
    }
    public void createButtons(){
        Texture upInv = manager.get("Visual/Buttons/Passive/PassBtnInventory.png",Texture.class);
        Texture downInv = manager.get("Visual/Buttons/Active/ActBtnInventory.png",Texture.class);
        Inventory = new ImageButton(new TextureRegionDrawable(new TextureRegion(upInv)),new TextureRegionDrawable(new TextureRegion(downInv)));
        Inventory.setSize(upInv.getWidth(),upInv.getHeight());
        Inventory.setPosition(buttonWidth,Math.round((Gdx.graphics.getHeight()/100)*82));
        Inventory.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                music.unMute();
                manager.get("Audio/btnClick.wav",Sound.class).play();
                return true;
            }
        });
        stage.addActor(Inventory);

        Texture upMarket = manager.get("Visual/Buttons/Passive/PassBtnMarket.png",Texture.class);
        Texture downMarket = manager.get("Visual/Buttons/Active/ActBtnMarket.png",Texture.class);
        Market = new ImageButton(new TextureRegionDrawable(new TextureRegion(upMarket)),new TextureRegionDrawable(new TextureRegion(downMarket)));
        Market.setSize(upMarket.getWidth(),upMarket.getHeight());
        Market.setPosition(buttonWidth,Math.round((Gdx.graphics.getHeight()/100)*71));
        Market.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

                manager.get("Audio/btnClick.wav",Sound.class).play();
                return true;
            }
        });
        stage.addActor(Market);

        Texture upMercenaries = manager.get("Visual/Buttons/Passive/PassBtnMercenaries.png",Texture.class);
        Texture downMercenaries = manager.get("Visual/Buttons/Active/ActBtnMercenaries.png",Texture.class);
        Mercenaries = new ImageButton(new TextureRegionDrawable(new TextureRegion(upMercenaries)),new TextureRegionDrawable(new TextureRegion(downMercenaries)));
        Mercenaries.setSize(upMercenaries.getWidth(),upMercenaries.getHeight());
        Mercenaries.setPosition(buttonWidth,Math.round((Gdx.graphics.getHeight()/100)*60));
        Mercenaries.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MercenariesScreen(game,music));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                manager.get("Audio/btnClick.wav",Sound.class).play();
                return true;
            }
        });
        stage.addActor(Mercenaries);

        Texture upRival = manager.get("Visual/Buttons/Passive/PassBtnRival.png",Texture.class);
        Texture downRival = manager.get("Visual/Buttons/Active/ActBtnRival.png",Texture.class);
        Rival = new ImageButton(new TextureRegionDrawable(new TextureRegion(upRival)),new TextureRegionDrawable(new TextureRegion(downRival)));
        Rival.setSize(upRival.getWidth(),upRival.getHeight());
        Rival.setPosition(buttonWidth,Math.round((Gdx.graphics.getHeight()/100)*49));
        Rival.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                manager.get("Audio/btnClick.wav",Sound.class).play();

                return true;
            }
        });
        stage.addActor(Rival);

        Texture upStatus = manager.get("Visual/Buttons/Passive/PassBtnStatus.png",Texture.class);
        Texture downStatus = manager.get("Visual/Buttons/Active/ActBtnStatus.png",Texture.class);
        Status = new ImageButton(new TextureRegionDrawable(new TextureRegion(upStatus)),new TextureRegionDrawable(new TextureRegion(downStatus)));
        Status.setSize(upInv.getWidth(),upInv.getHeight());
        Status.setPosition(buttonWidth,Math.round((Gdx.graphics.getHeight()/100)*38));
        Status.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                manager.get("Audio/btnClick.wav",Sound.class).play();
                return true;
            }
        });
        stage.addActor(Status);

        Texture upCredits = manager.get("Visual/Buttons/Passive/PassBtnCredits.png",Texture.class);
        Texture downCredits = manager.get("Visual/Buttons/Active/ActBtnCredits.png",Texture.class);
        Credits = new ImageButton(new TextureRegionDrawable(new TextureRegion(upCredits)),new TextureRegionDrawable(new TextureRegion(downCredits)));
        Credits.setSize(upCredits.getWidth(),upCredits.getHeight());
        Credits.setPosition(buttonWidth,Math.round((Gdx.graphics.getHeight()/100)*27));
        Credits.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                manager.get("Audio/btnClick.wav",Sound.class).play();
                return true;
            }
        });
        stage.addActor(Credits);

        Texture BtnExit = manager.get("Visual/Buttons/BtnX.png",Texture.class);
        Exit = new ImageButton(new TextureRegionDrawable(new TextureRegion(BtnExit)));
        Exit.setSize(BtnExit.getWidth(),BtnExit.getHeight());
        Exit.setPosition(Math.round((Gdx.graphics.getWidth()/100)*92),Math.round((Gdx.graphics.getHeight()/100)*4.5));
        Exit.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                manager.get("Audio/btnClick.wav",Sound.class).play();
                System.exit(-1);
            }
        });
        stage.addActor(Exit);

        Texture BtnOptions = manager.get("Visual/Buttons/BtnOptions.png",Texture.class);
        Options = new ImageButton(new TextureRegionDrawable(new TextureRegion(BtnOptions)));
        Options.setSize(Options.getWidth(),Options.getHeight());
        Options.setPosition(Math.round((Gdx.graphics.getWidth()/100)*86.5),Math.round((Gdx.graphics.getHeight()/100)*4.5));
        Options.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                manager.get("Audio/btnClick.wav",Sound.class).play();
                game.setScreen(new OptionsScreen(game,music));
            }
        });
        stage.addActor(Options);
    }
}
