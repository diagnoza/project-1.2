package com.company;

// !! !! Simplest Most stupid Thrust controller ... //
public class OpenLoopSimple implements ThrustController {

    private UniverseData universeData;
    private Lander lander;
    private double maxThrust;


    double numSecsAtMaxThrust;

    double timeThrustStart;
    double timeThrustStop;


    Vector2D lastThrust;

    OpenLoopSimple(Lander lander, UniverseData universeData) {
        this.universeData = universeData;
        this.lander = lander;

        // 16,000 N  (Ascent Propulsion System for Apollo Lunar Lander)
        this.maxThrust = 16000;
        this.lastThrust = new Vector2D(0, 0);
    }

    @Override
    public Vector2D initThrust(Lander lander) {
        // !! Very rudimentary !! //


        // !! NOT BEING USED NOW !!

        // first counter initial velocity (even though it's also being accelerated
        numSecsAtMaxThrust = ((lander.getVelocity().getY()) * lander.getMass()) / maxThrust;

        timeThrustStart = universeData.getTimeElapsed() + 10;
        timeThrustStop = timeThrustStart + numSecsAtMaxThrust;

        return null;
    }

    @Override
    public Vector2D thrust(double timedelta) {
        Vector2D retVal = new Vector2D(0, 1.3598);

        this.lastThrust = retVal;
        return retVal;
    }

    @Override
    public Vector2D getLastThrust() {
        return this.lastThrust;
    }

    @Override
    public double getFuelMass() {
        return 0;
    }
}
