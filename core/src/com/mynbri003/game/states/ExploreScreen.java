package com.mynbri003.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mynbri003.game.BizarreBazaar;
import com.mynbri003.game.Map;
import com.mynbri003.game.MusicPlayer;
import com.mynbri003.game.exploring.Entity;
import com.mynbri003.game.exploring.Node;
import com.mynbri003.game.exploring.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ExploreScreen implements Screen {
    final BizarreBazaar game;

    MusicPlayer music;

    SpriteBatch batch;
    int screenWidth;
    int screenHeight;
    Map map;
    int[][] mapping;

    // 1 = block
    // 0 = empty
    // the x and y coordinate system is not what it seems
    // visually x goes down and y across
    // this will make more sense when you compare it to what is drawn
    public int mapWidth = 15;
    public int mapHeight = 15;
    public int tileSize = 40;
    Texture tileTexture,blankTexture,pathTexture;

    ArrayList<Entity> entities;
    Point[] path;

    public enum Axis { X, Y };
    public enum Direction { U, D, L, R };

    Entity player;

    public ExploreScreen(final BizarreBazaar game,MusicPlayer music,int map) {
        this.game = game;
        this.music = music;
        this.map = new Map(map,game,this);


    }
    public void loadAssets(){

    }

    @Override
    public void show() {
        entities = map.getEntities();
        mapping=map.getMap();
        music.dispose();
        music.setAdventure(0);
        music.play();

        batch = new SpriteBatch();
        tileTexture = new Texture("block.png");
        blankTexture = new Texture("blank.png");
        pathTexture = new Texture("path.png");
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        // add some entities including a player
        player = entities.get(0);


    }

    @Override
    public void render(float delta) {
        // update
        // ---
        //path = aStar(map,new Point((int)player.x,(int)player.y),new Point((int)entities.get(1).x,(int)entities.get(1).y));

        delta = Gdx.graphics.getDeltaTime();

        player.update(delta);
        moveEntity(player,player.getX()+player.dx,player.getY()+player.dy);
        path = aStar(map,player.getPosition(),entities.get(1).getPosition());
        for(Point pnt:path){
            System.out.println(pnt);
        }
        // update all entities
        int counter = path.length-1;
        for(int i = entities.size() - 1; i > 0; i--) {
            Entity e = entities.get(i);


            if(path!=null) {

                    e.chase(delta, entities.get(0), path[counter]);
                    counter --;


            }
            // now we try move the entity on the map and check for collisions
            moveEntity(e, e.getX() + e.dx, e.getY() + e.dy);
        }


        // draw
        // ---


        // to offset where your map and entities are drawn change the viewport
        // see libgdx documentation

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        // draw tile map
        // go over each row bottom to top
        for(int y = 0; y < mapHeight; y++) {
            // go over each column left to right
            for(int x = 0; x < mapWidth; x++) {
                // tile
                if(mapping[x][y] == 1) {
                    batch.draw(tileTexture, x * tileSize, y * tileSize,tileSize,tileSize);
                }else if(mapping[x][y] == 0){
                    batch.draw(blankTexture,x*tileSize,y*tileSize,tileSize,tileSize);
                }

                // draw other types here...
            }
        }
        //Draw Path
        if(path!= null) {
            for (Point pnt : path) {
                //System.out.println(pnt);
                batch.draw(pathTexture, (int) pnt.getX(), (int) pnt.getY(), tileSize, tileSize);
            }
        }

        // draw all entities
        for(int i = entities.size() - 1; i >= 0; i--) {
            Entity e = entities.get(i);
            batch.draw(e.texture, e.getX(), e.getY(),tileSize,tileSize);
        }

        batch.end();


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
    public void moveEntity(Entity e, float newX, float newY) {
        // just check x collisions keep y the same
        moveEntityInAxis(e, Axis.X, newX, e.getY());
        // just check y collisions keep x the same
        moveEntityInAxis(e, Axis.Y, e.getX(), newY);
    }

    public void moveEntityInAxis(Entity e, Axis axis, float newX, float newY) {
        Direction direction;

        // determine axis direction
        if(axis == Axis.Y) {
            if(newY - e.getY() < 0) direction = Direction.U;
            else direction = Direction.D;
        }
        else {
            if(newX - e.getX() < 0) direction = Direction.L;
            else direction = Direction.R;
        }

        if(!tileCollision(e, direction, newX, newY) && !entityCollision(e, direction, newX, newY)) {
            // full move with no collision
            e.move(newX, newY);
        }
        // else collision with wither tile or entity occurred
    }

    public boolean tileCollision(Entity e, Direction direction, float newX, float newY) {
        boolean collision = false;

        // determine affected tiles
        int x1 = (int) Math.floor(Math.min(e.getX(), newX) / tileSize);
        int y1 = (int) Math.floor(Math.min(e.getY(), newY) / tileSize);
        int x2 = (int) Math.floor((Math.max(e.getX(), newX) + e.width - 0.1f) / tileSize);
        int y2 = (int) Math.floor((Math.max(e.getY(), newY) + e.height - 0.1f) / tileSize);

        // todo: add boundary checks...

        // tile checks
        for(int x = x1; x <= x2; x++) {
            for(int y = y1; y <= y2; y++) {
                if(mapping[x][y] == 1) {
                    collision = true;
                    e.tileCollision(mapping[x][y], x, y, newX, newY, direction);
                }
            }
        }

        return collision;
    }

    public boolean entityCollision(Entity e1, Direction direction, float newX, float newY) {
        boolean collision = false;

        for(int i = 0; i < entities.size(); i++) {
            Entity e2 = entities.get(i);

            // we don't want to check for collisions between the same entity
            if(e1 != e2) {
                // axis aligned rectangle rectangle collision detection
                if(newX < e2.getX() + e2.width && e2.getX() < newX + e1.width &&
                        newY < e2.getY() + e2.height && e2.getY() < newY + e1.height) {
                    collision = true;

                    e1.entityCollision(e2, newX, newY, direction);
                }
            }
        }

        return collision;
    }
    public Point[] aStar(Map map, Point start, Point end){
        //Returns a list of positions from point start to end.

        //Get map array
        int[][] Mapp = map.getMap();

        //Create astart and end nodes
        Node startNode = new Node(null,start);
        Node endNode = new Node(null,end);


        //Initialize open and closed lists and path list
        ArrayList<Node> open_list = new ArrayList<Node>();
        ArrayList<Node> closed_list = new ArrayList<Node>();
        ArrayList<Node> children = new ArrayList<Node>();;
        ArrayList<Point> path = new ArrayList<Point>();

        //Add start node
        open_list.add(startNode);

        //Loop until you find the end
        while(!open_list.isEmpty()){
            //Get this current node
            Node currentNode = open_list.get(0);
            int current_index = 0;
            int counter = 0;

            for(Node node: open_list){

                if(node.getTotal()<currentNode.getTotal()){
                    currentNode = node;
                    current_index = counter;
                    counter++;
                }
            }

            //Pop current node off open list and add to the closed list
            open_list.remove(current_index);
            closed_list.add(currentNode);
//            System.out.println();
//            for (int i = 0; i <closed_list.size() ; i++) {
//                System.out.println(closed_list.get(i).getPosition());
//
//            }

            //Goal
//            System.out.println(currentNode.equals(endNode));
            if(currentNode.equals(endNode)){
                Node current = currentNode;
                while(current.getParent() != null){
                    path.add(current.getPosition());
                    current = current.getParent();
                }
                //Reverse path
                Point[] pat = path.toArray(new Point[path.size()]);

                return Arrays.copyOf(pat,pat.length);
            }
            //Array of new positions
            Point[] positions = {new Point(0,-40), new Point(0,40),new Point(-40,0),new Point(40,0)/*,new Point(-40,-40), new Point(-40,40),new Point(40,-40), new Point(40,40)*/};


            children = new ArrayList<Node>();
            //Generate Children
            childrenloop:
            for(Point newPosition:positions){
                //Get node position
                Point node_position = new Point((int)(currentNode.getPosition().getX()+ newPosition.getX()),(int)(currentNode.getPosition().getY()+ newPosition.getY()));
                //System.out.println(node_position);
                //System.out.println(node_position);
                //Contain in Map
                if(node_position.getX()>(map.getxSize()-40)|| node_position.getX()<0 || node_position.getY()>(map.getySize()-40)|| node_position.getY() <0 ){
                    System.out.println("Out of Bounds");
                    continue;
                }else

                    //Check Walkable Terrain
                    if(Mapp[(int)node_position.getX()/40][(int)node_position.getY()/40] != 0){
                        //System.out.println("Not walkable");
                        continue;
                    }else {

                        //Create New Node
                        Node new_Node = new Node(currentNode, node_position);

                        //Add to children
                        children.add(new_Node);
                    }

            }

            //Loop through children
            outerloop:
            for(Node child : children){
                //System.out.println(child.getPosition());
                //Check if child is on closed list
                for(Node closed_child : closed_list){
                    if (child.equals(closed_child)){
                        continue outerloop;
                    }
                }

                // Generate the  Total, Distance and Heuristic values
                child.setDistance(currentNode.getDistance()+1);
                int heuristic =(int)(Math.round (((Math.abs((child.getPosition().getX()-endNode.getPosition().getX()))/40))+(Math.abs((child.getPosition().getY()-endNode.getPosition().getY())/40))));
                child.setHeuristic(heuristic);
                child.setTotal();

                //Child already in open list
                for(Node open_node:open_list){
                    if(child.equals(open_node)&& child.getDistance()>open_node.getDistance()){
                        continue outerloop;
                    }
                }

                //Add child to open list
                open_list.add(child);
            }



        }

        return null;

    }




}
