/*
 author : https://github.com/daeunkwak/Problem-Solving
 title : 쉬운 계단 수
 description : 다이나믹 프로그래밍
 date : 2022-07-18
 */

package DP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10844 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[][] dp = new long[N + 1][10];
        for(int i = 1; i <= 9; i++){
            dp[1][i] = 1;
        }

        for(int i = 2; i <= N; i++){
            for(int j = 0; j < 10; j++) {
                if (j == 0){
                    dp[i][j] = dp[i-1][j+1] % 1000000000;
                }
                else if (j == 9){
                    dp[i][j] = dp[i-1][j-1] % 1000000000;
                }
                else{
                    dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % 1000000000;
                }
            }
        }

        long res = 0;
        for(int i = 0; i < 10; i++){
            res += dp[N][i];
        }
        System.out.println(res % 1000000000);
    }
}
