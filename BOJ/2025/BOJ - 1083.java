package just.p_0826;

import java.util.*;

/**
 * title : 소트
 * date : 2025-08-26
 */
public class boj1083 {
    public static void main(String[] args) {
        // 연속된 두 개 원소 교환 S번 가능 -> 사전순 가장 뒷서는것

        // N, S : 1,000,000
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        if (N == 1) {
            System.out.println(arr[0]);
            return;
        }

        // pq -> 교환했을 때 앞자리가 커짐 && 가장 앞에 있는 수 -> pq는 불가능
        // 3 5 1 2 4 -> 5 3 1 2 4 -> 5 3 2 1 4
        // 가장 앞자리가 커지는 수부터 채우기

        int S = sc.nextInt();
        for (int i = 0; i < N; i++) {
            if (S <= 0) break;
            int maxVal = arr[i];
            int maxIdx = i;

            // 남은 횟수 최대한 활용해서 바꿀 수 있는 가장 큰 수 찾기
            int range = Math.min(N, i + S + 1);
            for (int j = i + 1; j < range; j++) {
                if (arr[j] > maxVal) {
                    maxVal = arr[j];
                    maxIdx = j;
                }
            }

            for (int j = maxIdx; j > i; j--) {
                int temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
            }

            S -= (maxIdx - i);
        }

        for (int i = 0; i < N; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
