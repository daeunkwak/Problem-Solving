package just.p_0814;

import java.util.*;

/**
 * title : 헤븐스 키친2
 * date : 2025-08-14
 */
public class boj15673 {
    static final int SIZE = 1000009;

    static int[] l_max = new int[SIZE];
    static int[] r_max = new int[SIZE];
    static int[] l_min = new int[SIZE];
    static int[] r_min = new int[SIZE];
    static int[] arr = new int[SIZE];
    static int[] l_maxS = new int[SIZE];
    static int[] r_maxS = new int[SIZE];
    static int[] l_minS = new int[SIZE];
    static int[] r_minS = new int[SIZE];

    public static void main(String[] args) {
        // 연속된 요리사들을 골라 두 팀으로 나누어 팀전 진행
        // 두 팀 스타성 곱의 최대 구하기, 스타성은 음수포함

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
        }

        for (int i = 1; i <= n; i++) {
            l_max[i] = Math.max(0, l_max[i-1] + arr[i]);
            l_maxS[i] = Math.max(l_maxS[i-1], l_max[i]);

            l_min[i] = Math.min(0, l_min[i-1] + arr[i]);
            l_minS[i] = Math.min(l_minS[i-1], l_min[i]);
        }

        for (int i = n; i >= 1; i--) {
            r_max[i] = Math.max(0, r_max[i+1] + arr[i]);
            r_maxS[i] = Math.max(r_maxS[i+1], r_max[i]);

            r_min[i] = Math.min(0, r_min[i+1] + arr[i]);
            r_minS[i] = Math.min(r_minS[i+1], r_min[i]);
        }

        long ans = 0;
        if (n == 2) {
            ans = (long) arr[1] * arr[2];
        }

        // + * +, - * -, + * - 케이스를 나눠서 생각할 필요가 없음
        for (int i = 1; i <= n && n != 2; i++) {
            ans = Math.max(ans, (long) l_max[i] * r_maxS[i+1]);
            ans = Math.max(ans, (long) l_min[i] * r_minS[i+1]);
        }

        System.out.println(ans);

    }
}
