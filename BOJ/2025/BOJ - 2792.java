package just.p_0729;

import java.util.*;

/**
 * title : 보석 상자
 * date : 2025-07-29
 */
public class boj2792 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] jewels = new int[M];
        int max = 0;
        for (int i = 0; i < M; i++) {
            jewels[i] = sc.nextInt();
            max = Math.max(max, jewels[i]);
        }

        // 생각대로 풀지말고 그냥 이분탐색해
        int start = 1;
        int end = max;
        int min = Integer.MAX_VALUE;    // 최소 질투심

        while (start <= end) {
            int mid = (start + end) / 2;  // 질투심
            int cnt = 0;
            for (int color : jewels) {
                int colorCnt = 0;
                // 나누어 떨어지지 않는 경우 한명 더 추가
                if (color % mid == 0) {
                    colorCnt = color / mid;
                } else {
                    colorCnt = color / mid + 1;
                }
                cnt += colorCnt;
            }
            if (cnt <= N) {
                end = mid - 1;
                min = Math.min(min, mid);
            } else {
                start = mid + 1;
            }
        }

        System.out.println(min);
    }
}
