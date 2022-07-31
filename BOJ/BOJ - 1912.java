/*
 author : https://github.com/daeunkwak/Problem-Solving
 title : 연속합
 description : 다이나믹 프로그래밍
 date : 2022-07-31
 */

package DP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1912 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];
        dp[0] = arr[0];

        int max = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
