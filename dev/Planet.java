import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

public class Planet {
    /*private static final double massOfSun = 1.989e30; // in kg
    private static final double radiusOfSun = 695510; // in km*/
    private static int lastID = 1;

    private String name;
    private int ID; //..
    private double mass; //..of the planet, in kg
    private double radius; //..of the planet, in km
    private int height;//... of the planet
    private int width;//... of the planet
    private double distance; //..from the Sun
    private int x, y;//... positions according to SUN, in km
    private Color planetFillColor;
    private Color planetBorderColor;
    public boolean fillBall = false;

    private static Planet[] planetsArray;
    private static Planet[] drawPlanetsArray;

    //For testing only
    public Planet(JFrame space) {
        planetBorderColor = Color.red;
        planetFillColor = Color.white;
        this.height = 12;
        this.width = 8;
        this.x = 13;
        this.y = 2;
        drawPlanet(space);

    }

    public Planet(String name, double mass, double radius, int height, int width, double distance, int x, int y, Color planetBorderColor, Color planetFillColor, JFrame space) {
        this.name = name;
        this.mass = mass;
        this.radius = radius;
        this.height = height;
        this.width = width;
        this.distance = distance;
        this.x = x;
        this.y = y;
        this.planetBorderColor = planetBorderColor;
        this.planetFillColor = planetFillColor;
        this.drawPlanet(space);

        this.ID = lastID;
        lastID++;
    }

    public Planet(String name, double mass, double radius, int height, int width, double distance, int x, int y, Color planetBorderColor, Color planetFillColor, JFrame space, Planet[] planets) {
        this.name = name;
        this.mass = mass;
        this.radius = radius;
        this.height = height;
        this.width = width;
        this.distance = distance;
        this.x = x;
        this.y = y;
        this.planetBorderColor = planetBorderColor;
        this.planetFillColor = planetFillColor;
        this.drawPlanet(space);
        this.drawManyPlanets(space, planets);

        this.ID = lastID;
        lastID++;
    }

    public static Planet[] getPlanetsArray() {
        return planetsArray;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public Color getPlanetFillColor() {
        return planetFillColor;
    }

    public void setPlanetFillColor(Color planetFillColor) {
        this.planetFillColor = planetFillColor;
    }

    public Color getPlanetBorderColor() {
        return planetBorderColor;
    }

    public void setPlanetBorderColor(Color planetBorderColor) {
        this.planetBorderColor = planetBorderColor;
    }

    //draw planets

    public void drawPlanet(JFrame space) {
        space.getContentPane().add(new SingleDrawComponent());
    }

    public void drawManyPlanets(JFrame space, Planet[] planets) {
        drawPlanetsArray = planets;
        space.getContentPane().add(new MultiDrawingComponent());
    }


    private class SingleDrawComponent extends JComponent {
        public void paintComponent(Graphics g) {

            if (fillBall) //Fill first, and then draw outline.
            {
                g.setColor(planetFillColor);
                g.fillOval(getX(), getY(), getHeight(), getWidth());
            }

            g.setColor(getPlanetBorderColor());
            g.drawOval(getX(), getY(), getHeight(), getWidth());

        }
    }

    private class MultiDrawingComponent extends JComponent {
        public void paintComponent(Graphics g) {

            for (int i = 0; i < drawPlanetsArray.length; i++) {
                if (drawPlanetsArray[i].fillBall) //Fill first, and then draw outline.
                {
                    g.setColor(drawPlanetsArray[i].planetFillColor);
                    g.fillOval(drawPlanetsArray[i].getX(), drawPlanetsArray[i].getY(), drawPlanetsArray[i].getHeight(), drawPlanetsArray[i].getWidth());
                }

                g.setColor(drawPlanetsArray[i].getPlanetBorderColor());
                g.drawOval(drawPlanetsArray[i].getX(), drawPlanetsArray[i].getY(), drawPlanetsArray[i].getHeight(), drawPlanetsArray[i].getWidth());
            }
        }
    }
}