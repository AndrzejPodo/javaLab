package agh.cs.lab2;

public class World {
    public static void main(String[] args) {
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));

        System.out.println(MapDirection.NORTH.toString());
        System.out.println(MapDirection.SOUTH.toString());
        System.out.println(MapDirection.WEST.toString());
        System.out.println(MapDirection.EAST.toString());

        System.out.println("SOUTH.next(): "+MapDirection.SOUTH.next());
        System.out.println("EAST.next(): "+MapDirection.EAST.next());
        System.out.println("WEST.next(): "+MapDirection.WEST.next());
        System.out.println("NORTH.next(): "+MapDirection.NORTH.next());

        System.out.println("SOUTH.previous(): "+MapDirection.SOUTH.previous());
        System.out.println("EAST.previous(): "+MapDirection.EAST.previous());
        System.out.println("WEST.previous(): "+MapDirection.WEST.previous());
        System.out.println("NORTH.previous(): "+MapDirection.NORTH.previous());

        System.out.println("Unit vector for SOUTH: "+ MapDirection.SOUTH.toUnitVector());
        System.out.println("Unit vector for NORTH: "+ MapDirection.NORTH.toUnitVector());
        System.out.println("Unit vector for EAST: "+ MapDirection.EAST.toUnitVector());
        System.out.println("Unit vector for WEST: "+ MapDirection.WEST.toUnitVector());
    }
}
