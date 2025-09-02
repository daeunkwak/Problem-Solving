package just.p_0901;

import java.util.*;

/**
 * title : PPC 만들기
 * date : 2025-09-01
 */
public class boj31778 {
    public static void main(String[] args) {
        // 문자열 S <- 길이 N
        // 연산 최대 K번 가능 : Si, Sj 바꾸기
        // PPC 부분문자열이 가장 많도록 바꾸기

        // 최대한 앞의 C를 최대한 뒤에있는 P랑 바꾸기
        // CCPCPPCP > PCPCPPCC > PPPCPCCC > 3 + 6 + 6 + 6 = 21

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        String S = sc.next();

        char[] a = S.toCharArray();
        int start = 0;
        int end = N - 1;
        for (int i = 0; i < K; i++) {
            while (start < end && a[start] != 'C') start++;
            while (start < end && a[end] != 'P') end--;

            if (start >= end) break;

            char tmp = a[start];
            a[start] = a[end];
            a[end] = tmp;
        }
        S = new String(a);

        long cnt = 0;
        long cntP = 0;
        for (int i = 0; i < N; i++) {
            if (S.charAt(i) == 'P') {
                cntP++;
            } else {
                if (cntP >= 2) {
                    cnt += (cntP * (cntP - 1) / 2);
                }
            }
        }

        System.out.println(cnt);
    }
}
