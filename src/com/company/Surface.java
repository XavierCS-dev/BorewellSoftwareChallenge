package com.company;


// In this context, a surface can represent a floor, a wall, a ceiling and other such objects and is strictly 2D.

public class Surface {
    private final double length;
    private final double width;
    private final double area;

    // Sub-surface array, allows a surface to be divided into squares, to account for varying surface shapes.

    Surface[] subSurfaces = null;

    // A surface can be either a simple rectangle, or a collection of rectangles.

    public Surface(double length, double width)
    {
        this.length = length;
        this.width = width;
        this.area = length * width;
    }

    public Surface(Surface[] subSurfaces) throws NullPointerException
    {
        if (subSurfaces == null || subSurfaces.length < 1)
        {
            throw new NullPointerException("No sub surfaces provided!");
        }

        // Each surface will calculate the total area of all it's surfaces upon creation.
        // Surface properties are immutable, and thus these values only need to be calculated once.
        this.subSurfaces = subSurfaces.clone();
        double totalLength = 0;
        for (Surface s : subSurfaces)
            totalLength += s.getLength();
        this.length = totalLength;

        double totalWidth = 0;
        for (Surface s : subSurfaces)
            totalWidth += s.getWidth();
        this.width = totalWidth;

        this.area = length * width;

    }

    public double getLength() {
       return length;
    }

    public double getWidth() {
        return width;
    }

    public double getArea()
    {
        return area;
    }

}
