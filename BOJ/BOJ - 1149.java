/*
 author : https://github.com/daeunkwak/Problem-Solving
 title : RGB거리
 description : 다이나믹 프로그래밍
 date : 2022-07-12
 */

package DP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1149 {

    final static int R = 0;
    final static int G = 1;
    final static int B = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] cost = new int[N][3];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            cost[i][R] = Integer.parseInt(st.nextToken());
            cost[i][G] = Integer.parseInt(st.nextToken());
            cost[i][B] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i < N; i++){
            cost[i][0] = cost[i][0] + Math.min(cost[i - 1][1], cost[i - 1][2]);
            cost[i][1] = cost[i][1] + Math.min(cost[i - 1][0], cost[i - 1][2]);
            cost[i][2] = cost[i][2] + Math.min(cost[i - 1][1], cost[i - 1][0]);
        }

        System.out.println(Math.min(Math.min(cost[N-1][R], cost[N-1][G]), cost[N-1][B]));
    }
}
