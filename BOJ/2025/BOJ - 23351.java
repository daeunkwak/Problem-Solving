package just.p_0818;

import java.util.*;

/**
 * title : 물 주기
 * date : 2025-08-18
 */
public class boj23351 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int A = sc.nextInt();
        int B = sc.nextInt();

        int[] pots = new int[N];
        for (int i = 0; i < N; i++) {
            pots[i] = K;
        }

        int cnt = 1;
        while (true) {
            int minStart = 0;
            int minSum = getRangeSum(pots, 0, A);

            // 합이 최소인 구간 구하기
            for (int start = 1; start <= N - A; start++) {
                int currentSum = getRangeSum(pots, start, start + A);
                if (currentSum < minSum) {
                    minSum = currentSum;
                    minStart = start;
                }
            }

            // 물주기
            for (int i = minStart; i < minStart + A; i++) {
                pots[i] += B;
            }

            // 모든 화분 감소
            for (int i = 0; i < N; i++) {
                pots[i]--;
            }

            boolean hasDead = false;
            for (int i = 0; i < N; i++) {
                if (pots[i] <= 0) {
                    hasDead = true;
                }
            }

            if (hasDead) {
                System.out.println(cnt);
                break;
            }

            cnt++;
        }
    }

    private static int getRangeSum(int[] arr, int start, int end) {
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum += arr[i];
        }
        return sum;
    }

}
