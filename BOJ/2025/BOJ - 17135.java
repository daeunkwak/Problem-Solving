package just;

import java.util.*;
import java.util.stream.Collectors;

/**
 * title : 캐슬 디펜스
 * date : 2025-07-16
 */
public class BOJ_17135 {
    static List<int[]> combs = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력: N, M, D
        int N = sc.nextInt(); // 행
        int M = sc.nextInt(); // 열
        int D = sc.nextInt(); // 공격 거리 제한

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        // 궁수 위치 조합 구하기 (M C 3)
        int[] arr = new int[M];
        for (int i = 0; i < M; i++) {
            arr[i] = i;
        }
        combination(arr, new int[3], 0, 0);

        // 초기 적 위치 저장
        List<int[]> enemies = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    enemies.add(new int[]{i, j});
                }
            }
        }

        int max = 0;

        // 각 궁수 조합에 대해 시뮬레이션
        for (int[] comb : combs) {
            int removed = 0;

            // 적 정보 복사
            List<int[]> enClone = new ArrayList<>();
            for (int[] e : enemies) {
                enClone.add(e.clone());
            }

            // 최대 N번 반복 (적이 성에 도달하기 전까지)
            for (int turn = 0; turn < N; turn++) {
                if (enClone.isEmpty()) break;

                Set<Integer> removedIdx = new HashSet<>();

                // 각 궁수마다 타겟 선정
                for (int c : comb) {
                    int minDist = Integer.MAX_VALUE;
                    int targetIdx = -1;

                    for (int i = 0; i < enClone.size(); i++) {
                        int[] enemy = enClone.get(i);
                        int dist = Math.abs(N - enemy[0]) + Math.abs(c - enemy[1]);

                        if (dist <= D) {
                            if (dist < minDist || (dist == minDist && enemy[1] < enClone.get(targetIdx)[1])) {
                                minDist = dist;
                                targetIdx = i;
                            }
                        }
                    }

                    if (targetIdx != -1) {
                        removedIdx.add(targetIdx);
                    }
                }

                // 적 제거 (내림차순)
                // List<Integer> sortedIdx = removedIdx.stream().sorted((a, b) -> b - a).toList();
                List<Integer> sortedIdx = removedIdx.stream()
                        .sorted((a, b) -> b - a)
                        .collect(Collectors.toList());
                for (int idx : sortedIdx) {
                    removed++;
                    enClone.remove((int) idx);
                }

                // 적 한 칸 아래로 이동
                List<int[]> afterMove = new ArrayList<>();
                for (int[] enemy : enClone) {
                    if (enemy[0] + 1 < N) {
                        afterMove.add(new int[]{enemy[0] + 1, enemy[1]});
                    }
                }
                enClone = afterMove;
            }

            max = Math.max(max, removed);
        }

        System.out.println(max);
    }

    // 궁수 위치 조합 구하기
    private static void combination(int[] arr, int[] output, int start, int depth) {
        if (depth == output.length) {
            combs.add(output.clone()); // clone 필수!
            return;
        }

        for (int i = start; i < arr.length; i++) {
            output[depth] = arr[i];
            combination(arr, output, i + 1, depth + 1);
        }
    }
}
