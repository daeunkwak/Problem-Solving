package just.p_1001;

import java.util.*;

/**
 * title : 급상승
 * date : 2025-10-06
 */
public class boj23978 {
    public static void main(String[] args) {
        // 코인 k원 이상 현금화하기
        // N개의 날짜에 가격 X원으로 상승 > 이후 1원씩 낮아짐 > 오른날부터 매일 1개씩 매도
        // 최소 금액으로 상승시켜 현금화함

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   // 날짝 개수
        long k = sc.nextLong();   // k원 이상 현금화하려면

        long[] date = new long[n];
        for (int i = 0; i < n; i++) date[i] = sc.nextLong();

        long left = 1;
        long right = 1_000_000_000L;
        long answer = right;

        while (left <= right) {
            long mid = (left + right) / 2;

            // 정확한 타겟이 없으므로 등호 포함해서 탐색, answer에 갱신하고 계속 탐색
            if (calculate(date, mid) >= k) {
                answer = mid;   // 업데이트하고
                right = mid - 1; // 더 작은값 찾기
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    private static long calculate(long[] date, long x) {
        long cal = 0;

        for (int i = 0; i < date.length; i++) {
            long gap = x;
            if (i < date.length - 1) {
                gap = Math.min(x, date[i + 1] - date[i]);
            }

            long diff = x - gap;
            cal += (x * (x + 1) / 2) - (diff * (diff + 1) / 2);
        }

        return cal;
    }
}
