import java.util.Arrays;

/**
 * find out the GCD of two numbers.
 * find LCM of two numbers.
 */
public class GCD {
    public static void main(String[] args) {
        int a = 15;
        int b = 20;
        System.out.println(gcd(a, b));
        System.out.println(iterativeGcd(a, b));
        System.out.println(lcm(a, b));

        System.out.println(gcd(99, 81));
        System.out.println(iterativeGcd(99, 81));
        int[] arr = {99, 81, 18};
        System.out.println(gcdOfArray(arr));

        int[] num1 = {1, 2};
        int[] num2 = {3, 2};
        System.out.println(Arrays.toString(addFraction(num1, num2)));
    }

    static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static int iterativeGcd(int a, int b) {
        while (a > 0 && b > 0) {
            if (a > b) {
                a = a % b;
            } else {
                b = b % a;
            }
        }

        return a == 0?b : a;
    }

    static int lcm(int a, int b) {
        int gcd = gcd(a, b);
        return (a * b)/gcd;
    }

    static int gcdOfArray(int[] arr) {
        int gcd = arr[0];
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            gcd = gcd(gcd, arr[i]);
            if (gcd == 1)
                return 1;
        }
        return gcd;
    }

    static int[] addFraction(int[] a, int[] b) {
        int lcm = lcm(a[1], b[1]);
        int numerator = a[0]*(lcm/a[1]) + b[0]*(lcm/b[1]);
        int gcd = gcd(lcm, numerator);
        return new int[]{numerator/gcd, lcm/gcd};
    }
}
