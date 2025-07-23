package just.p_0722;

import java.util.*;

/**
 * title : 다이어트
 * date : 2025-07-22
 */
public class BOJ_1484 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int G = sc.nextInt();

        int max = 1;

        // N과 N+1 제곱의 차가 G보다 크면 가망 없음을 활용
        while (true) {
            if (Math.pow(max + 1, 2) - Math.pow(max, 2) > G) {
                break;
            } else {
                max++;
            }
        }

        List<Integer> results = new ArrayList<>();
        int end = max;
        while (end > 1) {
            for (int start = 1; start < end; start++) {
                int res = (int) (Math.pow(end, 2) - Math.pow(start, 2));
                // System.out.println("start : " + start + " end : " + end + " res : " + res);
                if (res == G){
                    results.add(end);
                    break;
                }
            }
            end--;
        }

        Collections.sort(results);
        for (int r : results) {
            System.out.println(r);
        }

        if (results.size() == 0) {
            System.out.println(-1);
        }
    }
}
