package agh.cs.lab7;

import agh.cs.lab2.Vector2d;
import agh.cs.lab5.IMapElement;

import java.util.List;
import java.util.TreeMap;

public class MapBoundary implements IPositionChangeObserver {

    private boolean resize;
    private Vector2d upperRight = new Vector2d(0,0);
    private Vector2d lowerLeft = new Vector2d(0,0);

    public MapBoundary(List<IMapElement> elements){
        this.resize = true;
        for (IMapElement element: elements) {
            addElement(element);
        }
    }

    public MapBoundary(Vector2d upperRight, Vector2d lowerLeft, boolean resize){
        this.resize = resize;
        this.upperRight = upperRight;
        this.lowerLeft = lowerLeft;
    }

    private TreeMap<Vector2d, IMapElement> treeByX = new TreeMap<>((t1, t2) -> {
        if(t1.x > t2.x) return 1;
        else if(t1.x < t2.x) return -1;
        else if(t1.y > t2.y) return 1;
        else if(t1.y < t2.y) return -1;
        else return 0;
    });

    private TreeMap<Vector2d, IMapElement> treeByY = new TreeMap<>((t1, t2) -> {
        if(t1.y > t2.y) return 1;
        else if(t1.y < t2.y) return -1;
        else if(t1.x > t2.x) return 1;
        else if(t1.x < t2.x) return -1;
        else return 0;
    });

    public Vector2d getUpperRight(){
        return upperRight;
    }
    public Vector2d getLowerLeft(){
        return lowerLeft;
    }

    public void addElement(IMapElement element){
        if(this.resize) {
            treeByX.put(element.getPosition(), element);
            treeByY.put(element.getPosition(), element);
            resizeMap();
        }
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        if(this.resize) {
            treeByX.put(newPosition, treeByX.remove(oldPosition));
            treeByY.put(newPosition, treeByY.remove(oldPosition));
            resizeMap();
        }
    }

    private void resizeMap(){
        lowerLeft = new Vector2d(treeByX.firstKey().x, treeByY.firstKey().y);
        upperRight = new Vector2d(treeByX.lastKey().x, treeByY.lastKey().y);
    }

}
