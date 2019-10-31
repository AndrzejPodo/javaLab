package agh.cs.lab5;

import agh.cs.lab2.Vector2d;

public class Rock implements Placable {
    private Vector2d position;

    public Rock(Vector2d  position){
        this.position = position;
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "s";
    }
}
