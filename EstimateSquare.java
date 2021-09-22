import java.text.*;
import java.util.*;

/**
 *
 * EstimateSquare
 *
 * @author Simon Biennier
 * @id 1710028
 * @description check for accuracy on approximation of a square using
 *              https://www.youtube.com/watch?v=PJHtqMjrStk this method
 *
 **/

public class EstimateSquare {

    protected static final Scanner sc = new Scanner(System.in);

    protected static final DecimalFormat df = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.US));

    /**
     * Formats a string
     *
     * @param decimals the number of decimal places to format to
     *
     * @return the formatted string
     */
    static String format(Object s, int decimals) {
        String f = "%." + decimals + "f";
        return String.format(Locale.US, f, s);
    }

    /**
     * Check if integer is perfect square
     *
     * @param x the integer
     *
     * @return x is perfect square
     */
    static boolean isPerfectSquare(int x) {
        if (x >= 0) {
            int sr = (int) Math.sqrt(x);
            return ((sr * sr) == x);
        }
        return false;
    }

    /**
     * Find previous perfect square
     *
     * @param n the integer to check for
     *
     * @return previous perfect square or n if already a perfect square, and 0 if n
     *         <=0;
     */
    static int previousPerfect(int n) {
        if (n > 0) {
            int prevN = (int) Math.floor(Math.sqrt(n));
            int N = (isPerfectSquare(n)) ? n : prevN;
            return N;
        } else {
            return 0;
        }
    }

    /**
     * Get difference between two numbers
     *
     * @param v first value
     * @param w second value
     * @return the difference
     */
    static double getDifference(double v, double w) {
        return Math.abs(v - w);
    }

    /**
     * Estimate the square using method above
     *
     *
     * @see @description for the method
     * @param k the integer inside the square root
     *
     * @return the estimation
     */
    static double estimateSquareOf(int k) {
        if (k > 1) {
            if (isPerfectSquare(k)) {
                return (double) Math.sqrt(k);
            } else {
                double prev = previousPerfect(k);
                double diff = k - (prev * prev);
                double frac = ((double) diff / (2 * prev));
                return (prev + frac);
            }
        } else {
            return 0;
        }
    }

    /**
     * Find the minimum (non-zero) of an ArrayList
     *
     * @param a the list
     *
     * @return the minimum
     */
    static double findMin(ArrayList<Double> a) {
        Double tmp = 1.0;
        for (Double el : a) {
            if (el > 0 && el < tmp) {
                tmp = el;
            }
        }
        return tmp;
    }

    /**
     * Recursively calculate differences using method and Math.sqrt value
     *
     * @param iterations number of iterations
     */
    static void calculateDiff(int iterations) {
        ArrayList<Double> diffs = new ArrayList<>();

        for (int i = 2; i < iterations; i++) {
            Double diff = getDifference(estimateSquareOf(i), Math.sqrt(i));
            System.out.print("for n=");
            System.out.print(i);
            System.out.print(", est=");
            System.out.print(String.format(Locale.US, "%.2f", (estimateSquareOf(i))));
            System.out.print(", diff=");
            System.out.println(String.format(Locale.US, "%.8f", diff));
            diffs.add(diff);
        }

        Double maxDif = Collections.max(diffs);
        Integer maxIdx = diffs.indexOf(maxDif) + 1;

        Double minDif = findMin(diffs);
        Integer minIdx = diffs.indexOf(minDif) + 1;

        System.out.print("max diff=");
        System.out.print(maxDif);
        System.out.print(" at n=");
        System.out.println(maxIdx);

        System.out.print("min diff=");
        System.out.print(minDif);
        System.out.print(" at n=");
        System.out.println(minIdx);
    }

    /**
     * Runs standard code
     *
     */
    static void run() {
        System.out.print("n: ");
        int in = sc.nextInt();
        System.out.print("estimate: ");
        System.out.println(format(estimateSquareOf(in), 4));
        System.out.print("diff: ");
        System.out.println(format(getDifference(estimateSquareOf(in), Math.sqrt(in)), 8));
    }

    public static void main(String[] args) {
        run();
    }
}