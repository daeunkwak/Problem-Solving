import java.io.*;
import java.util.*;

/**
 * title : 내려가기
 * date : 2025-03-07
 */

public class BOJ_2096 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] maxDp = new int[3];
        int[] minDp = new int[3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int x3 = Integer.parseInt(st.nextToken());

            if (i == 0) {
                maxDp[0] = minDp[0] = x1;
                maxDp[1] = minDp[1] = x2;
                maxDp[2] = minDp[2] = x3;
            } else {
                // 1번째 칸에서 내려가는 경우 > 1, 2번칸 중 큰거
                // 3번째 칸에서 내려가는 경우 > 2, 3번칸 중 큰거
                // 2번째 칸에서 내려가는 경우 > 1번째 칸, 3번째 칸에서 내려가는 경우 중 더 큰걸 고르면 됨
                int beforeMaxDp_0 = maxDp[0], beforeMaxDp_2 = maxDp[2];
                maxDp[0] = Math.max(maxDp[0], maxDp[1]) + x1;
                maxDp[2] = Math.max(maxDp[1], maxDp[2]) + x3;
                maxDp[1] = Math.max(Math.max(beforeMaxDp_0, maxDp[1]), beforeMaxDp_2) + x2;

                int beforeMinDp_0 = minDp[0], beforeMinDp_2 = minDp[2];
                minDp[0] = Math.min(minDp[0], minDp[1]) + x1;
                minDp[2] = Math.min(minDp[1], minDp[2]) + x3;
                minDp[1] = Math.min(Math.min(beforeMinDp_0, minDp[1]), beforeMinDp_2) + x2;
            }
        }

        bw.write(Math.max(maxDp[0], Math.max(maxDp[1], maxDp[2])) + " ");
        bw.write(Math.min(minDp[0], Math.min(minDp[1], minDp[2])) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}