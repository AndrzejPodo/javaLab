package agh.cs.lab2;

public enum MapDirection {
    NORTH("północ", new Vector2d(0,1)),
    SOUTH("południe", new Vector2d(0,-1)),
    WEST("zachód", new Vector2d(-1,0 )),
    EAST("wschód", new Vector2d(1, 0));

    private String translation;
    private Vector2d unitVector;

    MapDirection(String translation, Vector2d unitVector){
        this.translation = translation;
        this.unitVector = unitVector;
    }

    public MapDirection next(){
        if(this == NORTH) return EAST;
        else if(this == EAST) return SOUTH;
        else if(this == SOUTH) return WEST;
        else return NORTH;
    }

    public MapDirection previous(){
        if(this == NORTH) return WEST;
        else if(this == WEST) return SOUTH;
        else if(this == SOUTH) return EAST;
        else return NORTH;
    }

    public Vector2d toUnitVector(){
        return this.unitVector;
    }

    @Override
    public String toString() {
        return this.translation;
    }
}
