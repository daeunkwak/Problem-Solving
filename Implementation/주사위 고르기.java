import java.util.*;

/**
 * title : 주사위 고르기
 * date : 2025-03-07
 */

class Solution {
    int n = 0;
    List<int[]> combinations;

    public int[] solution(int[][] dice) {
        int[] answer = null;
        n = dice.length;
        combinations = new ArrayList<>();

        // n/2개 선택 조합
        getCombinations(dice, new int[n/2], 0, 0);

        // A, B 시뮬레이션
        int maxWinCnt = 0;
        for (int[] combA : combinations) {
            int[] combB = getCombB(combA);

            List<Integer> arrA = new ArrayList<>();
            List<Integer> arrB = new ArrayList<>();

            // n/2개 주사위 던져서 나오는 합 모두 구하기
            getCalculate(dice, arrA, combA, 0, 0);
            getCalculate(dice, arrB, combB, 0, 0);

            // 이분탐색으로 A가 이기는 횟수 구하기
            // 정렬 까먹지마
            Collections.sort(arrB);

            // A가 이기는 횟수 구하ㄱ긱
            int winCnt = getWinCntOfA(arrA, arrB);
            if (maxWinCnt < winCnt) {
                maxWinCnt = winCnt;
                answer = combA;
            }

        }

        // 주사위는 1번부터 시작하까 1씩 더해야함
        Arrays.sort(answer);
        for (int i = 0; i < answer.length; i++) {
            answer[i] += 1;
        }

        return answer;
    }

    private void getCombinations(int[][] dice, int[] current, int count, int idx) {
        if (count == n/2) {
            combinations.add(current.clone());
            return;
        }

        for (int i = idx; i < n; i++) {
            current[count] = i;
            getCombinations(dice, current, count + 1, i + 1);
        }
    }

    private int[] getCombB(int[] combA) {
        int[] combB = new int[n/2];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int k : combA) {
                if (i == k) {
                    flag = true;
                }
            }
            if (!flag) {
                combB[idx] = i;
                idx++;
            }
        }

        return combB;
    }

    private void getCalculate(int[][] dice, List<Integer> arr, int[] comb, int count, int sum) {
        if (count == n/2) {
            arr.add(sum);
            return;
        }

        for (int i = 0; i < 6; i++) {
            getCalculate(dice, arr, comb, count + 1, sum + dice[comb[count]][i]);
        }
    }

    private int getWinCntOfA(List<Integer> aScores, List<Integer> bScores) {
        int winCnt = 0;
        // a를 기준으로 for문을 돌리고
        // b배열의 어디까지 이길 수 있는지를 이분탐색으로 찾으면 된다!
        for (int s : aScores) {
            int start = 0;
            // 그래서 end를 이렇게 설정해야 하는 것
            int end = bScores.size() - 1;
            while (start <= end) {
                int mid = (start + end) / 2;

                if (s > bScores.get(mid)) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
            winCnt += start;
        }
        return winCnt;
    }
}