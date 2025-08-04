
import java.util.*;

/**
 * title : 관악산 등산
 * date : 2025-08-04
 */
public class boj14699 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        // idx == 쉼터 번호
        int[][] heights = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            heights[i][0] = i;
            heights[i][1] = sc.nextInt();
        }

        // 낮 -> 높 단방향 그래프
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            if (heights[start][1] < heights[end][1]) {
                graph.get(start).add(end);
            } else {
                graph.get(end).add(start);
            }
//            System.out.println(start+"번 섬 > 높이 " + heights[start][1] + "  " + end+"번 섬 > 높이 " + heights[end][1]);
//            System.out.println("key : " + start + " val size : " + graph.get(start).size());
//            System.out.println("key : " + end + " val size : " + graph.get(end).size());
        }

        int[] dp = new int[N + 1];
        Arrays.fill(dp, 1);

        // 쉼터 높이 내림차순 정렬
        Arrays.sort(heights, (a, b) -> b[1] - a[1]);

        for (int i = 0; i < N; i++) {
            int max = 0;
            for (int rest : graph.get(heights[i][0])) {
                max = Math.max(max, dp[rest]);
            }
            dp[heights[i][0]] += max;
            //System.out.println(dp[i]);
        }

        for (int i = 1; i <= N; i++) {
            System.out.println(dp[i]);
        }

    }
}
