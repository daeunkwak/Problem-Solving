/*
 author : https://github.com/daeunkwak/Problem-Solving
 title : 진법 변환
 description : 수학, 구현, 문자열
 date : 2022-07-17
 */

package 이것저것;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2745 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String N = st.nextToken();
        int B = Integer.parseInt(st.nextToken());

        int res = 0;
        for(int i = 0; i < N.length(); i++){
            if ('0' <= N.charAt(i) && N.charAt(i) <= '9'){
                res = res * B + ((int)N.charAt(i) - (int)'0');
            }
            else{
                res = res * B + ((int)N.charAt(i) - (int)'A' + 10);
            }
        }
        System.out.println(res);
    }
}
