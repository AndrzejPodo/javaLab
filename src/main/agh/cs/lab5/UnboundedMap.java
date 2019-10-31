package agh.cs.lab5;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.MapVisualizer;

import java.util.*;

public class UnboundedMap implements IWorldMap {
    private List<Animal> animals = new LinkedList<>();
    private List<Rock> rocks;
    private Vector2d upperRight;
    private Vector2d lowerLeft;
    private MapVisualizer mapVisualizer = new MapVisualizer(this);

    private TreeMap<Vector2d, Placable> treeByX = new TreeMap<>((t1, t2) -> {
        if(t1.x > t2.x) return 1;
        if(t1.x < t2.x) return -1;
        return 0;
    });

    private TreeMap<Vector2d, Placable> treeByY = new TreeMap<>((t1, t2) -> {
        if(t1.y > t2.y) return 1;
        if(t1.y < t2.y) return -1;
        return 0;
    });

    public UnboundedMap(List<Rock> rocks){
        this.rocks = rocks;
        for(Rock rock: rocks){
            treeByX.put(rock.getPosition(), rock);
            treeByY.put(rock.getPosition(), rock);
        }
        resizeMap();
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPosition())){
            animals.add(animal);
            treeByX.put(animal.getPosition(), animal);
            treeByY.put(animal.getPosition(), animal);
            resizeMap();
            return true;
        }
        return false;
    }

    @Override
    public void run(MoveDirection[] directions) {
        Animal temp;
        for(int i = 0; i < directions.length; i++){
            temp = animals.get(i%animals.size());
            treeByX.remove(temp.getPosition());
            treeByY.remove(temp.getPosition());
            temp.move(directions[i]);
            treeByX.put(temp.getPosition(), temp);
            treeByY.put(temp.getPosition(), temp);
        }
        resizeMap();
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return (objectAt(position) != null);
    }

    @Override
    public Object objectAt(Vector2d position) {
        Animal animalAtPosition = animals.stream().filter(animal -> position.equals(animal.getPosition())).findAny().orElse(null);
        Rock rockAtPosition = rocks.stream().filter(rock -> position.equals(rock.getPosition())).findAny().orElse(null);

        return (animalAtPosition == null)?rockAtPosition:animalAtPosition;
    }

    @Override
    public String toString() {
        return mapVisualizer.draw(lowerLeft, upperRight);
    }

    private void resizeMap(){
        lowerLeft = new Vector2d(treeByX.firstKey().x, treeByY.firstKey().y);
        upperRight = new Vector2d(treeByX.lastKey().x, treeByY.lastKey().y);
    }
}
