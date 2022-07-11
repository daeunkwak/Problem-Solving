/**
 author : https://github.com/daeunkwak
 title : 최대공약수
 description : 수학, 정수론, 유클리드 호제법
 date : 2022-07-11
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1850 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        long gcd = gcd(Math.max(A, B), Math.min(A, B));

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < gcd; i++){
            sb.append("1");
        }
        System.out.println(sb);
    }

    public static long gcd(long a, long b) {
        while(b != 0) {
            long r = a % b;

            a = b;
            b = r;
        }
        return a;
    }
}
