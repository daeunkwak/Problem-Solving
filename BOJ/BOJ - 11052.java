/*
 author : https://github.com/daeunkwak/Problem-Solving
 title : 카드 구매하기
 description : 다이나믹 프로그래밍
 date : 2022-07-12
 */

package DP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] D = new int[N+1];
        int[] cost = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i <= N ; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= i; j++){
                D[i] = Math.max(D[i], cost[j] + D[i - j]);
            }
        }

        System.out.println(D[N]);
    }
}
