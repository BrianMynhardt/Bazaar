package com.mynbri003.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
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

public class MercenariesScreen implements Screen {
    final BizarreBazaar game;

    AssetManager manager = new AssetManager();
    Button Hunter,Knight,Forest,Mountains,Desert,Back,Explore;
    boolean classSelected,mapSelected;
    int chosenMap;

    private Stage stage;
    Texture BackGround;
    MusicPlayer music;
    float buttonWidth = Math.round(Gdx.graphics.getWidth()/100*1.5);
    public MercenariesScreen(final BizarreBazaar game,MusicPlayer music) {
        this.game = game;
        this.music = music;
        this.classSelected = false;
        this.mapSelected = false;
    }
    public void loadAssets(){
        manager.load("Visual/MercenariesBackGround.png", Texture.class);
        manager.load("Visual/ForestBackGround.png", Texture.class);
        manager.load("Visual/MountainsBackGround.png", Texture.class);
        manager.load("Visual/DesertBackGround.png", Texture.class);

        manager.load("Visual/Buttons/Active/ActBtnKnight.png",Texture.class);
        manager.load("Visual/Buttons/Active/ActBtnHunter.png",Texture.class);
        manager.load("Visual/Buttons/Active/ActBtnForest.png",Texture.class);
        manager.load("Visual/Buttons/Active/ActBtnMountains.png",Texture.class);
        manager.load("Visual/Buttons/Active/ActBtnDesert.png",Texture.class);
        manager.load("Visual/Buttons/Active/ActBtnBack.png",Texture.class);
        manager.load("Visual/Buttons/Active/ActBtnExplore.png",Texture.class);

        manager.load("Visual/Buttons/Passive/PassBtnKnight.png",Texture.class);
        manager.load("Visual/Buttons/Passive/PassBtnHunter.png",Texture.class);
        manager.load("Visual/Buttons/Passive/PassBtnForest.png",Texture.class);
        manager.load("Visual/Buttons/Passive/PassBtnMountains.png",Texture.class);
        manager.load("Visual/Buttons/Passive/PassBtnDesert.png",Texture.class);
        manager.load("Visual/Buttons/Passive/PassBtnBack.png",Texture.class);
        manager.load("Visual/Buttons/Passive/PassBtnExplore.png",Texture.class);

        manager.load("Visual/Buttons/BtnOptions.png",Texture.class);

        manager.load("Audio/btnClick.wav", Sound.class);
        manager.finishLoading();
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);



//        camera = new OrthographicCamera();
//        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        loadAssets();

        BackGround = manager.get("Visual/MercenariesBackGround.png",Texture.class);
        createButtons();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //camera.update();
        Explore();
        //game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(BackGround,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
        Texture upKnight = manager.get("Visual/Buttons/Passive/PassBtnKnight.png",Texture.class);
        Texture downKnight = manager.get("Visual/Buttons/Active/ActBtnKnight.png",Texture.class);
        Knight = new Button(new TextureRegionDrawable(new TextureRegion(upKnight)),new TextureRegionDrawable(new TextureRegion(downKnight)),new TextureRegionDrawable(new TextureRegion(downKnight)));
        Knight.setSize(upKnight.getWidth(),upKnight.getHeight());
        Knight.setPosition(buttonWidth,Math.round((Gdx.graphics.getHeight()/100)*82));
        Knight.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(Knight.isChecked()){
                    Knight.setChecked(false);
                    classSelected = false;
                }else{
                    Hunter.setChecked(false);
                   classSelected = true;

                }

