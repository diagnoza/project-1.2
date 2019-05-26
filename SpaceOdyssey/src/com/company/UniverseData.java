package com.company;

/*
 * Holds all data related to the UniverseData. Things like what time it is (timeElapsed), what's the
 * force of gravity on the surface of titan..
 */
public class UniverseData {

    private double timeElapsed;
    private Vector2D gTitan;

    public double getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(double timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public Vector2D getgTitan() {
        return gTitan;
    }

    public void setgTitan(Vector2D gTitan) {
        this.gTitan = gTitan;
    }


    public void elapseTime(double delta) {
        this.timeElapsed += delta;
    }


    public UniverseData() {
        timeElapsed = 0;
        gTitan = new Vector2D(0, -1.36);
    }
}
