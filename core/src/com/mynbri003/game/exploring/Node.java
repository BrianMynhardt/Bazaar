package com.mynbri003.game.exploring;

import java.awt.*;

public class Node {
    //Node class for A*
    Node parent;
    Point position;

    int distance,heuristic,total;

    public Node(Node parent, Point position){
        this.parent = parent;
        this.position  = position;

        this.distance = 0;
        this.heuristic = 0;
        this.total = 0;
    }

    public boolean equals(Node other){
        return this.position.equals(other.position);
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal() {
        this.total = heuristic+distance;
    }

    public String toString(){
        return position.toString();
    }
}
