public class MaximizeSumOfSquareOfDigits {
    public static void main(String[] args) {
        int num = 2;
        int sum = 3;
        System.out.println("Ans: " + maxSumOfSquareOfDigits(num, sum));
        System.out.println("Ans: " + maxSumOfSquareOfDigitsEasy(2, 19));
    }

    public static String maxSumOfSquareOfDigits(int num, int sum) {
        if (sum > 9 * num)
            return "";
        StringBuilder result = new StringBuilder();
        int digit = 9;
        while (sum > 0 || num > 0) {
            if (digit <= sum) {
                result.append(digit);
                sum -= digit;
                num--;
            } else {
                digit--;
            }
        }
        return result.toString();
    }

    public static String maxSumOfSquareOfDigitsEasy(int num, int sum) {
        if (sum > 9 * num)
            return "";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < num; i++) {
            int digit = Math.min(9, sum);
            result.append(digit);
            sum -= digit;
        }
        if (sum != 0)
            return "";
        return result.toString();
    }
}
