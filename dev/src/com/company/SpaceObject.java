package com.company;


import java.awt.image.BufferedImage;
import java.util.List;

// "Celestial Objects" implies that the objects are "natural", whereas Space Object does not.
public class SpaceObject {
    // gravitational constant (TODO: move to Universe.java)
    public static final double G = 6.674 * Math.pow(10, -11);

    // name of obeject (e.g. cassini, Earth, Sun..)
    private String name;
    // position (vector origin: Sun)
    private Vector3D pos;
    // velocity (origin: this space object itself)
    private Vector3D vel;
    // acceleration (origin: this space object itself)
    private Vector3D acc;
    private double mass;
    private int radius;
    private BufferedImage img;

    public SpaceObject() {
    }

    public SpaceObject(String name, Vector3D pos, Vector3D vel, double mass, int radius, BufferedImage img) {
        this.name = name;
        this.pos = pos;
        this.vel = vel;
        this.mass = mass;
        this.radius = radius;
        this.img = img;
    }

    public Vector3D getPos() {
        return pos;
    }

    public void setPos(Vector3D pos) {
        this.pos = pos;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void stepPos(int timestep) {
        // pos = pos + vel * dt
        pos.addVector(Vector3D.multiply(timestep, vel));
    }

    public void stepVel(int timestep) {
        // vel = vel + acc*dt
        vel.addVector(Vector3D.multiply(timestep, acc));
    }

    public void computeAcc(List<SpaceObject> spaceObjects) {
        Vector3D netAccel = new Vector3D();

        // computes each component of the net acceleration (each acceleration due to the different bodies
        // that exert a gravitational force on this object)
        for (SpaceObject spaceObject : spaceObjects) {
            // skip if it's this very object (TODO: is this right? ==?)
            if (spaceObject == this)
                continue;

            // new net accel is given by:
            //          (distance * G * Mass_other_object) / (|distance|^3)
            // Where distance is a vector (from Mars to Earth for example)

            Vector3D distance = Vector3D.subtract(spaceObject.pos, pos);
            distance.computeMagnitude();

            Vector3D accelComponent = Vector3D.multiply((G * spaceObject.getMass()) / Math.pow(distance.getMagnitude(), 3), distance);
            netAccel.addVector(accelComponent);
        }

        acc = netAccel;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public Vector3D getAcc() {
        return acc;
    }

    public void setAcc(Vector3D acc) {
        this.acc = acc;
    }

    public BufferedImage getImg() {
        return this.img;
    }
}
