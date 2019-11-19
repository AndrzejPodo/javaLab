package agh.cs.lab5;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.MapVisualizer;
import agh.cs.lab6.FieldOccupiedException;
import agh.cs.lab7.IPositionChangeObserver;
import agh.cs.lab7.MapBoundary;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

abstract public class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected MapVisualizer mapVisualizer = new MapVisualizer(this);
    protected Map<Vector2d, IMapElement> elements = new HashMap<>();
    protected List<Animal> animals = new LinkedList<>();
    protected MapBoundary mapBoundary = new MapBoundary(new LinkedList<>(elements.values()));

    public Vector2d getUpperRight(){
        return mapBoundary.getUpperRight();
    }
    public Vector2d getLowerLeft(){
        return mapBoundary.getLowerLeft();
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        elements.put(newPosition, elements.remove(oldPosition));
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return (objectAt(position) != null);
    }

    @Override
    public IMapElement objectAt(Vector2d position) {
        return elements.get(position);
    }

    @Override
    public void place(Animal animal) throws FieldOccupiedException {
        if(canMoveTo(animal.getPosition())){
            animals.add(animal);
            elements.put(animal.getPosition(), animal);
            mapBoundary.addElement(animal);
            animal.addObserver(this);
            animal.addObserver(mapBoundary);
        }
        else throw new FieldOccupiedException(String.format("Field (%d,%d) is already occupied!",animal.getPosition().x,animal.getPosition().y));
    }

    @Override
    public void run(MoveDirection[] directions) {
        Animal temp;
        for(int i = 0; i < directions.length; i++){
            temp = animals.get(i%animals.size());
            if(directions[i] == MoveDirection.FORWARD || directions[i] == MoveDirection.BACKWARD) {
                elements.remove(temp.getPosition());
                temp.move(directions[i]);
                elements.put(temp.getPosition(), temp);
            }else {
                temp.move(directions[i]);
            }
        }
    }
    @Override
    public String toString() {
        return mapVisualizer.draw(getLowerLeft(), getUpperRight());
    }
}
