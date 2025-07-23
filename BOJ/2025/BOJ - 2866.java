
import java.util.*;

/**
 * title : 문자열 잘라내기
 * date : 2025-07-23
 */
public class boj2866 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int R = sc.nextInt();
        int C = sc.nextInt();
        sc.nextLine();

        String[] words = new String[R];
        for (int i = 0; i < R; i++) {
            words[i] = sc.nextLine();
        }

        // 한 번 중복되면 영원히 중복되므로 이분탐색 가능
        int left = 0;
        int right = R - 1;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canRemove(words, R, C, mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean canRemove(String[] words, int R, int C, int removeCount) {
        Set<String> set = new HashSet<>();

        for (int j = 0; j < C; j++) {
            StringBuilder sb = new StringBuilder();
            for (int k = removeCount; k < R; k++) {
                sb.append(words[k].charAt(j));
            }
            String columnString = sb.toString();

            if (set.contains(columnString)) {
                return false;
            }
            set.add(columnString);
        }

        return set.size() == C;
    }
}