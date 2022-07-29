/*
 author : https://github.com/daeunkwak/Problem-Solving
 title : 골드바흐의 추측
 description : 수학, 정수론, 소수 판정, 에라토스테네스의 체
 date : 2022-07-29
 */

package 이것저것;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_6588 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 에라토스테네스의 체 -> 소수 세팅
        boolean[] isPrime = new boolean[1000001];
        for (int i = 2; i <= 1000000; i++){
            if (! isPrime[i]){
                for(int j = i*2; j <= 1000000; j += i){
                    isPrime[j] = true;
                }
            }
        }

        while(true){
            int num = Integer.parseInt(br.readLine());
            boolean isExists = false;

            if (num == 0){
                System.out.println(sb);

                return;
            }

            for(int i = 3; i <= num; i += 2){
                if (!isPrime[i] && !isPrime[num-i]) {
                    sb.append(num + " = " + i + " + " + (num - i)).append("\n");
                    isExists = true;

                    break;
                }
            }

            if (! isExists){
                sb.append("Goldbach's conjecture is wrong.").append("\n");
            }
        }


    }
}
