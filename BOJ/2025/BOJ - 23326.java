package just.p_0922;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * title : 홍익 투어리스트
 * date : 2025-09-22
 */
public class boj23326 {
    public static void main(String[] args) {
        // 1 : 명소 지정 on/off
        // 2 : 이동
        // 3 : 가장 가까운 명소 출력
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int Q = sc.nextInt();

        boolean[] isPlace = new boolean[N + 1];
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 1; i <= N; i++) {
            int bol = sc.nextInt();
            if (bol == 1) {
                isPlace[i] = true;
                set.add(i);
            } else {
                isPlace[i] = false;
            }
        }

        int location = 1;
        for (int i = 0; i < Q; i++) {
            int cur = sc.nextInt();
            switch (cur) {
                case 1 :
                    int idx = sc.nextInt();
                    if (isPlace[idx]) {
                        isPlace[idx] = false;
                        set.remove(idx);
                    } else {
                        isPlace[idx] = true;
                        set.add(idx);
                    }
                    break;
                case 2 :
                    int mv = sc.nextInt();
                    // *** 인덱스 계산 ****
                    location = ((location - 1 + mv % N) % N) + 1;
                    break;
                case 3 :
                    if (!set.isEmpty()) {
                        System.out.println(findPlace(location, set, N));
                    } else {
                        System.out.println(-1);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private static int findPlace(int location, TreeSet<Integer> set, int N) {
        Integer nextSpot = set.ceiling(location);

        if (nextSpot == null) {
            nextSpot = set.first();
            return (N - location + nextSpot);
        }

        return (nextSpot - location);
    }
}
