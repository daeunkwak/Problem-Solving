
import java.util.*;

/**
 * title : Angry Cows
 * date : 2025-07-24
 */
public class boj11973 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        int[] hay = new int[N];
        for (int i = 0; i < N; i++) {
            hay[i] = sc.nextInt();
        }
        Arrays.sort(hay);

        int left = 0;
        int right = ((hay[N - 1] - hay[0]) / K + 1) / 2;
        int res = right;

        while (left <= right) {
            int mid = (left + right) / 2;
            int cnt = 0;
            int cow = 0;

            while (cnt < N) {
                // 소 한마리씩 늘려가면서
                cow++;
                int range = hay[cnt] + 2 * mid;
                // 범위 내 격파할 수 있는 건초더미 count
                while (cnt < N && hay[cnt] <= range) {
                    cnt++;
                }
            }

            if (cow <= K) {
                res = mid;
                right = mid - 1;  // R을 더 줄여봐도 됨
            } else {
                left = mid + 1; // 더 늘려야함
            }
        }

        System.out.println(res);
    }
}
