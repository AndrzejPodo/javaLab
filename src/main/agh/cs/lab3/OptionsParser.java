package agh.cs.lab3;

import agh.cs.lab2.MoveDirection;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OptionsParser {
    private Map<String, MoveDirection> dictionary = new HashMap<>();

    public OptionsParser(){
        dictionary.put("f", MoveDirection.FORWARD);
        dictionary.put("forward", MoveDirection.FORWARD);

        dictionary.put("b", MoveDirection.BACKWARD);
        dictionary.put("backward", MoveDirection.BACKWARD);

        dictionary.put("r", MoveDirection.RIGHT);
        dictionary.put("right", MoveDirection.RIGHT);

        dictionary.put("l", MoveDirection.LEFT);
        dictionary.put("left", MoveDirection.LEFT);
    }

    public MoveDirection[] parse(String[] commands){
        List<MoveDirection> directions = new LinkedList<>();
        for(String command : commands){
           directions.add(dictionary.get(command));
        }
        return (MoveDirection[]) directions.toArray();
    }
}
