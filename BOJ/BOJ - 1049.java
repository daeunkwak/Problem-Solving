/**
 author : Kwak Daeun
 github : https://github.com/daeunkwak
 title : 기타줄
 description : 수학, 그리디 알고리즘
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1049 {
    static int N, M;
    static int[][] arr;
    static int res = 0;
    static int res_one = 0;
    static int min_p = 1001;
    static int min_o = 1001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M][2];

        // 브랜드별 가격 받아 최솟값 설정
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            arr[i][0] = Integer.parseInt(st.nextToken());
            if (arr[i][0] < min_p){
                min_p = arr[i][0];
            }
            arr[i][1] = Integer.parseInt(st.nextToken());
            if (arr[i][1] < min_o){
                min_o = arr[i][1];
            }
        }

        res += (N / 6) * min_p;
        if ((N % 6) * min_o < min_p){
            res += (N % 6) * min_o;
        } else {
            res += min_p;
        }

        // 모두 낱개로 구매할때 추가
        res_one = N * min_o;
        System.out.println(Math.min(res, res_one));
    }
}
