package com.mynbri003.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicPlayer {
    final BizarreBazaar game;
    Music music;
    float position;

    public MusicPlayer(final BizarreBazaar game) {
        this.game = game;
        this.position = position;

    }
    public void setAdventure(float position){
        music = Gdx.audio.newMusic(Gdx.files.internal("Audio/AdventureNo.wav"));
        this.position = position;
    }
    public void setMain(float position){
        music = Gdx.audio.newMusic(Gdx.files.internal("Audio/MarketStart.wav"));
        this.position = position;

    }
    public void play(){
        if(game.prefs.getBoolean("music")) {
            music.setPosition(position);
            music.play();
            music.setLooping(true);
        }else{
            music.stop();
        }


    }
    public void mute(){
        music.setVolume(0);


    }
    public void unMute(){
        music.setVolume(0.8f);


    }
    public void dispose(){
        music.dispose();
    }
}
