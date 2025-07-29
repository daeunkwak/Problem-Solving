
import java.util.*;

/**
 * title : 팀 빌딩
 * date : 2025-07-28
 */
public class boj22945 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] dev = new int[N];
        for (int i = 0; i < N; i++) {
            dev[i] = sc.nextInt();
        }

        int max = Integer.MIN_VALUE;
        int start = 0;
        int end = N - 1;
        while (start < end) {
            int power = Math.min(dev[start], dev[end]) * (end - start - 1);
            max = Math.max(power, max);
            if (dev[start] < dev[end]) {
                start++;
            } else {
                end--;
            }
        }

        System.out.println(max);
    }
}
