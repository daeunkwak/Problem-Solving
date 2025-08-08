package just.p_0807;

import java.util.*;

/**
 * title : 뱀과 사다리 게임
 * date : 2025-08-07
 */
public class boj16928 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        Map<Integer, Integer> ladders = new HashMap<>();
        Map<Integer, Integer> snakes = new HashMap<>();

        for (int i = 0; i < N; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            ladders.put(start, end);
        }

        for (int i = 0; i < M; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            snakes.put(start, end);
        }

        // [현재 위치, 주사위 굴린 횟수]
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{1, 0});

        boolean[] visited = new boolean[101];
        visited[1] = true;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curNum = cur[0];
            int curDiceCnt = cur[1];
            // System.out.println("num : " + curNum + " cnt : " + curDiceCnt);

            if (curNum == 100) {
                System.out.println(curDiceCnt);
                return;
            }

            if (curNum > 100) {
                continue;
            }

            for (int i = 1; i <= 6; i++) {
                int nextNum = curNum + i;

                if (ladders.containsKey(nextNum)) {
                    nextNum = ladders.get(nextNum);
                } else if (snakes.containsKey(nextNum)) {
                    nextNum = snakes.get(nextNum);
                }

                if (nextNum <= 100 && !visited[nextNum]) {
                    queue.add(new int[]{nextNum, curDiceCnt + 1});
                    visited[nextNum] = true;
                }
            }
        }
    }
}
