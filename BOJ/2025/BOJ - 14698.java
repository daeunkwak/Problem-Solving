
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * title : 전생했더니 슬라임 연구자였던 건에 대하여 (Hard)
 * date : 2025-07-28
 */
public class boj14698 {
    private static long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            PriorityQueue<Long> pq = new PriorityQueue<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                pq.offer(Long.parseLong(st.nextToken()));
            }

            long result = 1;

            while (pq.size() > 1) {
                long a = pq.poll();
                long b = pq.poll();
                long merged = a * b;
                result = (result * (merged % MOD)) % MOD;
                pq.offer(merged);
            }

            if (N == 1) {
                System.out.println(1);
            } else {
                System.out.println(result);
            }
        }

    }
}
