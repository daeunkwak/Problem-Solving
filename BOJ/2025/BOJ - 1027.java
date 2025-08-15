package just.p_0815;

import java.util.*;

/**
 * title : 고층 건물
 * date : 2025-08-15
 */
public class boj1027 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] heights = new int[N];
        for (int i = 0; i < N; i++) {
            heights[i] = sc.nextInt();
        }

        int cnt = 0;
        int maxCnt = 0;
        for (int i = 0; i < N; i++) {
            cnt = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (isVisible(i, j, heights)) {
                    cnt++;
                }
            }
            for (int j = i + 1; j < N; j++) {
                if (isVisible(i, j, heights)) {
                    cnt++;
                }
            }
            maxCnt = Math.max(maxCnt, cnt);
        }

        System.out.println(maxCnt);
    }

    private static boolean isVisible(int i, int j, int[] heights) {
        if (i < j) {
            double slope = (double)(heights[j] - heights[i]) / (j - i);
            for (int k = i + 1; k < j; k++) {
                double temp = (double)(heights[k] - heights[i]) / (k - i);
                if (temp >= slope) {
                    return false;
                }
            }
        } else {
            double slope = (double)(heights[j] - heights[i]) / (j - i);
            for (int k = j + 1; k < i; k++) {
                double temp = (double)(heights[k] - heights[i]) / (k - i);
                if (temp <= slope) {
                    return false;
                }
            }
        }
        return true;
    }
}
