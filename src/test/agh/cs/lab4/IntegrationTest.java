package agh.cs.lab4;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab3.OptionsParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class IntegrationTest {
    IWorldMap map;
    Animal animal1;
    Animal animal2;
    OptionsParser optionsParser = new OptionsParser();

    @Before
    public void setUp(){
        map = new RectangularMap(10, 5);
        animal1 = new Animal(map);
        animal2 = new Animal(map, new Vector2d(3,4));
        map.place(animal1);
        map.place(animal2);
    }

    @Test
    public void testObjectAt(){
        Assert.assertNull(map.objectAt(new Vector2d(1,1)));
        Assert.assertNull(map.objectAt(new Vector2d(2,3)));
        Assert.assertEquals(map.objectAt(new Vector2d(2,2)), animal1);
        Assert.assertEquals(map.objectAt(new Vector2d(3,4)), animal2);
    }

    @Test
    public void testIsOccupied(){
        Assert.assertTrue(map.isOccupied(new Vector2d(3,4)));
        Assert.assertTrue(map.isOccupied(new Vector2d(2,2 )));
        Assert.assertFalse(map.isOccupied(new Vector2d(10,5)));
        Assert.assertFalse(map.isOccupied(new Vector2d(4, 4)));
    }

    @Test
    public void testCanMoveTo(){
        Assert.assertTrue(map.canMoveTo(new Vector2d(0,0)));
        Assert.assertTrue(map.canMoveTo(new Vector2d(4,4)));
        Assert.assertFalse(map.canMoveTo(new Vector2d(2,2)));
        Assert.assertFalse(map.canMoveTo(new Vector2d(3,4)));
        Assert.assertFalse(map.canMoveTo(new Vector2d(11,4)));
        Assert.assertFalse(map.canMoveTo(new Vector2d(10,-1)));
        Assert.assertFalse(map.canMoveTo(new Vector2d(10,6)));
    }

    @Test
    public void testPlace(){
        Assert.assertTrue(map.place(new Animal(map, new Vector2d(0,0))));
        Assert.assertTrue(map.place(new Animal(map, new Vector2d(4,4))));
        Assert.assertFalse(map.place(new Animal(map, new Vector2d(0,-1))));
        Assert.assertFalse(map.place(new Animal(map, new Vector2d(3,4))));
    }

    @Test
    public void testIntegration(){
        String[] args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] commands = optionsParser.parse(args);
        map.run(commands);

        Assert.assertEquals(animal1.getPosition(), new Vector2d(2,0));
        Assert.assertEquals(animal2.getPosition(), new Vector2d(3,5));
        Assert.assertEquals(animal1.getOrientation(), MapDirection.SOUTH);
        Assert.assertEquals(animal2.getOrientation(), MapDirection.NORTH);
    }
    @Test
    public void testIntegration2(){
        String[] args = {"f", "l", "f", "l", "r", "f", "f", "f", "r", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] commands = optionsParser.parse(args);
        map.run(commands);

        Assert.assertEquals(animal1.getPosition(), new Vector2d(3,1));
        Assert.assertEquals(animal2.getPosition(), new Vector2d(3,0));
        Assert.assertEquals(animal1.getOrientation(), MapDirection.SOUTH);
        Assert.assertEquals(animal2.getOrientation(), MapDirection.SOUTH);
    }
}
