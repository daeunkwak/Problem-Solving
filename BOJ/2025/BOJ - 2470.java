
import java.util.*;

/**
 * title : 두 용액
 * date : 2025-10-03
 */
public class boj2470 {
    public static void main(String[] args) {
        // 혼합한 특성값 = 각 용액의 특성값의 합
        // 혼합해서 0에 가장 가까운 용액 찾기
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] solution = new int[N];
        for (int i = 0; i < N; i++) solution[i] = sc.nextInt();

        Arrays.sort(solution);

        int left = 0;
        int right = N - 1;
        int diff = Integer.MAX_VALUE;
        int[] closeToZero = new int[2];

        while (left < right) {
            int sum = solution[left] + solution[right];

            if (Math.abs(sum) < diff) {
                closeToZero[0] = solution[left];
                closeToZero[1] = solution[right];
                diff = Math.abs(sum);
            }

            // 합이 음수면 더 큰 값 필요 → left++
            // 합이 양수면 더 작은 값 필요 → right--
            if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println(closeToZero[0] + " " + closeToZero[1]);
    }
}
