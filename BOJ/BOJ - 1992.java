/**
 author : Kwak Daeun
 github : https://github.com/daeunkwak
 title : 쿼드 트리
 description : 분할 정복, 재귀
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1992 {
    public static int N, M;
    public static int[][] arr;
    public static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        arr = new int[N][N];
        StringTokenizer st;

        for(int i = 0; i < N; i++){
            String str = br.readLine();
            for(int j = 0; j < N; j++){
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        splitAndCheck(0, 0, N);
        System.out.println(sb);
    }

    public static boolean isDiff(int r, int c, int len) {
        int t = arr[r][c];

        for(int i = r; i < r+len; i++) {
            for(int j = c; j < c+len; j++) {
                if(t != arr[i][j]) {
                    return false;
                }
            }
        }
        M = t;
        return true;
    }

    public static void splitAndCheck(int r, int c, int len) {
        if (isDiff(r, c, len)){
            sb.append(M);
        } else{
            sb.append("(");
            int newLen = len/2;
            for(int i = 0; i < 2; i++){
                for (int j = 0; j < 2; j++){
                    splitAndCheck(r + newLen*i, c + newLen*j, newLen);
                }
            }
            sb.append(")");
        }
    }
}
