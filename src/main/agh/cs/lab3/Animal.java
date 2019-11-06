package agh.cs.lab3;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab5.IMapElement;

public class Animal implements IMapElement {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);
    private IWorldMap map;

    public Animal(){
        super();
    }

    public Animal(IWorldMap map){
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.position = initialPosition;
        this.map = map;
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

        if((map != null && map.canMoveTo(temporaryPosition))) {
            this.position = temporaryPosition;
        }
//        }else if(map == null &&  (temporaryPosition.x >=0 && temporaryPosition.x <= 4 && temporaryPosition.y >= 0 && temporaryPosition.y <= 4)){
//            this.position = temporaryPosition;
//        }
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
