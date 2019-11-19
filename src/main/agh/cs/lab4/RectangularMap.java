package agh.cs.lab4;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab5.AbstractWorldMap;
import agh.cs.lab6.FieldOccupiedException;


public class RectangularMap extends AbstractWorldMap implements IWorldMap {

    public final int width;
    public final int height;

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.precedes(new Vector2d(width, height)) && position.follows(new Vector2d(0, 0)) && !isOccupied(position);
    }

    @Override
    public void place(Animal animal) throws FieldOccupiedException {
        if(canMoveTo(animal.getPosition())){
            animals.add(animal);
            animalMap.put(animal.getPosition(), animal);
        }
        else throw new FieldOccupiedException(String.format("Field (%d,%d) is already occupied, or filed is out of map bounds.",animal.getPosition().x,animal.getPosition().y));
    }

    @Override
    public void run(MoveDirection[] directions) {
        Animal temp;
        for(int i = 0; i < directions.length; i++){
            temp = animals.get(i%animals.size());
            animalMap.remove(temp.getPosition(),temp);
            animals.get(i%animals.size()).move(directions[i]);
            animalMap.put(temp.getPosition(), temp);
        }
    }

    @Override
    public Object objectAt(Vector2d position) {
        return animalMap.get(position);
    }

    @Override
    public Vector2d getUpperRight() {
        return new Vector2d(width, height);
    }

    @Override
    public Vector2d getLowerLeft() {
        return new Vector2d(0,0);
    }

}
