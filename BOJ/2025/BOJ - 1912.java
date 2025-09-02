package just.p_0901;

import java.util.*;

/**
 * title : 연속합
 * date : 2025-09-01
 */
public class boj1912 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // 음수가 나와도 누적합이 양수면 선택해도 ok -> 최대 갱신
        // 10 -4 3 1 5 6   -35 12 21 -1
        // 10 6 9 10 15 21 -14 12 33 32

        int[] arr = new int[n];
        int maxVal = Integer.MIN_VALUE;
        int sum = 0;
        int maxSum = arr[0];
        boolean flag = false;
        for (int i = 0; i < n; i ++) {
            arr[i] = sc.nextInt();
            maxVal = Math.max(maxVal, arr[i]);
            if (sum + arr[i] > 0) {
                sum += arr[i];
                flag = true;
            } else {
                sum = 0;
            }
            maxSum = Math.max(maxSum, sum);
        }

        if (!flag) maxSum = maxVal;
        System.out.println(maxSum);
    }
}
