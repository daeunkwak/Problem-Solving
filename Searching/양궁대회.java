import java.util.*;

/**
 * title : 프로그래머스 - 양궁대회
 * date : 2025-03-21
 */
class Solution {
    int[] lion;
    int[] apeach;
    int[] answer;
    int maxDiff = -1; // -1로 설정해서 답이 없는 경우를 구별해야 함

    public int[] solution(int n, int[] info) {
        lion = new int[11];
        apeach = info;
        answer = new int[1];
        answer[0] = -1;

        dfs(0, n);

        return answer;
    }

    private void dfs(int index, int arrowsLeft) {

        // 조건을 or로 걸어서
        if(index == 11 || arrowsLeft == 0) {
            // *** 남은 화살이 있다면 모두 0점에 쏘는 경우 처리
            if(arrowsLeft > 0) {
                lion[10] += arrowsLeft;
            }

            int apeachScore = 0;
            int lionScore = 0;

            for(int i = 0; i < 11; i++) {
                if(apeach[i] == 0 && lion[i] == 0) continue; // 둘 다 못 맞힌 경우

                if(apeach[i] >= lion[i]) {
                    apeachScore += 10 - i;
                } else {
                    lionScore += 10 - i;
                }
            }

            int diff = lionScore - apeachScore;
            if(diff > 0 && diff >= maxDiff) {
                if(diff > maxDiff || isLowerScoreBetter(lion, answer)) {
                    maxDiff = diff;
                    answer = lion.clone();
                }
            }

            if(arrowsLeft > 0) {
                lion[10] -= arrowsLeft;
            }

            return;
        }

        // 현재 과녁에 화살을 쏘는 경우 (어피치보다 1발 더 쏨)
        if(arrowsLeft > apeach[index]) {
            lion[index] = apeach[index] + 1;
            dfs(index + 1, arrowsLeft - lion[index]);
            lion[index] = 0; // 백트래킹
        }

        // 현재 과녁에 화살을 쏘지 않는 경우
        lion[index] = 0;
        dfs(index + 1, arrowsLeft);
    }

    // 점수가 같음 > 낮은점수 많이 맞춘 경우 식별
    private boolean isLowerScoreBetter(int[] lion, int[] answer) {
        if(answer.length == 1 && answer[0] == -1) return true;

        for(int i = 10; i >= 0; i--) {
            if(lion[i] > answer[i]) return true;
            if(lion[i] < answer[i]) return false;
        }

        return false;
    }
}