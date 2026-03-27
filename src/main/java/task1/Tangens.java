package task1;

public class Tangens {

    public static double tgSeries(double x, int n) {

        if (Double.isNaN(x) || Double.isInfinite(x)) {
            return Double.NaN;
        }
        double reduced = x % Math.PI;

        double sign = 1.0;
        if (reduced > Math.PI/2) {
            reduced = Math.PI - reduced;
            sign = -1.0;
        } else if (reduced < -Math.PI/2) {
            reduced = -Math.PI - reduced;
            sign = -1.0;
        }
        if (Math.abs(Math.abs(reduced) - Math.PI/2) < 1e-10) {
            return Double.NaN;
        }

        double[] coefficients = {
                1.0,
                1.0 / 3.0,
                2.0 / 15.0,
                17.0 / 315.0,
                62.0 / 2835.0,
                1382.0 / 155925.0
        };

        double result = 0.0;

        for (int i = 0; i < n && i < coefficients.length; i++) {
            int pow = 2 * i + 1;
            result += coefficients[i] * Math.pow(reduced, pow);
        }

        return sign * result;
    }
}