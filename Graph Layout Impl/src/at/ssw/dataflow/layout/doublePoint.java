package at.ssw.dataflow.layout;

import java.awt.geom.Point2D;

/**
 * Simple helper class for the force-directed layouts.
 *
 * @author Stefan Loidl
 */

class doublePoint extends Point2D{
    private double x,y;

    public doublePoint(double x, double y){this.x=x; this.y=y;}

    public double getX() {return x;}

    public double getY() {return y;}

    public void setLocation(double x, double y) {this.x=x; this.y=y;}
}
