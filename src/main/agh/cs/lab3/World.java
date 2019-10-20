package agh.cs.lab3;

import agh.cs.lab1.Direction;
import agh.cs.lab2.MoveDirection;

public class World {
    public static void main(String[] args) {
        Animal animal = new Animal();
        OptionsParser optionsParser = new OptionsParser();
//        System.out.println(animal);
//        animal.move(MoveDirection.RIGHT);
//        animal.move(MoveDirection.FORWARD);
//        animal.move(MoveDirection.FORWARD);
//        animal.move(MoveDirection.FORWARD);
//        System.out.println(animal);
        System.out.println(animal);
        MoveDirection[] commands = optionsParser.parse(args);
        for (MoveDirection cmd:commands) {
            animal.move(cmd);
        }
        System.out.println(animal);
    }
}
