package just.p_1013;

import java.util.*;

/**
 * title : 회전 초밥
 * date : 2025-10-13
 */
public class boj15961 {
    public static void main(String[] args) {
        // 모두 다른 초밥
        // 임의 위치부터 k개 접시를 연속으로 먹음 > 할인된 가격
        // 초밥 쿠폰 발행 > 1번 행사 참가 > 초밥 하나를 무료로 제공
        // 먹을 수 있는 초밥 가짓수의 최댓값

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();   // 접시 수
        int d = sc.nextInt();   // 가짓수
        int k = sc.nextInt();   // 연속 접시 수
        int c = sc.nextInt();   // 쿠폰번호

        int[] sushi = new int[N];
        for (int i = 0; i < N; i++) sushi[i] = sc.nextInt();

        // 최적화
        // Map<Integer, Integer> count = new HashMap<>();
        int[] count = new int[d + 1];
        int types = 0;

        for (int i = 0; i < k; i++) {
            if (count[sushi[i]] == 0) types++;
            count[sushi[i]]++;
        }

        int max = (count[c] == 0) ? types + 1 : types;

        // ** 원형배열
        for (int i = 0; i < N; i++) {
            int newSushi = sushi[(i + k) % N];  // *** 모듈러
            int oldSushi = sushi[i];

            if (count[newSushi] == 0) types++;
            count[newSushi]++;

            count[oldSushi]--;
            if (count[oldSushi] == 0) types--;

            int current = (count[c] == 0) ? types + 1 : types;
            max = Math.max(max, current);
        }

        System.out.println(max);
    }
}
