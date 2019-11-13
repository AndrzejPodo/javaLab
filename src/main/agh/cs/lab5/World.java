package agh.cs.lab5;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab3.OptionsParser;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab6.FieldOccupiedException;

public class World {
    public static void main(String[] args) {
        try {
            MoveDirection[] directions = new OptionsParser().parse(args);
            IWorldMap map = new GrassField(10);
            System.out.println(map);
            map.place(new Animal(map));
            map.place(new Animal(map, new Vector2d(3, 4)));
            System.out.println(map);
            map.run(directions);
            System.out.println(map);
        }catch(IllegalArgumentException | FieldOccupiedException e){
            System.err.println(e);
        }
    }
}
