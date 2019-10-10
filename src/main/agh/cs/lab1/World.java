package agh.cs.lab1;

import java.util.Arrays;

import static java.lang.System.out;


public class World {

    private static void run(Direction[] commands){
        for (Direction command : commands) {
            try{
                switch (command) {
                    case FORWARD:
                        out.println("Moving forward!");
                        break;
                    case BACKWARD:
                        out.println("Moving backward!");
                        break;
                    case LEFT:
                        out.println("Moving left!");
                        break;
                    case RIGHT:
                        out.println("Moving right!");
                        break;
                }
            }catch (NullPointerException e){} //ignorujemy wyjatek
        }
    }

    public static void main(String[] args) {
        out.println("Start");

        Direction[] commands = Arrays.stream(args).map(arg -> Direction.fromString(arg)).toArray(Direction[]::new);


        run(commands);


        out.println("Finish");
    }
}
