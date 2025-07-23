
import java.util.*;

/**
 * title : 가장 높은 탑 쌓기
 * date : 2025-07-22
 */
public class BOJ_2655 {

    static class Brick {
        int idx;
        int area;
        int height;
        int weight;

        public Brick(int idx, int area, int height, int weight) {
            this.idx = idx;
            this.area = area;
            this.height = height;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List<Brick> bricks = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int area = sc.nextInt();
            int height = sc.nextInt();
            int weight = sc.nextInt();
            bricks.add(new Brick(i + 1, area, height, weight));
        }

        // 가벼운 순으로 정렬
        bricks.sort((b1, b2) -> b1.weight - b2.weight);

        // N번째 블록이 가장 아래일 때 최대 높이 저장
        int[] dp = new int[N];
        List<List<Integer>> used = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            dp[i] = bricks.get(i).height;
            List<Integer> idx = new ArrayList<>();
            idx.add(bricks.get(i).idx);

            for (int j = 0; j < i; j++) {

                // 무게로 정렬했지만 넓이 조건도 같이 비교해서 최대 높이를 갱신하므로 답이 됨
                if (bricks.get(j).area < bricks.get(i).area && bricks.get(j).weight < bricks.get(i).weight) {
                    if (dp[j] + bricks.get(i).height > dp[i]) {
                        dp[i] = dp[j] + bricks.get(i).height;
                        idx = new ArrayList<>(used.get(j));
                        idx.add(bricks.get(i).idx);
                    }
                }
            }

            used.add(idx);
        }

        int max = 0;
        int idx = 0;
        for (int i = 0; i < N; i++) {
            if (dp[i] > max) {
                idx = i;
                max = dp[i];
            }
        }

        List<Integer> answer = used.get(idx);
        System.out.println(answer.size());
        for (int i : answer) {
            System.out.println(i);
        }
    }
}
