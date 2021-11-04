package agh.ics.oop;

import java.util.LinkedList;

public class RectangularMap implements IWorldMap {
    private final Vector2D lowerLeft;
    private final Vector2D upperRight;
    MapVisualizer mapVisualizer = new MapVisualizer(this);

    private final LinkedList<Animal> animals = new LinkedList<>();

    RectangularMap(int width, int height) {
        this.lowerLeft = new Vector2D(0, 0);
        this.upperRight = new Vector2D(width - 1, height - 1);
    }

    @Override
    public boolean canMoveTo(Vector2D position) {
        return (position.follows(lowerLeft) && position.precedes(upperRight) && !isOccupied(position));
    }

    @Override
    public boolean place(Animal animal) {
        if (isOccupied(animal.getPosition())) return false;
        animals.add(animal);
        return true;
    }

    @Override
    public boolean isOccupied(Vector2D position) {
        return objectAt(position) != null;
    }

    @Override
    public Object objectAt(Vector2D position) {
        for (Animal animal: animals) {
            if (animal.isAt(position)) return animal;
        }
        return null;
    }

    public String toString() {
        return mapVisualizer.draw(lowerLeft, upperRight);
    }
}
