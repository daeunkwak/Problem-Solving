/*
 author : https://github.com/daeunkwak/Problem-Solving
 title : 2xn 타일링 2
 description : 다이나믹 프로그래밍
 date : 2022-07-13
 */

package DP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11727 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;

        // 난 정말 공감이 안돼
        for(int i = 2; i <= N; i++){
            dp[i] = (dp[i-1] + (dp[i-2] * 2)) % 10007;
        }
        System.out.println(dp[N]);
    }
}
