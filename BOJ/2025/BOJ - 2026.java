package just.p_0822;

import java.util.*;

/**
 * title : 소풍
 * date : 2025-08-22
 */
public class boj2026 {
    static List<Integer> result = new ArrayList<>();
    public static void main(String[] args) {
        // 친구관계 정보 F -> K명의 친구 선발
        // union-find XXX

        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        int N = sc.nextInt();
        int F = sc.nextInt();

        boolean[][] friends = new boolean[N + 1][N + 1];
        for (int i = 0; i < F; i++) {
            int f1 = sc.nextInt();
            int f2 = sc.nextInt();
            friends[f1][f2] = true;
            friends[f2][f1] = true;
        }

        if (dfs(1, new ArrayList<>(), friends, N, K)) {
            for (int student : result) {
                System.out.println(student);
            }
        } else {
            System.out.println(-1);
        }

    }

    static boolean dfs(int start, List<Integer> selected, boolean[][] friends, int N, int K) {
        if (selected.size() == K) {
            result = new ArrayList<>(selected);
            return true;
        }

        for (int i = start; i <= N; i++) {
            if (canAdd(i, selected, friends)) {
                selected.add(i);
                if (dfs(i + 1, selected, friends, N, K)) {
                    return true;
                }
                selected.remove(selected.size() - 1);
            }
        }

        return false;
    }

    // 기존 모든 친구들과 친구인지 확인
    static boolean canAdd(int student, List<Integer> selected, boolean[][] friends) {
        for (int friend : selected) {
            if (!friends[student][friend]) {
                return false;
            }
        }
        return true;
    }
}
