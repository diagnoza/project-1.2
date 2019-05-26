package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Handles all computations related to the universe.
 * Simulator
 */
public class UniverseComputer {

    private Lander lander;

    private UniverseData universeData;

    // timestep in seconds
    private double timestep;

    private File exportDataFile;
    private FileWriter fileWriter;
    private BufferedWriter bufFileWriter;

    public UniverseComputer(UniverseData universeData) {
        this.universeData = universeData;
        timestep = 1;

        Vector2D initPos = new Vector2D(-100e3, 250e3);
        Vector2D initVel = new Vector2D(1320, 0);
        lander = new Lander(initPos, initVel, universeData.getgTitan(), universeData);

        // list of objects
        // listObjs.add(lander)

        exportDataFile = new File("tmp.data");
        try {
            fileWriter = new FileWriter(exportDataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bufFileWriter = new BufferedWriter(fileWriter);
    }


    public void run() {
        // go through list of objects and make them interact with the laws of the universe
        // (for now just lander)

        // Choose Different Thrust lander controller
        //lander.setThrustController(new OpenLoopSimple(lander, universeData));

        lander.init();


        while (lander.getPosition().getY() > 0) {
            //System.out.println();
            //System.out.println("Time: " + universeData.getTimeElapsed());
            //System.out.println(lander);
            interactWithObject(lander);

            savePointToFile();

            universeData.elapseTime(timestep);
            if (universeData.getTimeElapsed() > 40) {
                //break;
            }
        }
    }


    private void savePointToFile() {
        try {
            bufFileWriter.write("Time: " + universeData.getTimeElapsed() + "\r\n" + lander.toString() + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO: s/Lander/Interface
    private Vector2D computeDragAccelVector(Lander lander) {
        Vector2D retVal = new Vector2D(lander.getVelocity());
        retVal.scalarMult(-1 * lander.getVelocity().getMagnitude());
        retVal.scalarMult(lander.getDragArea() * lander.getDragCoeff() * 0.5);


        // Acceleration due to this force  a = F / m
        retVal.scalarMult(1 / (double) lander.getMass());
        // TODO: ρ(p)  [ density(position) ]

        return retVal;
    }


    // TODO: s/Lander/Nice Interface/
    public void interactWithObject(Lander lander) {
        // this has to be majorly inefficient. But I blame java. Not the jvm. Java as a language.
        Vector2D curAccel = new Vector2D(universeData.getgTitan());
//      curAccel.addVector(lander.getThrustAccel());
        lander.setDragForce(computeDragAccelVector(lander));
        curAccel.addVector(lander.getDragForce());
        curAccel.addVector(lander.computeThrust(timestep));
        lander.setAccel(curAccel);

        // compute new velocity
        Vector2D velDelta = new Vector2D(lander.getAccel());
        velDelta.scalarMult(timestep);
        lander.getVelocity().addVector(velDelta);

        // compute new position
        Vector2D posDelta = new Vector2D(lander.getVelocity());
        posDelta.scalarMult(timestep);
        lander.getPosition().addVector(posDelta);
    }

}
