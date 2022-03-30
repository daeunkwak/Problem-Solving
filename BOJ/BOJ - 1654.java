import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * author : Kwak Daeun
 * github : https://github.com/daeunkwak
 *
 * title : 랜선 자르기
 * description : 이분 탐색, 매개 변수 탐색
 */

import java.util.Scanner;

public class BOJ_1654 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        // 입력 범위가 int의 최대값까지
        // 이분 탐색에 필요한 모든 변수들은 long타입으로 잡기
        long N = sc.nextInt();

        // 배열 생성, 원소 채우기
        long [] arr = new long[K];
        long maxx = 0;
        for (int i = 0; i < K; i++) {
            arr[i] = sc.nextInt();
            maxx = Math.max(maxx, arr[i]);
        }

        // 이분탐색
        long left = 1;
        long right = maxx;
        // int mid = (left + right) / 2;

        while (left <= right) {
            long mid = (left + right) / 2;
            long cnt = 0;
            for (int i = 0; i < K; i++) {
                cnt += arr[i] / mid;
                // System.out.println("cnt" + cnt);
            }
            if (cnt >= N) {
                // 더 길게 자를 수 있음
                left = mid + 1;
            } else{
                // 더 짧아야함
                right = mid - 1;
            }
        }
        // System.out.println("res : " + res);
        // System.out.println("left : " + left);
        System.out.println(right);
    }
}














