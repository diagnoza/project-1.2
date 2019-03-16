public final class Formulas {
    /*Made final, so it cannot be extended in order to avoid
    * issues with statics*/

    public static final double mercAphelion = 69.8e6; // in kms
    public static final double mercPerihelion = 46e6;
    public static final double marsAphelion = 249.2e6;
    public static final double marsPerihelion = 206.6e6;
    public static final double earthAphelion = 152.1e6;
    public static final double earthPerihelion = 147.1e6;
    public static final double venusAphelion = 108.9e6;
    public static final double venusPerihelion = 107.5e6;
    public static final double jupiterAphelion = 816.6e6;
    public static final double jupiterPerihelion = 740.5e6;
    public static final double saturnAphelion = 1514.5e6;
    public static final double saturnPerihelion = 1352.6e6;
    public static final double uranusAphelion = 3003.6e6;
    public static final double uranusPerihelion = 2741.3e6;
    public static final double neptuneAphelion = 4545.7e6;
    public static final double neptunePerihelion = 4444.5e6;

    public double NewtonsSecondLaw (double mass, double accel) {
        return mass * accel;
    }

}
