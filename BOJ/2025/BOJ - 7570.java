
import java.io.IOException;
import java.util.*;
import java.io.*;

/**
 * title : 줄 세우기
 * date : 2025-10-01
 */
public class boj7570 {
    public static void main(String[] args) throws IOException {
        // LIS찾기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] childs = new int[N];
        int[] dp = new int[N + 1];

        int maxLength = 0;
        for (int i = 0; i < N; i++) {
            childs[i] = Integer.parseInt(st.nextToken());
            dp[childs[i]] = dp[childs[i] - 1] + 1;
            maxLength = Math.max(maxLength, dp[childs[i]]);
        }
        System.out.println(N - maxLength);

//        Arrays.fill(dp, 1);
//        int maxLength = 1;
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < i; j++) {
//                if (childs[i] > childs[j]) {
//                    dp[i] = Math.max(dp[i], dp[j] + 1);
//                    maxLength = Math.max(maxLength, dp[i]);
//                }
//            }
//        }

        // System.out.println(N - maxLength);
    }
}
