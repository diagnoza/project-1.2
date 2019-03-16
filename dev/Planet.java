public class Planet {
    private static final double massOfSun = 1.989e30; // in kgs
    private static final double radiusOfSun = 695510; // in kms
    private static int lastID = 1;

    private String name;
    private int ID; //..
    private double mass; //..of the planet, in kgs
    private double radius; //..of the planet, in kms
    private double distance; //..from the Sun


    public Planet(String name, double mass, double radius, double distance) {
        this.name = name;
        this.mass = mass;
        this.radius = radius;
        this.distance = distance;

        this.ID = lastID;
        lastID++;
    }


}
