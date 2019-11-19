package agh.cs.lab3;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab5.IMapElement;
import agh.cs.lab7.IPositionChangeObserver;

import java.util.LinkedList;
import java.util.List;

public class Animal implements IMapElement {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);
    private IWorldMap map;
    private List<IPositionChangeObserver> observers = new LinkedList<>();

    public Animal(){
        super();
    }

    public Animal(IWorldMap map){
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this(map);
        this.position = initialPosition;
    }


    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for(IPositionChangeObserver observer : observers){
            observer.positionChanged(oldPosition, newPosition);
        }
    }

    public void move(MoveDirection direction){
        Vector2d temporaryPosition = this.position;

        switch (direction){
            case FORWARD:
                temporaryPosition = temporaryPosition.add(this.orientation.toUnitVector());
                break;
            case BACKWARD:
                temporaryPosition = temporaryPosition.add(this.orientation.toUnitVector().opposite());
                break;
            case LEFT:
                orientation = this.orientation.previous();
                break;
            case RIGHT:
                orientation = this.orientation.next();
                break;
        }

        if((map != null && map.canMoveTo(temporaryPosition) && !this.position.equals(temporaryPosition))) {
            positionChanged(this.position, temporaryPosition);
            this.position = temporaryPosition;
        }
    }

    public MapDirection getOrientation() {
        return this.orientation;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    @Override
    public String toString() {
        String s = this.orientation.name();
        return s.charAt(0)+"";
    }
}
