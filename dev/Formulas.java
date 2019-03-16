public final class Formulas {
    /*Made final, so it cannot be extended in order to avoid
    * issues with statics*/

    public static final double mercOrbitRadius = 57.740e6; // in kms
    public static final double neptunOrbitRadius = 4493.650e6;


    public double NewtonsSecondLaw (double mass, double accel) {
        return mass * accel;
    }

}
