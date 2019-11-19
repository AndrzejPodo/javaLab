package agh.cs.lab4;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab5.AbstractWorldMap;
import agh.cs.lab5.IMapElement;
import agh.cs.lab6.FieldOccupiedException;
import agh.cs.lab7.MapBoundary;


public class RectangularMap extends AbstractWorldMap implements IWorldMap {

    public final int width;
    public final int height;

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        mapBoundary = new MapBoundary(new Vector2d(0,0), new Vector2d(width,height), false);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.precedes(new Vector2d(width, height)) && position.follows(new Vector2d(0, 0)) && !isOccupied(position);
    }
}
