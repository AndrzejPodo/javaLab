package agh.cs.lab3;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AnimalTest {
    Animal animal;

    @Before
    public void setUp() throws Exception {
        animal = new Animal();

        Assert.assertEquals(animal.getPosition(), new Vector2d(2,2));
        Assert.assertEquals(animal.getOrientation(), MapDirection.NORTH);
    }

    @Test
    public void testTurning(){
        animal.move(MoveDirection.RIGHT);
        Assert.assertEquals(animal.getOrientation(), MapDirection.EAST);
        Assert.assertEquals(animal.getPosition(), new Vector2d(2,2));
        animal.move(MoveDirection.RIGHT);
        Assert.assertEquals(animal.getOrientation(), MapDirection.SOUTH);
        Assert.assertEquals(animal.getPosition(), new Vector2d(2,2));
        animal.move(MoveDirection.RIGHT);
        Assert.assertEquals(animal.getOrientation(), MapDirection.WEST);
        Assert.assertEquals(animal.getPosition(), new Vector2d(2,2));
        animal.move(MoveDirection.RIGHT);
        Assert.assertEquals(animal.getOrientation(), MapDirection.NORTH);
        Assert.assertEquals(animal.getPosition(), new Vector2d(2,2));

        animal.move(MoveDirection.LEFT);
        Assert.assertEquals(animal.getOrientation(), MapDirection.WEST);
        Assert.assertEquals(animal.getPosition(), new Vector2d(2,2));
        animal.move(MoveDirection.LEFT);
        Assert.assertEquals(animal.getOrientation(), MapDirection.SOUTH);
        Assert.assertEquals(animal.getPosition(), new Vector2d(2,2));
        animal.move(MoveDirection.LEFT);
        Assert.assertEquals(animal.getOrientation(), MapDirection.EAST);
        Assert.assertEquals(animal.getPosition(), new Vector2d(2,2));
        animal.move(MoveDirection.LEFT);
        Assert.assertEquals(animal.getOrientation(), MapDirection.NORTH);
        Assert.assertEquals(animal.getPosition(), new Vector2d(2,2));
    }

    @Test
    public void testMovingWithNorthOrientation(){
        animal.move(MoveDirection.FORWARD);
        Assert.assertEquals(animal.getPosition(), new Vector2d(2,3));
        Assert.assertEquals(animal.getOrientation(), MapDirection.NORTH);

        animal.move(MoveDirection.BACKWARD);
        Assert.assertEquals(animal.getPosition(), new Vector2d(2,2));
        Assert.assertEquals(animal.getOrientation(), MapDirection.NORTH);
    }

    @Test
    public void testMovingWithEastOrientation(){
        animal.move(MoveDirection.RIGHT);

        animal.move(MoveDirection.FORWARD);
        Assert.assertEquals(animal.getPosition(), new Vector2d(3,2));
        Assert.assertEquals(animal.getOrientation(), MapDirection.EAST);

        animal.move(MoveDirection.BACKWARD);
        Assert.assertEquals(animal.getPosition(), new Vector2d(2,2));
        Assert.assertEquals(animal.getOrientation(), MapDirection.EAST);


    }

    @Test
    public void testMovingWithSouthOrientation(){
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);

        animal.move(MoveDirection.FORWARD);
        Assert.assertEquals(animal.getPosition(), new Vector2d(2,1));
        Assert.assertEquals(animal.getOrientation(), MapDirection.SOUTH);

        animal.move(MoveDirection.BACKWARD);
        Assert.assertEquals(animal.getPosition(), new Vector2d(2,2));
        Assert.assertEquals(animal.getOrientation(), MapDirection.SOUTH);
    }

    @Test
    public void testMovingWithWestOrientation() {
        animal.move(MoveDirection.LEFT);

        animal.move(MoveDirection.FORWARD);
        Assert.assertEquals(animal.getPosition(), new Vector2d(1,2));
        Assert.assertEquals(animal.getOrientation(), MapDirection.WEST);

        animal.move(MoveDirection.BACKWARD);
        Assert.assertEquals(animal.getPosition(), new Vector2d(2,2));
        Assert.assertEquals(animal.getOrientation(), MapDirection.WEST);
    }

    @Test
    public void testOutOfAreaNorth(){
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);

        Assert.assertEquals(animal.getPosition(), new Vector2d(2,4));

        animal.move(MoveDirection.FORWARD);

        Assert.assertEquals(animal.getPosition(), new Vector2d(2,4));
    }

    @Test
    public void testOutOfAreaEast(){
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);

        Assert.assertEquals(animal.getPosition(), new Vector2d(4,2));

        animal.move(MoveDirection.FORWARD);

        Assert.assertEquals(animal.getPosition(), new Vector2d(4,2));
    }

    @Test
    public void testOutOfAreaSouth(){
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);

        Assert.assertEquals(animal.getPosition(), new Vector2d(2,0));

        animal.move(MoveDirection.FORWARD);

        Assert.assertEquals(animal.getPosition(), new Vector2d(2,0));
    }

    @Test
    public void testOutOfAreaWest(){
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);

        Assert.assertEquals(animal.getPosition(), new Vector2d(0,2));

        animal.move(MoveDirection.FORWARD);

        Assert.assertEquals(animal.getPosition(), new Vector2d(0,2));
    }
}