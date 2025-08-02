
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * title : 신입 사원
 * date : 2025-08-01
 */
public class boj1946 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            int[][] ranks = new int[N][2];

            for (int j = 0; j < N; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                ranks[j][0] = Integer.parseInt(st.nextToken());
                ranks[j][1] = Integer.parseInt(st.nextToken());
            }

            // 서류 순위 오름차순 정렬
            Arrays.sort(ranks, (a, b) -> {
                return a[0] - b[0];
            });

            // 서류 N등까지 탐색하면서, 면접 등수가 passRank 작기만하면 ok
            int cnt = 1;
            int passRank = ranks[0][1];
            for (int j = 1; j < N; j++) {
                if (ranks[j][1] < passRank) {
                    cnt++;
                }
                passRank = Math.min(passRank, ranks[j][1]);
            }

            System.out.println(cnt);
        }
    }
}
