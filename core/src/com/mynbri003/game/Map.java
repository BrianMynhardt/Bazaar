package com.mynbri003.game;

import com.badlogic.gdx.graphics.Texture;
import com.mynbri003.game.exploring.Entity;
import com.mynbri003.game.exploring.Player;
import com.mynbri003.game.states.ExploreScreen;

import java.util.ArrayList;

public class Map {
    final BizarreBazaar game;
    ExploreScreen screen;
    int level;
    int xSize;
    int ySize;
    int[][] map;
    ArrayList<Entity> entities = new ArrayList<Entity>();
    public Map(int level,final BizarreBazaar game,ExploreScreen screen) {
        this.screen = screen;
        this.game = game;
        this.level=level;
        setMap();
    }

    public void setMap(){
        switch(level){
            case 1:
                xSize = 15;
                ySize = 15;
                map = new int[][]{
                                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                                {1,0,0,0,0,1,1,1,1,0,0,0,0,0,1},
                                {1,0,0,0,0,0,0,0,1,0,0,0,0,0,1},
                                {1,0,0,0,0,0,0,0,1,0,0,0,0,0,1},
                                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                                {1,0,0,0,0,0,0,0,1,0,0,0,0,0,1},
                                {1,0,0,0,0,0,0,0,1,0,0,0,0,0,1},
                                {1,0,0,0,0,0,0,0,1,1,1,0,0,0,1},
                                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                        };
                entities.add(new Player(game,screen, 6*40, 3*40, 40, 40, 40.0f, new Texture("player.png"),"player",this));
                entities.add(new Entity(game,screen, 120, 320, 40, 40, 40.0f, new Texture("enemy.png"),"",this));
               // entities.add(new Entity(game,screen, 400, 400, 40, 40, 40.0f, new Texture("enemy.png"),""));
               // entities.add(new Entity(game,screen, 360, 120, 40, 40, 40.0f, new Texture("enemy.png"),""));
                break;
            case 2:
                xSize = 15;
                ySize = 15;
                map = new int[][]{
                        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                        {1,0,0,0,1,1,1,1,1,1,1,0,0,0,1},
                        {1,0,0,0,1,0,0,0,0,0,1,0,0,0,1},
                        {1,0,0,0,1,0,0,0,0,0,1,0,0,0,1},
                        {1,0,0,0,1,0,0,0,0,0,1,0,0,0,1},
                        {1,0,0,0,1,0,0,0,0,0,1,0,0,0,1},
                        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                        {1,0,0,0,0,0,0,0,1,1,1,1,1,0,1},
                        {1,0,1,1,1,1,1,0,0,0,0,0,0,0,1},
                        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                };
                entities.add(new Player(game,screen, 40, 40, 40, 40, 40.0f, new Texture("player.png"),"player",this));
                entities.add(new Entity(game,screen, 80, 320, 40, 40, 40.0f, new Texture("enemy.png"),"",this));
                //entities.add(new Entity(game,screen, 400, 400, 40, 40, 40.0f, new Texture("enemy.png"),""));
                //entities.add(new Entity(game,screen, 360, 120, 40, 40, 40.0f, new Texture("enemy.png"),""));
                break;
            case 3:
                xSize = 15;
                ySize = 15;
                map = new int[][]{
                        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                        {1,0,0,0,0,0,1,1,1,0,0,0,0,0,1},
                        {1,0,0,1,0,0,0,0,0,0,0,0,0,0,1},
                        {1,0,0,1,1,0,0,0,0,0,0,0,0,0,1},
                        {1,0,0,0,0,1,0,0,0,0,0,0,0,0,1},
                        {1,0,0,0,0,1,0,0,0,0,0,0,0,0,1},
                        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                        {1,0,0,0,0,1,1,1,1,0,0,0,0,0,1},
                        {1,0,0,0,0,0,0,0,1,0,0,0,0,0,1},
                        {1,0,0,0,0,0,0,0,1,0,0,0,0,0,1},
                        {1,0,0,0,0,0,0,0,1,0,0,0,0,0,1},
                        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                };
                entities.add(new Player(game,screen, 40, 40, 40, 40, 40.0f, new Texture("player.png"),"player",this));
                entities.add(new Entity(game,screen, 120, 320, 40, 40, 40.0f, new Texture("enemy.png"),"",this));
                //entities.add(new Entity(game,screen, 400, 400, 40, 40, 40.0f, new Texture("enemy.png"),""));
                //entities.add(new Entity(game,screen, 360, 120, 40, 40, 40.0f, new Texture("enemy.png"),""));
                break;
        }

    }
    public ArrayList<Entity> getEntities(){
        return new ArrayList<Entity>(entities);
    }

    public int[][] getMap(){
        return map;
    }

    public int getxSize() {
        return xSize*40;
    }

    public int getySize() {
        return ySize*40;
    }
}
