package com.company;


// In this context, a surface can represent a floor, a wall, a ceiling and other such objects and is strictly 2D.

public class Surface {
    private double length;
    private double width;

    // Sub-surface array, allows a surface to be divided into squares, to account for varying surface shapes.

    Surface[] subSurfaces = null;

    // A surface can be either a simple square, or a collection of squares.

    public Surface(double length, double width)
    {
        this.length = length;
        this.width = width;
    }

    public Surface(Surface[] subSurfaces)
    {
        this.subSurfaces = subSurfaces.clone();
    }

    public double getLength() {
        if (this.isUniform())
            return length;
        double totalLength = 0;
        for (Surface s : subSurfaces)
            totalLength += s.getLength();
        return totalLength;
    }

    public double getWidth() {
        if (this.isUniform())
            return width;
        double totalWidth = 0;
        for (Surface s : subSurfaces)
            totalWidth += s.getWidth();
        return totalWidth;
    }

    public double getArea()
    {
        if (this.isUniform())
        {
            return length * width;
        }

        // Recursively call getArea to all sub-surfaces, not the fastest, but clean and simple.
        double totalArea = 0;
        for (Surface s: subSurfaces)
        {
            totalArea += s.getArea();
        }

        return totalArea;
    }

    // Used to check whether a surface is made up of sub-surfaces
    public boolean isUniform()
    {
        return subSurfaces == null;
    }
}
