package agh.cs.lab3;

import agh.cs.lab2.MoveDirection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OptionsParserTest {
    OptionsParser optionsParser = new OptionsParser();


    @Test
    public void testParseNormal() {
        String[] commands = {"left","right","forward","backward","l","r","f","b"};
        MoveDirection[] expectedResult = {
                MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.BACKWARD,
                MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.BACKWARD
        };

        Assert.assertArrayEquals(expectedResult, optionsParser.parse(commands));
    }

    @Test
    public void testParseWithUnknownCommands() {
        String[] commands = {"lext","right","forward","backwar","l","x","f","back"};
        MoveDirection[] expectedResult = {
                MoveDirection.RIGHT, MoveDirection.FORWARD,
                MoveDirection.LEFT, MoveDirection.FORWARD
        };

        Assert.assertArrayEquals(expectedResult, optionsParser.parse(commands));
    }

    @Test
    public void testParseWithEmptyCommands() {
        String[] commands = {};
        MoveDirection[] expectedResult = {};

        Assert.assertArrayEquals(expectedResult, optionsParser.parse(commands));
    }
}