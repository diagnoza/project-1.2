package com.company;


public class Vector2D {
    private double x;
    private double y;
    private double z;
    private double magnitude;

    public Vector2D() {

    }

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D vector2D) {
        this.x = vector2D.getX();
        this.y = vector2D.getY();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    // Euclidean distance
    public double eqDistance(Vector2D v) {
        // Math.pow(x, 2) get replaced by x*x at compile time, I've heard.
        return Math.sqrt(Math.pow(v.getX() - this.x, 2) + Math.pow(v.getY() - this.y, 2));
    }

    public Vector2D scalarMult(double k) {
        this.x *= k;
        this.y *= k;

        return this;
    }

    public void addVector(Vector2D v) {
        this.x += v.getX();
        this.y += v.getY();
    }

    public double getMagnitude() {
        return Math.sqrt(x*x + y*y);
    }

    // hm....
    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    void computeMagnitude() {
        magnitude = Math.sqrt(x*x + y*y);
    }

    public String toString() {
        return "x: " + this.x + "  y: " + this.y;
    }

    public String toStringCVS() {
        return this.x + "," + this.y;
    }
}
