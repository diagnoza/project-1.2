package com.company;

public interface ThrustController {
    // Some controller may make use of this method to initialize and precompute how they
    // are going to distribute their thrust thru time
    Vector2D initThrust(Lander lander);

    // will make the Thrust Controller "thrust" for timedelta seconds. (computes and returns thrust)
    Vector2D thrust(double timedelta);

    // returns the last thrust computed (which means ThrustController should keep track of it)
    Vector2D getLastThrust();

    // returns the current mass of the Thrust Module in the Lander
    double getFuelMass();
}
