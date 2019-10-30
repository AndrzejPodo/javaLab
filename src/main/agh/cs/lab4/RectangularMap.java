package agh.cs.lab4;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap {

    public final int width;
    public final int height;
    private List<Animal> animals = new ArrayList<>();
    private MapVisualizer mapVisualizer = new MapVisualizer(this);

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if(position.x >=0 && position.x <= width && position.y >= 0 && position.y <= height && !isOccupied(position)) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPosition())){
            animals.add(animal);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void run(MoveDirection[] directions) {
        for(int i = 0; i < directions.length; i++){
            animals.get(i%animals.size()).move(directions[i]);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if(objectAt(position) != null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Object objectAt(Vector2d position) {
        return animals.stream().filter(animal -> position.equals(animal.getPosition())).findAny().orElse(null);
    }

    @Override
    public String toString() {
        return mapVisualizer.draw(new Vector2d(0,0), new Vector2d(width, height));
    }
}
