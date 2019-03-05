package com.mynbri003.game.exploring;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.mynbri003.game.BizarreBazaar;
import com.mynbri003.game.Map;
import com.mynbri003.game.states.ExploreScreen;

public class Player extends Entity {



    public Player(final BizarreBazaar game, ExploreScreen explore, float x, float y, int width, int height, float speed, Texture texture, String name, Map map) {
        super(game,explore, x, y, width, height, speed, texture,name,map);
    }

    @Override
    public void update(float delta) {

        dx = 0;
        dy = 0;

        // move
        if(Gdx.input.isKeyJustPressed(Keys.UP)) {
            dy = speed;
        }
        if(Gdx.input.isKeyJustPressed(Keys.DOWN)) {
            dy = -speed;
        }
        if(Gdx.input.isKeyJustPressed(Keys.LEFT)) {
            dx = -speed;
        }
        if(Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
            dx = speed;
        }
    }


}
