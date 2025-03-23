import java.util.*;

/**
 * title : 광물 캐기
 * date : 2025-03-21
 */
class Solution {
    public int solution(int[] picks, String[] minerals) {

        int pickCount = picks[0] + picks[1] + picks[2];
        int mineralGroups = (int) Math.ceil((double) minerals.length / 5);
        int groups = Math.min(pickCount, mineralGroups);

        int[][] sortedMinerals = new int[groups][6];

        for (int i = 0; i < groups; i++) {
            int sum = 0;
            for (int j = 0; j < 5; j++) {
                int index = i * 5 + j;  // index 계산 **
                if (index < minerals.length) {
                    if (minerals[index].equals("diamond")) {
                        sum += 25;
                        sortedMinerals[i][j + 1] = 25;
                    } else if (minerals[index].equals("iron")) {
                        sum += 5;
                        sortedMinerals[i][j + 1] = 5;
                    } else {
                        sum += 1;
                        sortedMinerals[i][j + 1] = 1;
                    }
                } else {
                    sortedMinerals[i][j + 1] = 0;
                }
            }
            // 기껏 더해놓고 까먹기
            sortedMinerals[i][0] = sum;
        }

        // 피로도 합계가 높은 그룹부터 내림차순 정렬
        Arrays.sort(sortedMinerals, (a, b) -> b[0] - a[0]);

        int tired = 0;

        for (int i = 0; i < sortedMinerals.length; i++) {
            // 다이아몬드 곡괭이 사용
            if (picks[0] > 0) {
                for (int j = 0; j < 5; j++) {
                    if (sortedMinerals[i][j + 1] != 0) {
                        tired += 1; // 다이아 곡괭이는 모두 피로도 1
                    }
                }
                picks[0]--;
            }
            // 철 곡괭이 사용
            else if (picks[1] > 0) {
                for (int j = 0; j < 5; j++) {
                    if (sortedMinerals[i][j + 1] == 25) {
                        tired += 5;
                    } else if (sortedMinerals[i][j + 1] != 0) {
                        tired += 1;
                    }
                }
                picks[1]--;
            }
            // 돌 곡괭이 사용
            else if (picks[2] > 0) {
                for (int j = 0; j < 5; j++) {
                    if (sortedMinerals[i][j + 1] == 25) {
                        tired += 25;
                    } else if (sortedMinerals[i][j + 1] == 5) {
                        tired += 5;
                    } else if (sortedMinerals[i][j + 1] == 1) {
                        tired += 1;
                    }
                }
                picks[2]--;
            }
        }

        return tired;
    }
}