                manager.get("Audio/btnClick.wav",Sound.class).play();
                return true;
            }
        });

        Texture upHunter = manager.get("Visual/Buttons/Passive/PassBtnHunter.png",Texture.class);
        Texture downHunter = manager.get("Visual/Buttons/Active/ActBtnHunter.png",Texture.class);
        Hunter = new Button(new TextureRegionDrawable(new TextureRegion(upHunter)),new TextureRegionDrawable(new TextureRegion(downHunter)),new TextureRegionDrawable(new TextureRegion(downHunter)));
        Hunter.setSize(upHunter.getWidth(),upHunter.getHeight());
        Hunter.setPosition(buttonWidth,Math.round((Gdx.graphics.getHeight()/100)*71));
        Hunter.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(Hunter.isChecked()){
                    Hunter.setChecked(false);
                    classSelected = false;
                }else{
                    Knight.setChecked(false);
                    classSelected = true;

                }

                manager.get("Audio/btnClick.wav",Sound.class).play();
                return true;
            }
        });



        Texture upForest = manager.get("Visual/Buttons/Passive/PassBtnForest.png",Texture.class);
        Texture downForest = manager.get("Visual/Buttons/Active/ActBtnForest.png",Texture.class);
        Forest = new Button(new TextureRegionDrawable(new TextureRegion(upForest)),new TextureRegionDrawable(new TextureRegion(downForest)),new TextureRegionDrawable(new TextureRegion(downForest)));
        Forest.setSize(upForest.getWidth(),upForest.getHeight());
        Forest.setPosition(Math.round((Gdx.graphics.getWidth()/100)*83.5),Math.round((Gdx.graphics.getHeight()/100)*82));
        Forest.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {


            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(Forest.isChecked()){
                    Forest.setChecked(false);
                    mapSelected = false;
                }else{
                    Mountains.setChecked(false);
                    Desert.setChecked(false);
                    chosenMap = 1;
                    mapSelected = true;

                }
                manager.get("Audio/btnClick.wav",Sound.class).play();
                BackGround = manager.get("Visual/ForestBackGround.png",Texture.class);
                return true;
            }
        });


        Texture upMountains = manager.get("Visual/Buttons/Passive/PassBtnMountains.png",Texture.class);
        Texture downMountains = manager.get("Visual/Buttons/Active/ActBtnMountains.png",Texture.class);
        Mountains = new Button(new TextureRegionDrawable(new TextureRegion(upMountains)),new TextureRegionDrawable(new TextureRegion(downMountains)),new TextureRegionDrawable(new TextureRegion(downMountains)));
        Mountains.setSize(upMountains.getWidth(),upMountains.getHeight());
        Mountains.setPosition(Math.round((Gdx.graphics.getWidth()/100)*83.5),Math.round((Gdx.graphics.getHeight()/100)*71));
        Mountains.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(Mountains.isChecked()){
                    Mountains.setChecked(false);
                    mapSelected = false;
                }else{
                    Forest.setChecked(false);
                    Desert.setChecked(false);
                    chosenMap = 2;
                    mapSelected = true;

                }
                manager.get("Audio/btnClick.wav",Sound.class).play();
                BackGround = manager.get("Visual/MountainsBackGround.png",Texture.class);


                return true;
            }
        });


        Texture upDesert = manager.get("Visual/Buttons/Passive/PassBtnDesert.png",Texture.class);
        Texture downDesert = manager.get("Visual/Buttons/Active/ActBtnDesert.png",Texture.class);
        Desert = new Button(new TextureRegionDrawable(new TextureRegion(upDesert)),new TextureRegionDrawable(new TextureRegion(downDesert)),new TextureRegionDrawable(new TextureRegion(downDesert)));
        Desert.setSize(upDesert.getWidth(),upDesert.getHeight());
        Desert.setPosition(Math.round((Gdx.graphics.getWidth()/100)*83.5),Math.round((Gdx.graphics.getHeight()/100)*60));
        Desert.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(Desert.isChecked()){
                    Desert.setChecked(false);
                    mapSelected = false;
                }else{
                    Mountains.setChecked(false);
                    Forest.setChecked(false);
                    chosenMap = 3;
                    mapSelected = true;

                }
                manager.get("Audio/btnClick.wav",Sound.class).play();
                BackGround = manager.get("Visual/DesertBackGround.png",Texture.class);
                return true;
            }
        });



        stage.addActor(Knight);
        stage.addActor(Hunter);
        stage.addActor(Forest);
        stage.addActor(Mountains);
        stage.addActor(Desert);

        Texture upBack = manager.get("Visual/Buttons/Passive/PassBtnBack.png",Texture.class);
        Texture downBack = manager.get("Visual/Buttons/Active/ActBtnBack.png",Texture.class);
        Back = new Button(new TextureRegionDrawable(new TextureRegion(upBack)),new TextureRegionDrawable(new TextureRegion(downBack)));
        Back.setSize(upBack.getWidth(),upBack.getHeight());
        Back.setPosition(Math.round((Gdx.graphics.getWidth()/100)*83.5),Math.round((Gdx.graphics.getHeight()/100)*16));
        Back.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new ShopScreen(game,music));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                manager.get("Audio/btnClick.wav",Sound.class).play();
                return true;
            }
        });
        stage.addActor(Back);



    }
    public void Explore(){
        if (mapSelected && classSelected) {
            Texture upExplore = manager.get("Visual/Buttons/Passive/PassBtnExplore.png", Texture.class);
            Texture downExplore = manager.get("Visual/Buttons/Active/ActBtnExplore.png", Texture.class);
            Explore = new Button(new TextureRegionDrawable(new TextureRegion(upExplore)), new TextureRegionDrawable(new TextureRegion(downExplore)));
            Explore.setSize(upExplore.getWidth(), upExplore.getHeight());
            Explore.setPosition(Math.round((Gdx.graphics.getWidth() / 100) * 65.3), Math.round((Gdx.graphics.getHeight() / 100) * 3.35));
            Explore.addListener(new InputListener() {
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {


                }

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                    manager.get("Audio/btnClick.wav", Sound.class).play();
                    game.setScreen(new ExploreScreen(game,music,chosenMap));
                    return true;
                }
            });
            stage.addActor(Explore);
        }else{
            Texture upExplore = manager.get("Visual/Buttons/Active/ActBtnExplore.png", Texture.class);
            Texture downExplore = manager.get("Visual/Buttons/Active/ActBtnExplore.png", Texture.class);
            Explore = new Button(new TextureRegionDrawable(new TextureRegion(upExplore)), new TextureRegionDrawable(new TextureRegion(downExplore)));
            Explore.setSize(upExplore.getWidth(), upExplore.getHeight());
            Explore.setPosition(Math.round((Gdx.graphics.getWidth() / 100) * 65.3), Math.round((Gdx.graphics.getHeight() / 100) * 3.35));
            Explore.addListener(new InputListener() {
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                }

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                    return true;
                }
            });
            stage.addActor(Explore);


        }
    }
}
