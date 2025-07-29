
import java.util.*;

/**
 * title : 건너 아는 사이
 * date : 2025-07-26
 */
public class boj25921 {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int N = sc.nextInt();

            long sum = 2; // 1과 2 연결
            boolean[] isPrime = new boolean[N + 1];
            Arrays.fill(isPrime, true);
            isPrime[0] = isPrime[1] = false;

            for (int i = 2; i <= N; i++) {
                if (isPrime[i]) {
                    // i의 배수들을 처리
                    for (int j = i * 2; j <= N; j += i) {
                        if (isPrime[j]) {
                            sum += i;
                            isPrime[j] = false;
                        }
                    }

                    // 소수 i는 (i-1)과 연결
                    if (i > 2 && i <= N) { // 2는 제외 (이미 1과 연결)
                        sum += i;
                    }
                }
            }

            System.out.println(sum);
        }
}
