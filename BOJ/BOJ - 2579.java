/*
 author : https://github.com/daeunkwak/Problem-Solving
 title : 계단 오르기
 description : 다이나믹 프로그래밍
 date : 2022-08-13
 */

package DP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2579 {

    static Integer dp[];
    static int arr[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        dp = new Integer[N + 1];
        arr = new int[N + 1];

        for(int i = 1; i <= N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = arr[0];
        dp[1] = arr[1];

        if (N >= 2){
            dp[2] = arr[1] + arr[2];
        }

        System.out.println(stair(N));
    }

    static int stair(int N){
        if (dp[N] == null){
            dp[N] = Math.max(stair(N - 2), stair(N - 3) + arr[N - 1]) + arr[N];
        }

        return dp[N];
    }
}
