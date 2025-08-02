package just.p_0730;

import java.util.*;

/**
 * title : 가희와 탑
 * date : 2025-08-02
 */
public class boj24337 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();

        List<Integer> list = new LinkedList<>();

        if (a + b - 1 > N) {
            System.out.println(-1);
            return;
        }

        // 가희부터
        for (int i=1; i <= a-1; i++)
            list.add(i);

        // 가장 높은 건물 높이 정하고
        list.add(Math.max(a, b));

        // 단비
        for (int i=b-1; i >= 1; i--)
            list.add(i);

        // ****** 남은건 전부 맨앞에 1로 추가
        for (int i=0; i < N - (a + b - 1); i++)
            list.add(1, 1);

        StringBuilder sb = new StringBuilder();
        for (int n: list)
            sb.append(n).append(' ');

        System.out.println(sb.toString());
    }
}
