package agh.cs.lab5;

import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.MapVisualizer;

import java.util.LinkedList;
import java.util.List;

abstract public class AbstractWorldMap implements IWorldMap {
    protected MapVisualizer mapVisualizer = new MapVisualizer(this);

    public abstract Vector2d getUpperRight();
    public abstract Vector2d getLowerLeft();

    @Override
    public boolean isOccupied(Vector2d position) {
        return (objectAt(position) != null);
    }

    @Override
    public String toString() {
        return mapVisualizer.draw(getLowerLeft(), getUpperRight());
    }
}
