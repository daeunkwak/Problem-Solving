
/**
 * title : Pow(x, n)
 * date : 2025-08-19
 */
class Solution {
    public double myPow(double x, int n) {
        // x^n = x^n/2 * x^n/2 -> logn
        double answer = x;
        long exp = 1;

        // 2, 10
        // 2 -> 4 (2) -> 16 (4) -> 256 (8) -> 512, 1024

        while (exp * 2 <= Math.abs((long)n)) {
            answer *= answer;
            exp *= 2;
        }

        // for (long i = 0; i < Math.abs((long)n) - exp; i++) {
        //     answer *= x;
        // }

        // ***** 남은부분도 재귀로 풀어서 전체 O(logn) 유지
        long remaining = Math.abs((long)n) - exp;
        if (remaining > 0) {
            double remainingResult = myPow(x, (int)remaining);
            answer *= remainingResult;
        }

        if (n < 0) {
            return (1.0 / answer);
        } else if (n == 0) {
            return 1;
        } else {
            return answer;
        }
    }
}