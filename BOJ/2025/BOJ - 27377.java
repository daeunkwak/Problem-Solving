package just.p_0909;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * title : 읽씹 멈춰!
 * date : 2025-09-16
 */
public class boj27377 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            long n = Long.parseLong(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            long s = Long.parseLong(st.nextToken());  // 적기
            long t = Long.parseLong(st.nextToken());  // 복붙하기

            long time = 0;
            long cnt = n;

            // 0부터 시작하는게 아니라 n부터 나누면서 풀면 됨
            // 짝수인 경우에 적기 or 복붙하기 비교해주고
            // 홀수인 경우엔 무조건 적기가 필요하므로 적어주기
            while (cnt > 0) {
                if (cnt % 2 == 0) {
                    if (cnt / 2 * s > 0) {
                        time += Math.min(cnt / (long)2 * s, t);
                    } else {
                        time += t;
                    }
                    cnt /= 2;
                } else {
                    time += s;
                    cnt -= 1;
                }
            }
            System.out.println(time);


            // 이렇게 풀면 예외케이스가 너무 많음
//            while (cnt < n && cnt * s < t) {
//                time += s;
//                cnt += 1;
//            }
//
//            while (cnt < n) {
//                time += t;
//                cnt *= 2;
//            }
//
//            if ((n - cnt) * s < t) {
//                time += (n - cnt) * s;
//            } else {
//                time += t;
//            }
        }
    }
}
