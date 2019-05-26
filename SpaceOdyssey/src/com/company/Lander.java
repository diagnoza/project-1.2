package com.company;

public class Lander {

    private UniverseData universeData;

    private Vector2D position;
    private Vector2D velocity;
    private Vector2D accel;
    private Vector2D thrust;
    private Vector2D dragForce;
    // angle of the lander with respect to the horizon
    private double theta;
    // rotSpeed: rad/s
    private double rotSpeed;
    private double torque;
    private double mass;

    private ThrustController thrustController;

    /*
     * Getters and Setters
     */

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    public Vector2D getAccel() {
        return accel;
    }

    public void setAccel(Vector2D accel) {
        this.accel = accel;
    }

    public Vector2D getThrust() {
        return thrust;
    }

    public void setThrust(Vector2D thrust) {
        this.thrust = thrust;
    }

    public Vector2D getDragForce() {
        return dragForce;
    }

    public void setDragForce(Vector2D dragForce) {
        this.dragForce = dragForce;
    }

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    public double getRotSpeed() {
        return rotSpeed;
    }

    public void setRotSpeed(double rotSpeed) {
        this.rotSpeed = rotSpeed;
    }

    public double getTorque() {
        return torque;
    }

    public void setTorque(double torque) {
        this.torque = torque;
    }

    public double getMass() {
        return mass + thrustController.getFuelMass();
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    /*
     * Constructors
     */
    public Lander(Vector2D position, Vector2D velocity, Vector2D accel, UniverseData universeData) {
        this.universeData = universeData;

        // since these vectors are going to be modified later, I'd rather control their instantiation from here
        this.position = new Vector2D(position.getX(), position.getY());
        this.velocity = new Vector2D(velocity.getX(), velocity.getY());
        this.accel = new Vector2D(accel.getX(), accel.getY());
        this.dragForce = new Vector2D(0, 0);

        // default values
        this.theta = Math.PI / 2;
        this.rotSpeed = 0;
        this.torque = 0;
        this.mass = 750;

        this.thrustController = new ClosedLoopSimple(this, universeData);
    }

    public double getDragArea() {
        return 1;
    }

    public double getDragCoeff() {
        return 1;
    }

    public void setThrustController(ThrustController tc) {
        this.thrustController = tc;
    }

    public Vector2D computeThrust(double timedelta) {
        return this.thrustController.thrust(timedelta);
    }

    public void init() {
        this.thrustController.initThrust(this);
    }

    public String toString() {
        return "Lander: " + "\r\n"
             + " Position: " + position.toString() + "\r\n"
             + " Velocity: " + velocity.toString() + "\r\n"
             + " Acceleration: " + accel.toString() + "\r\n"
             + " DragDeceleration: " + dragForce.toString() + "\r\n"
             + " Thrust: " + thrustController.getLastThrust() + "\r\n"
             + " Fuel: " + thrustController.getFuelMass() + "\r\n";
    }

    public String toStringCVS() {
        return          position.toStringCVS()
                + "," + velocity.toStringCVS()
                + "," + accel.toStringCVS()
                + "," + dragForce.toStringCVS()
                + "," + thrustController.getLastThrust().toStringCVS()
                + "," + thrustController.getFuelMass()
                + "\n";
    }
}
