package com.company;


// In this context, a room is a collection of 2D surfaces that make up a 3D space.

// The height of a wall shall be defined as its width
// Walls will be enforced to have equal height

public class Room {

    private final Surface floor;
    private final Surface ceiling;
    private final Surface[] walls;
    private final double floorArea;
    private final double ceilingArea;
    private double wallsArea;
    private final double volume;
    private final double length;
    private final double width;
    private final double height;

    public Room(Surface floor, Surface[] walls)
    {
        this.floor = floor;
        // The ceiling and the floor will be the same, however, making a distinction makes the code easier to understand.
        this.ceiling = new Surface(floor.getLength(), floor.getWidth());
        // Making sure the Room object owns the array by creating a deep copy.
        this.walls = walls.clone();
        this.floorArea = floor.getArea();
        this.ceilingArea = ceiling.getArea();
        this.wallsArea = 0;
        for (Surface w : walls)
        {
            this.wallsArea += w.getArea();
        }
        this.length = floor.getLength();
        this.width = floor.getWidth();
        this.height = walls[0].getWidth();
        this.volume = floorArea * height;
    }

    public double getFloorArea() {
        return floorArea;
    }

    public double getCeilingArea()
    {
        return ceilingArea;
    }

    public double getWallsArea()
    {
        return wallsArea;
    }

    public double getLength()
    {
        return length;
    }

    public double getWidth()
    {
        return width;
    }

    public double getHeight() {
        return height;
    }

    // Shape of the floor is unknown due to the possibility of a composite shape, thus, is treated like a prism.
    public double getVolume()
    {
        return this.volume;
    }

}
