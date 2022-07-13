/*
 author : https://github.com/daeunkwak/Problem-Solving
 title : 1로 만들기
 description : 다이나믹 프로그래밍
 date : 2022-07-13
 */

package DP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1463 {

    // 재귀함수 <-> 전역변수로 리턴값 관리
    static int res = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        recur(N, 0);
        System.out.println(res);
    }

    static void recur(int num, int cnt){
        // else if문을 쓰면 안됨 !!!!
        if (num == 1){
            res = Math.min(res, cnt);
        }
        if(cnt >= res){
            return;
        }
        if(num % 3 == 0){
            recur(num / 3, cnt + 1);
        }
        if(num % 2 == 0){
            recur(num / 2, cnt + 1);
        }
        recur(num - 1, cnt + 1);
    }
}
