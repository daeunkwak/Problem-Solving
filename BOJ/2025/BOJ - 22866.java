
import java.util.*;

/**
 * title : 탑 보기
 * date : 2025-08-03
 */
public class boj22866 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        // idx == 건물 번호
        int[] buildings = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            buildings[i] = sc.nextInt();
        }

        // ***** map.values()는 순서 보장이 안되니까 이럴 때 쓰면 안 됨
        // HashMap<Integer, int[]> map = new HashMap<>();
        int[] count = new int[N + 1];
        int[] nearest = new int[N + 1];
        Arrays.fill(nearest, -1);

        // {건물 번호, 건물 높이}
        Stack<int[]> stack = new Stack<>();

        // 왼쪽 방향 바라볼 때
        for (int i = 1; i <= N; i++) {
            // 못보는 건물들 제거
            // ** 같아도 안 됨
            while (!stack.isEmpty() && stack.peek()[1] <= buildings[i]) {
                stack.pop();
            }

            count[i] = stack.size();

            if (!stack.isEmpty()) {
                nearest[i] = stack.peek()[0];
            }

            stack.push(new int[]{i, buildings[i]});
        }

        // 오른쪽 방향 바라볼 때
        stack.clear();
        for (int i = N; i >= 1; i--) {
            while (!stack.isEmpty() && stack.peek()[1] <= buildings[i]) {
                stack.pop();
            }

            count[i] += stack.size();

            if (!stack.isEmpty()) {
                int rightNearest = stack.peek()[0];
                if (nearest[i] == -1) {
                    nearest[i] = rightNearest;
                } else {
                    // 거리 비교
                    int leftDist = i - nearest[i];
                    int rightDist = rightNearest - i;

                    if (rightDist < leftDist) {
                        nearest[i] = rightNearest;
                    } else if (rightDist == leftDist && rightNearest < nearest[i]) {
                        nearest[i] = rightNearest;
                    }
                }
            }

            stack.push(new int[]{i, buildings[i]});
        }

        for (int i = 1; i <= N; i++) {
            if (count[i] == 0) {
                System.out.println(0);
            } else {
                System.out.println(count[i] + " " + nearest[i]);
            }
        }
    }
}
