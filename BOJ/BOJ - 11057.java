/*
 author : https://github.com/daeunkwak/Problem-Solving
 title : 오르막 수
 description : 다이나믹 프로그래밍
 date : 2022-07-18
 */

package DP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11057 {
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dp = new int[N+1][10];

        for(int i = 0; i < 10; i++){
            dp[1][i] = 1;
        }

        for(int i = 2; i <= N; i++){
            for(int j = 0; j < 10; j++){
                dp[i][j] = summ(i, j);
            }
        }

        int res = 0;
        for(int i = 0; i <10; i++){
            res += dp[N][i];
        }
        System.out.println(res % 10007);
    }

    // static의 유무??
    static int summ(int N, int i){
        int res = 0;
        for(int m = i; m < 10; m++){
            res += dp[N - 1][m];
        }
        return res % 10007;
    }
}
