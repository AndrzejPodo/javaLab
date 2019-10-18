package agh.cs.lab3;

import agh.cs.lab1.Direction;
import agh.cs.lab2.MoveDirection;

public class World {
    public static void main(String[] args) {
        Animal animal = new Animal();
        System.out.println(animal);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        System.out.println(animal);

        OptionsParser optionsParser = new OptionsParser();
    }
}
