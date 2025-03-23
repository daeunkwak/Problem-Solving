import java.util.*;

/**
 * title : 프로그래머스 - 코딩 테스트 공부
 * date : 2025-03-19
 */
class Solution {
    public int solution(int alp, int cop, int[][] problems) {

        int maxAlp = 0;
        int maxCop = 0;
        for (int i = 0; i < problems.length; i++) {
            maxAlp = Math.max(maxAlp, problems[i][0]);
            maxCop = Math.max(maxCop, problems[i][1]);
        }

        if (maxAlp < alp) {
            alp = maxAlp;
        }

        // 오타..
        if (maxCop < cop) {
            cop = maxCop;
        }

        int [][] dp = new int[maxAlp + 1][maxCop + 1];

        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dp[alp][cop] = 0;   // 시작 지점까지 걸리는 시간 0

        for (int i = alp; i < maxAlp + 1; i++) {
            for (int j = cop; j < maxCop + 1; j++) {
                if (dp[i][j] == Integer.MAX_VALUE) {
                    continue;
                }

                // 1시간씩 공부하기
                if (i < maxAlp) {
                    dp[i + 1][j] = Math.min(dp[i+1][j], dp[i][j] + 1);
                }
                if (j < maxCop) {
                    dp[i][j + 1] = Math.min(dp[i][j+1], dp[i][j] + 1);
                }


                // 문제 풀기
                for (int p = 0; p < problems.length; p++) {
                    int needAlp = problems[p][0];
                    int needCop = problems[p][1];
                    int addAlp = problems[p][2];
                    int addCop = problems[p][3];
                    int cost = problems[p][4];

                    if (needAlp <= i && needCop <= j) {
                        int afterAlp = Math.min(i + addAlp, maxAlp);
                        int afterCop = Math.min(j + addCop, maxCop);
                        dp[afterAlp][afterCop] = Math.min(dp[afterAlp][afterCop], dp[i][j] + cost);
                    }

                }
            }
        }

        // 정신차려라진짜;
        //int answer = dp[maxAlp + 1][maxCop + 1];
        int answer = dp[maxAlp][maxCop];

        return answer;
    }
}