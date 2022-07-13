/**
 author : https://github.com/daeunkwak/Problem-Solving
 title : GCD 합
 description : 수학, 브루트포스 알고리즘 정수론, 유클리드 호제법
 date : 2022-07-12
 comment : 틀렸을 땐 자료형 확인 long/int
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_9613 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for(int i = 0; i < t; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int arr[] = new int[N];
            for(int j = 0; j < N; j++){
                arr[j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr);
            long res = 0;
            for(int m = 0; m < N-1; m++){
                for(int n = m+1; n < N; n++){
                    //System.out.println(arr[m] + " " + arr[n]);
                    res += gcd(arr[n], arr[m]);
                }
            }
            System.out.println(res);

        }
    }

    public static int gcd(int a, int b) {
        while(b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
