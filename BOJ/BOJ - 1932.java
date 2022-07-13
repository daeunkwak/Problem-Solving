/*
 author : https://github.com/daeunkwak/Problem-Solving
 title : 정수 삼각형
 description : 다이나믹 프로그래밍
 date : 2022-07-12
 */

package DP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1932 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][N];
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < i+1; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i < N; i++){
            for (int j = 0; j < N; j++){
                if (j == 0){
                    arr[i][j] = arr[i][j] + arr[i - 1][j];
                }
                else if (j == i){
                    arr[i][j] = arr[i][j] + arr[i - 1][j - 1];
                }
                else{
                    arr[i][j] = arr[i][j] + Math.max(arr[i - 1][j - 1], arr[i - 1][j]);
                }
            }
        }
        Arrays.sort(arr[N-1]);
        System.out.println(arr[N-1][N-1]);
    }
}
