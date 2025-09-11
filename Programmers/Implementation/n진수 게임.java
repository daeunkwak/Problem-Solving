import java.util.*;

/**
 * title : n진수 게임
 * date : 2025-09-09
 */
class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";

        StringBuilder result = new StringBuilder();
        StringBuilder sequence = new StringBuilder();

        int cnt = 0;
        while (sequence.length() < t * m) {
            String num = Integer.toString(cnt, n).toUpperCase();
            sequence.append(num);
            cnt++;
        }

        // System.out.println(sequence);

        for (int i = p - 1; i < sequence.length() && result.length() < t; i += m) {
            result.append(sequence.charAt(i));
            // System.out.println("?? i : " + i);
        }

        return result.toString();
    }
}