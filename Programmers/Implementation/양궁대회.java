import java.util.*;

/**
 * title : 양궁대회
 * date : 2025-10-03
 */
class Solution {
    static List<List<Integer>> combs;
    public int[] solution(int n, int[] info) {
        combs = new ArrayList<>();

        // info는 10점부터 0점까지

        // 라이언에게 불리하게
        // 1. 어피치 n발 > 라이언 n발
        // 2. k점을 어피치가 a발, 라이언이 b발 > 더 많은 화살을 k점에 맞힌 선수가 k점을 가져감
        // a == b인 경우 어피치가 k점
        // 3. 최종 점수 계산, 같으면 어피치가 우승
        // 어피치가 n발을 쏨 > 라이언이 어피치를 가장 큰 점수차로 이기기 위해 어떻게 쏠지 구하기
        // 어떻게 해도 못이기면 [-1]

        // 그리디? dp?
        // 완탐해도 충분

        // [1] 라이언이 점수를 딸 점수 조합을 모두 구하기 2^10 = 1024
        getCombs(1, new ArrayList<>());
        System.out.println("comb size : " + combs.size());

        // [2] 고른 점수를 획득하는 경우 최대 점수차 계산 > 갱신, 불가능하면 pass
        int maxScoreDiff = -1;
        int[] maxLionScores = new int[]{-1};
        int cnt = 0;
        for (List<Integer> comb : combs) {
            int arrowLeft = n;
            // 작은 점수부터 어피치를 이기기 위해 화살 소진
            boolean available = true;
            int[] lion = new int[11];

            for (int i = 9; i >= 0; i--) {  // 점수는 10 - i, idx는 i
                if (comb.contains(10 - i)) {
                    int apeach = info[i];

                    if (arrowLeft < apeach + 1) {
                        available = false;
                        cnt++;
                        break;  // 화살 다써서 불가능
                    }

                    lion[i] = apeach + 1;   // 점수 기록, 화살 소진
                    arrowLeft -= apeach + 1;
                }
            }

            if (available && arrowLeft > 0) {
                lion[10] += arrowLeft;
            }

            // 가능한 조합인 경우 점수차 계산
            if (available) {
                int lionScore = 0;
                int apeachScore = 0;
                for (int i = 9; i >= 0; i--) {
                    if (lion[i] > info[i]) lionScore += (10 - i);
                    else if (lion[i] < info[i]) apeachScore += (10 - i);
                    else {
                        if (lion[i] != 0) apeachScore += (10 - i);
                    }
                }

                if (lionScore > apeachScore) {
                    int scoreDiff = lionScore - apeachScore;
                    if (maxScoreDiff < scoreDiff) {
                        maxScoreDiff = scoreDiff;
                        maxLionScores = lion;
                    }
                }
            }
        }

        return (maxScoreDiff == -1) ? new int[]{-1} : maxLionScores;
    }

    private static void getCombs(int n, List<Integer> cur) {
        if (n == 11) {
            List<Integer> clone = new ArrayList<>();
            for (int i : cur) clone.add(i);
            combs.add(clone);
            return;
        }

        cur.add(n);
        getCombs(n + 1, cur);
        cur.remove(cur.size() - 1);
        getCombs(n + 1, cur);
    }

}