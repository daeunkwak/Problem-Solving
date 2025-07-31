
import java.util.*;

/**
 * title : Three Dots
 * date : 2025-07-30
 */
public class boj13423 {
    private static List<List<Integer>> combs;
    private static int cnt;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int t = 0; t < T; t++) {
            combs = new ArrayList<>();
            cnt = 0;
            int N = sc.nextInt();

            int[] points = new int[N];
            Set<Integer> pointSet = new HashSet<>();
            for (int i = 0; i < N; i++) {
                points[i] = sc.nextInt();
                pointSet.add(points[i]);
            }

            Arrays.sort(points);

            // 중간점을 기준으로 비교
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == j) continue;

                    int mid = points[i];
                    int other = points[j];
                    int last = 0;

                    if (mid < other) {
                        last = mid - (other - mid);
                    } else {
                        last = mid + (mid - other);
                    }

                    // 안겹치면 조건 만족
                    if (pointSet.contains(last) && last != mid && last != other) {
                        cnt++;
                    }
                }
            }


            System.out.println(cnt / 2);
        }

    }


}
