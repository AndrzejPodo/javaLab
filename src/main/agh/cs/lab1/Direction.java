package agh.cs.lab1;

public enum Direction {
    FORWARD, BACKWARD, LEFT, RIGHT;
    public static Direction fromString(String s){
        switch(s) {
            case "f":
                return FORWARD;
            case "b":
                return BACKWARD;
            case "l":
                return LEFT;
            case "r":
                return RIGHT;
        }
        return null;
    }
}
