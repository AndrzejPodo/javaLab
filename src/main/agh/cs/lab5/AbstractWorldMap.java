package agh.cs.lab5;

import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.MapVisualizer;
import agh.cs.lab7.IPositionChangeObserver;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

abstract public class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected MapVisualizer mapVisualizer = new MapVisualizer(this);
    protected Map<Vector2d, Animal> animalMap = new HashMap<>();
    protected List<Animal> animals = new LinkedList<>();

    public abstract Vector2d getUpperRight();
    public abstract Vector2d getLowerLeft();

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        animalMap.put(newPosition, animalMap.remove(oldPosition));
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return (objectAt(position) != null);
    }

    @Override
    public String toString() {
        return mapVisualizer.draw(getLowerLeft(), getUpperRight());
    }
}
