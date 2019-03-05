package com.mynbri003.game.exploring;

import com.badlogic.gdx.graphics.Texture;
import com.mynbri003.game.BizarreBazaar;
import com.mynbri003.game.Map;
import com.mynbri003.game.states.BattleScreen;
import com.mynbri003.game.states.ExploreScreen;
import com.mynbri003.game.states.ShopScreen;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static com.mynbri003.game.states.ExploreScreen.*;

public class Entity {
    public String name;
    final BizarreBazaar game;
    public ExploreScreen explore;
    private float x;
    private float y;
    public float dx;
    public float dy;
    public int width;
    public int height;
    private float moveTimer;
    private float timeThreshold = 0.7f;
    public float speed;
    public Texture texture;
    private Map map;
    int[][] mapping;
    Point[] pathB;
    ;

    public Entity(final BizarreBazaar game,ExploreScreen explore, float x, float y, int width, int height, float speed, Texture texture,String name, Map map) {
        this.game = game;
        this.explore = explore;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.texture = texture;
        this.name = name;
        this.moveTimer = 0;
        this.map = map;
        this.mapping = map.getMap();
    }

    public void chase(float delta,Entity player,Point point) {

        dx = 0;
        dy = 0;
        moveTimer += delta;


        Point go;
        if(moveTimer>timeThreshold) {

            go = point;
            if(go.getY()>this.y){
                System.out.println("up");
                dy = speed;
            }else
            if(go.getY()<this.y){
                System.out.println("down");
                dy = -speed;
            }else

            if(go.getX()>this.x){
                System.out.println("right");
                dx = speed;
            }else
            if(go.getX()<this.x){
                System.out.println("left");
                dx = -speed;
            }


            moveTimer = 0;


        }else{
//            pathB = aStar(map,new Point((int)player.x,(int)player.y),new Point((int)this.x,(int)this.y));
            //return Arrays.copyOf(pathB,pathB.length);
        }

    }
    public void update(float delta){

    }

    public void move(float newX, float newY) {
        x = newX;
        y = newY;
    }

    public void render() {

    }

    public void tileCollision(int tile, int tileX, int tileY, float newX, float newY, Direction direction) {
        //System.out.println("tile collision at: " + tileX + " " + tileY);

        if(direction == Direction.U) {
            y = tileY * explore.tileSize + explore.tileSize;
        }
        else if(direction == Direction.D) {
            y = tileY * explore.tileSize - height;
        }
        else if(direction == Direction.L) {
            x = tileX * explore.tileSize + explore.tileSize;
        }
        else if(direction == Direction.R) {
            x = tileX * explore.tileSize - width;
        }
    }

    public void entityCollision(Entity e2, float newX, float newY, Direction direction) {
        if (e2.name=="player"){
            game.fight(e2.toString());
        }

        move(newX, newY);
        // could also resolve entity collisions in the same we do tile collision resolution
        // as shown in class
    }



    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Point getPosition(){
        Point tmp = new Point((int)this.x,(int)this.y);
        return tmp;
    }
}

