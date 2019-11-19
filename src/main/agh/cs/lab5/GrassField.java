package agh.cs.lab5;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab6.FieldOccupiedException;

import java.util.*;

public class GrassField extends AbstractWorldMap implements IWorldMap {

    public GrassField(int n){
        if(n > 0) {
            List<Grass> grasses = new ArrayList<>();
            Random rnd = new Random();
            Vector2d position = new Vector2d(rnd.nextInt((int) Math.sqrt(10.0 * n)), rnd.nextInt((int) Math.sqrt(10.0 * n)));
            grasses.add(new Grass(position));
            for (int i = 1; i < n; i++) {
                while (isOccupied(position))
                    position = new Vector2d(rnd.nextInt((int) Math.sqrt(10.0 * n)), rnd.nextInt((int) Math.sqrt(10.0 * n)));
                grasses.add(new Grass(position));
            }

            for (Grass grass : grasses) {
                mapBoundary.addElement(grass);
            }
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position) || objectAt(position) instanceof Grass;
    }

}
