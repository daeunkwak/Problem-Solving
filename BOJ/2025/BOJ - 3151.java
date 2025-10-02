
import java.util.*;

/**
 * title : 합이 0
 * date : 2025-10-02
 */
public class boj3151 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] coding = new int[N];
        for (int i = 0; i < N; i++) coding[i] = sc.nextInt();

        // 3명 합이 0이 되는 팀 최대 몇팀?
        Arrays.sort(coding);
        long cnt = 0;

        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                int sum = coding[i] + coding[j];

                // 이분탐색
                int target = sum * -1;
                int left = lowerBound(coding, j + 1, N, target);
                int right = upperBound(coding, j + 1, N, target);
                cnt += (right - left);
            }
        }
        System.out.println(cnt);
    }

    static int lowerBound(int[] arr, int start, int end, int target) {
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    static int upperBound(int[] arr, int start, int end, int target) {
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] > target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

}
