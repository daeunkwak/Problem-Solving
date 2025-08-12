package just.p_0811;

import java.util.*;

/**
 * title : 빗물
 * date : 2025-08-12
 */
public class boj14719 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int H = sc.nextInt();
        int W = sc.nextInt();

        int[] heights = new int[W];
        for (int i = 0; i < W; i++) {
            heights[i] = sc.nextInt();
        }

        int area = 0;
        for (int i = 1; i < W - 1; i++) {
            // 왼쪽
            int leftMax = 0;
            for (int l = 0; l < i; l++) {
                leftMax = Math.max(leftMax, heights[l]);

            }

            // 오른쪽
            int rightMax = 0;
            for (int r = i + 1; r < W; r++) {
                rightMax = Math.max(rightMax, heights[r]);
            }

            if (Math.min(leftMax, rightMax) > heights[i]) {
                area += Math.min(leftMax, rightMax) - heights[i];
            }
        }

        System.out.println(area);
    }
}