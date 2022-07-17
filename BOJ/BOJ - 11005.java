/*
 author : https://github.com/daeunkwak/Problem-Solving
 title : 진법 변환2
 description : 수학, 구현
 date : 2022-07-17
 */

package 이것저것;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11005 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        int rest = 0;
        while(N > 0){
            rest = N % B;
            if (rest >= 10){
                sb.append((char) (rest - 10 + (int) 'A'));
            } else {
                sb.append((char) (rest + (int) '0'));
            }
            N = N / B;
        }
        System.out.println(sb.reverse());
    }
}
