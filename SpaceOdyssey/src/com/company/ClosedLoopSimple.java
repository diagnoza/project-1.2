package com.company;

//Closed-loop version which reads the height and adjusts the thrust according to value.
public class ClosedLoopSimple implements ThrustController {
    private UniverseData universeData;
    private Lander lander;
    private double maxThrust;
    private Vector2D maxThrottle;
    private Vector2D halfThrottle;
    private Vector2D optimalThrottle;

    private Vector2D lastThrust;
    private double lastTime;

    private double fuel;
    private double maxBurnRate;



    double numSecsAtMaxThrust;

    double timeThrustStart;
    double timeThrustStop;

    ClosedLoopSimple(Lander lander, UniverseData universeData) {
        this.universeData = universeData;
        this.lander = lander;

        // 16,000 N  (Ascent Propulsion System for Apollo Lunar Lander)
        // approx 1,500 N max thrust needed
        // 750kg mass of the lander
        // 1.36 Titan g
        this.maxThrust = 16000;
        this.maxThrottle = new Vector2D(0,4);
        this.halfThrottle = new Vector2D(0,2);
        this.optimalThrottle = new Vector2D(0,1.36);

        // burn rate
        // kg/s at max throttle
        this.maxBurnRate = 0.000001;
        // in kg
        this.fuel = 150;

    }

    @Override
    public Vector2D initThrust(Lander lander) {
        Vector2D retVal;
        double curVelY = lander.getVelocity().getY();
        // first counter initial velocity (even though it's also being accelerated
        // numSecsAtMaxThrust = ((lander.getVelocity().getY()) * lander.getMass()) / maxThrust;

        //timeThrustStart = universeData.getTimeElapsed() + 10;
        //timeThrustStop = timeThrustStart + numSecsAtMaxThrust;

        return null;
    }

    @Override
    public Vector2D thrust(double timedelta) {

        Vector2D retVal = new Vector2D(0, 0);

        double curVelY = lander.getVelocity().getY();
        double curPosY = lander.getPosition().getY();
        // Use thrusters for soft landing

        if (curVelY<=0){
            // Free fall until 100km from surface
            if (curPosY > 100e3) {

            }
            // Controlled deceleration from 2 to 1km from surface
            if (curPosY <= 2e3) {
                if (curVelY < -2) {
                    retVal = maxThrottle;
                }else if (curVelY < -1) {
                    retVal = halfThrottle;
                }
            }
            // Descent at <1m/s from 1km above surface
            if (curPosY <= 1e3){
                if (curVelY < -0.5) {
                retVal = halfThrottle;
            } else {
                    retVal = optimalThrottle;
                }
            }
        }

        // consume fuel (throttle [0%-100%] * maxBurnRate * timedelta)
        fuel -= (retVal.getMagnitude() / maxThrottle.getMagnitude()) * maxBurnRate * timedelta;

        // TODO: smoother transition to zero thrust?
        if (fuel <= 0) {
            retVal = new Vector2D(0, 0);
        }

        this.lastThrust = retVal;
        //pass how much thrust used in total
        //lander.fuelUsed(retVal);
        return retVal;
    }

    @Override
    public Vector2D getLastThrust() {
        return this.lastThrust;
    }

    @Override
    public double getFuelMass() {
        return this.fuel;
    }

}
