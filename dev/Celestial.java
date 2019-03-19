/*public abstract class Celestial {

    private double mass; //..of the planet, in kg
    private double radius; //..of the planet, in km
    private double distance; //..from the Sun
    private double x, y; //...of positions from SUN
    private double vx = 0, vy = 0;//...of velocities
    private double ax = 0 , ay = 0; //...of accelerations
    private boolean isStationary = false;//...if its stationary

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

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public double getAx() {
        return ax;
    }

    public void setAx(double ax) {
        this.ax = ax;
    }

    public double getAy() {
        return ay;
    }

    public void setAy(double ay) {
        this.ay = ay;
    }

    public void react(Planet pl2){
      //  double x = calculateDistX(pl2); // delta x distance between time-frames
       // double y = calculateDistY(pl2);// delta y distance between time-frames
       // double r = calculateDistance(x, y);// delta vector distance between time-frames
        double f = (this.getMass() * pl2.getMass()) / Math.
                ,,
        ,


        r, 2); // gravitational
        // force

        double fx = f * (x / r); // x component of the force - f times cosine of
        // the angle
        double fy = f * (y / r); // x component of the force - f times sine of
        // the angle

        // calculate accelerations for both bodies, set vector orientation
        if (pl2.getX() > this.getX()) {
            this.setAx(fx / this.getMass());
            pl2.setAx(-fx / pl2.getMass());
        } else {
            this.setAx(-fx / this.getMass());
            pl2.setAx(fx / pl2.getMass());
        }

        if (pl2.getY() > this.getY()) {
            this.setAy(fy / this.getMass());
            pl2.setAy(-fy / pl2.getMass());
        } else {
            this.setAy(-fy / this.getMass());
            pl2.setAy(fy / pl2.getMass());
        }

        // calculate velocities for both bodies
        this.setVx(this.getVx() + this.getAx());
        this.setVy(this.getVy() + this.getAy());

        pl2.setVx(pl2.getVx() + pl2.getAx());
        pl2.setVy(pl2.getVy() + pl2.getAy());

        // calculate positions for both bodies
        this.setX(this.getX() + this.getVx());
        this.setY(this.getY() + this.getVy());

        pl2.setX(pl2.getX() + pl2.getVx());
        pl2.setY(pl2.getY() + pl2.getVy());

    }


}
*/