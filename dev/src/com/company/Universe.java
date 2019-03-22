package com.company;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Universe {
    public ArrayList<SpaceObject> spaceObjects;

    //
    private Timestamp initTime;

    // how many seconds is a timestep
    private int timestep;


    public Universe() {
        timestep = 1 * 360;
        spaceObjects = new ArrayList<SpaceObject>();
    }

    public void loadData() throws IOException {
        // TODO: from file..
        //                                  name            position                        velocity                    mass
        spaceObjects.add(new SpaceObject("Sun", new Vector3D(0, 0, 0), new Vector3D(0, 0, 0), 1.989e+30, 1350, ImageIO.read(new File("src\\com\\company\\img_planets\\Sun.jpg"))));
        spaceObjects.add(new SpaceObject("Earth", new Vector3D(1.521e+11, 0, 0), new Vector3D(0, 29290, 0), 5.972e+24, 350, ImageIO.read(new File("src\\com\\company\\img_planets\\Earth.jpg"))));
        spaceObjects.add(new SpaceObject("Mercury", new Vector3D(69.8e+9, 0, 0), new Vector3D(0, 38860, 0), 0.33011e+24, 160, ImageIO.read(new File("src\\com\\company\\img_planets\\Mercury.jpg"))));
        spaceObjects.add(new SpaceObject("Venus", new Vector3D(108.9e+9, 0, 0), new Vector3D(0, 34790, 0), 4.8675e+24, 200, ImageIO.read(new File("src\\com\\company\\img_planets\\Venus.jpg"))));
        spaceObjects.add(new SpaceObject("Moon", new Vector3D(1.521e+11 + 9.066e+8 + 10000, 0, 0), new Vector3D(0, 29504, 0), 0.07346e+24, 40, ImageIO.read(new File("src\\com\\company\\img_planets\\Moon.jpg"))));
        spaceObjects.add(new SpaceObject("Mars", new Vector3D(249.23e+9, 0, 0), new Vector3D(0, 21970, 0), 6.41e+23, 220, ImageIO.read(new File("src\\com\\company\\img_planets\\Mars.jpg"))));
        spaceObjects.add(new SpaceObject("Jupiter", new Vector3D(816.6e+9, 0, 0), new Vector3D(0, 12440, 0), 1898.19e+24, 800, ImageIO.read(new File("src\\com\\company\\img_planets\\Jupiter.jpg"))));
        spaceObjects.add(new SpaceObject("Saturn", new Vector3D(1514.5e+9, 0, 0), new Vector3D(0, 9090, 0), 568.34e+24, 900, ImageIO.read(new File("src\\com\\company\\img_planets\\Saturn.jpg"))));
        spaceObjects.add(new SpaceObject("Uranus", new Vector3D(3003.6e+9, 0, 0), new Vector3D(0, 6490, 0), 86.813e+24, 480, ImageIO.read(new File("src\\com\\company\\img_planets\\Uranus.jpg"))));
        spaceObjects.add(new SpaceObject("Neptune", new Vector3D(4545.7e+9, 0, 0), new Vector3D(0, 5370, 0), 102.413e+24, 480, ImageIO.read(new File("src\\com\\company\\img_planets\\Neptune.jpg"))));
        spaceObjects.add(new SpaceObject("Pluto", new Vector3D(5906.38e+9, 0, 0), new Vector3D(0, 3710, 0), 0.01303 + 24, 320, ImageIO.read(new File("src\\com\\company\\img_planets\\Pluto.jpg"))));
/*
        spaceObjects.add(new SpaceObject("Earth", new Vector3D(1.521e+11, 0, 0), new Vector3D(0, 0, 0), 5.972e+24, 10));
        spaceObjects.add(new SpaceObject("Moon", new Vector3D(1.521e+11 + 9.066e+8, 0, 0), new Vector3D(0, 530,530), 0.07346e+24, 2));
  */
    }

    public void run() {
        // for (int i = 0; i < 24*270*3; i++) {
        //     doStep();
        // }
        System.out.println("Finished");
    }

    public void speedUp() {
        timestep += 30;
    }

    public void slowDown() {
        timestep -= 30;
    }

    public void doStep() {
        // compute accel for each object
        for (SpaceObject so : spaceObjects) {
            so.computeAcc(spaceObjects);
        }

        // move objects (delta position)
        for (SpaceObject so : spaceObjects) {
            int t = timestep;
            so.stepPos(t);
        }

        // change velocity (delta velocity)
        for (SpaceObject so : spaceObjects) {
            int t = timestep;
            so.stepVel(t);
        }
    }
}
