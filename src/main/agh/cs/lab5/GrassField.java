package agh.cs.lab5;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.MapVisualizer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GrassField extends AbstractWorldMap implements IWorldMap {
    private List<Animal> animals = new LinkedList<>();
    private List<Grass> grasses = new ArrayList<>();;
    private Vector2d upperRight;
    private Vector2d lowerLeft;

    private TreeMap<Vector2d, IMapElement> treeByX = new TreeMap<>((t1, t2) -> {
        if(t1.x > t2.x) return 1;
        else if(t1.x < t2.x) return -1;
        else if(t1.y > t2.y) return 1;
        else if(t1.y < t2.y) return -1;
        else return 0;
    });

    private TreeMap<Vector2d, IMapElement> treeByY = new TreeMap<>((t1, t2) -> {
        if(t1.y > t2.y) return 1;
        else if(t1.y < t2.y) return -1;
        else if(t1.x > t2.x) return 1;
        else if(t1.x < t2.x) return -1;
        else return 0;
    });

    public GrassField(int n){
        if(n > 0) {
            Random rnd = new Random();

            Vector2d position = new Vector2d(rnd.nextInt((int) Math.sqrt(10.0 * n)), rnd.nextInt((int) Math.sqrt(10.0 * n)));
            grasses.add(new Grass(position));
            for (int i = 1; i < n; i++) {
                while (isOccupied(position))
                    position = new Vector2d(rnd.nextInt((int) Math.sqrt(10.0 * n)), rnd.nextInt((int) Math.sqrt(10.0 * n)));
                grasses.add(new Grass(position));
            }

            for (Grass grass : grasses) {
                treeByX.put(grass.getPosition(), grass);
                treeByY.put(grass.getPosition(), grass);
            }
            resizeMap();
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position) || objectAt(position) instanceof Grass;
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
    public Object objectAt(Vector2d position) {
        Animal animalAtPosition = animals.stream().filter(animal -> position.equals(animal.getPosition())).findAny().orElse(null);
        Grass grassAtPosition = grasses.stream().filter(grass -> position.equals(grass.getPosition())).findAny().orElse(null);

        return (animalAtPosition == null)? grassAtPosition :animalAtPosition;
    }

    @Override
    public Vector2d getUpperRight() {
        return upperRight;
    }

    @Override
    public Vector2d getLowerLeft() {
        return lowerLeft;
    }

    private void resizeMap(){
        lowerLeft = new Vector2d(treeByX.firstKey().x, treeByY.firstKey().y);
        upperRight = new Vector2d(treeByX.lastKey().x, treeByY.lastKey().y);
    }
}
