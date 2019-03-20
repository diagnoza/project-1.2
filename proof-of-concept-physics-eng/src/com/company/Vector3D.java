package com.company;

import java.lang.Math;
import java.util.Vector;


// It would be nice to use generics here -- but it would also be nice if java allowed overloading operators.
public class Vector3D {
    private double x;
    private double y;
    private double z;
    private double magnitude;

    public Vector3D() {

    }

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    static Vector3D add(Vector3D l, Vector3D r) {
        return new Vector3D(l.getX() + r.getX(), l.getY() + r.getY(), l.getZ() + r.getZ());
    }

    static Vector3D subtract(Vector3D l, Vector3D r) {
        return new Vector3D(l.getX() - r.getX(), l.getY() - r.getY(), l.getZ() - r.getZ());
    }

    static Vector3D multiply(double scalar, Vector3D v) {
        return new Vector3D(scalar * v.getX(), scalar * v.getY(), scalar * v.getZ());
    }

    // Euclidean distance
    public double eqDistance(Vector3D v) {
        // Math.pow(x, 2) get replaced by x*x at compile time, I've heard.
        return Math.sqrt(Math.pow(v.getX() - this.x, 2) + Math.pow(v.getY() - this.y, 2) + Math.pow(v.getZ() - this.z, 2));
    }

    public void scalarMult(double k) {
        this.x *= k;
        this.y *= k;
        this.z *= k;
    }

    public void addVector(Vector3D v) {
        this.x += v.getX();
        this.y += v.getY();
        this.z += v.getZ();
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

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    void computeMagnitude() {
        magnitude = Math.sqrt(x*x + y*y + z*z);
    }



}
