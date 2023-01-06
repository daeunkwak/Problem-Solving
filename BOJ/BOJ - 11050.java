/**
 * author : https://github.com/daeunkwak
 * date : 2022-01-06
 * title : 이항 계수 1
 * description : 수학, 구현, 조합론
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11050 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // N! / R! (N-R)!
        System.out.println(factorial(N) / (factorial(K) * factorial(N - K)));

    }

    protected static int factorial(int N){
        int temp = 1;
        for(int i = 1; i <= N; i++){
            temp = temp * i;
        }
        return temp;
    }
}
