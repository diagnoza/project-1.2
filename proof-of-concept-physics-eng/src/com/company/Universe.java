package com.company;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Universe {
    private ArrayList<SpaceObject> spaceObjects;

    //
    private Timestamp initTime;

    // how many seconds is a timestep
    private int timestep;


    public Universe() {
        timestep = 60*60;
        spaceObjects = new ArrayList<SpaceObject>();
    }

    public void loadData() {
        // TODO: from file..
        //                                  name            position                        velocity                    mass
        spaceObjects.add(new SpaceObject("Sun", new Vector3D(0, 0, 0), new Vector3D(0, 0, 0), 1.989e+30));
        spaceObjects.add(new SpaceObject("Earth", new Vector3D(1.521e+11, 0, 0), new Vector3D(0, 29290, 0), 5.972e+24));
    }

    public void run() {
        for (int i = 0; i <= 2190; i++) {
            doStep();
        }
    }

    public void doStep() {
        // compute accel for each object
        for (SpaceObject so : spaceObjects) {
            so.computeAcc(spaceObjects);
        }

        // move objects (delta position)
        for (SpaceObject so : spaceObjects) {
            so.stepPos(timestep);
        }

        // change velocity (delta velocity)
        for (SpaceObject so : spaceObjects) {
            so.stepPos(timestep);
        }
    }
}
