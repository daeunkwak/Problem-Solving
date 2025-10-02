import java.util.*;

/**
 * title : 주사위 고르기
 * date : 2025-10-01
 */
class Solution {
    static int n;
    static List<int[]> diceCombs;
    static List<Integer> sumA;
    static List<Integer> sumB;
    public int[] solution(int[][] dice) {
        n = dice.length;
        diceCombs = new ArrayList<>();  // 주사위 조합 저장

        // A, B 주사위 n/2개씩 > 주사위 모두 굴리기 > 나온 수 모두 합해 점수 계산
        // A가 이길 확률이 가장 높은 주사위 조합 오름차순으로 return

        // [1] 주사위 n/2개 선택 조합 구하기 - A, B 각각 저장
        // for (int i = 1; i <= n; i++) {
        //     int[] comb = new int[n / 2];
        //     combDice(i, 0, comb);
        // }
        int[] comb = new int[n / 2];
        combDice(1, 0, comb);
        // System.out.println("Total combinations: " + diceCombs.size());

        // [2] 직접 굴려보면서 A의 합이 더 큰 경우 count > 조합마다 값 갱신하기
        int maxWinCnt = 0;
        int[] maxWinComb = new int[n / 2];
        for (int[] diceA : diceCombs) {
            int[] diceB = new int[n / 2];

            int idxA = 0;
            int idxB = 0;

            // 남은 주사위로 B 채우기
            for (int i = 1; i <= n; i++) {
                // **** 인덱스 초과조건 확인
                if (idxA < n / 2 && diceA[idxA] == i) {
                    idxA++;
                }
                else {
                    diceB[idxB] = i;
                    idxB++;
                }
            }

            // A, B 각각 주사위 굴려보기
            sumA = new ArrayList<>();
            sumB = new ArrayList<>();
            rollDice(dice, diceA, 0, 0, sumA);
            rollDice(dice, diceB, 0, 0, sumB);

            // 이분탐색 > A가 이기는 횟수 count
            Collections.sort(sumA);
            Collections.sort(sumB);

            int winCnt = 0;
            for (int sum : sumA) {
                winCnt += binarySearch(sum);
            }

            // 최대 조합 갱신
            if (maxWinCnt < winCnt) {
                maxWinCnt = winCnt;
                maxWinComb = diceA;
            }
        }

        return maxWinComb;
    }

    public int binarySearch(int sum) {
        int start = 0;
        //int end = sumB.size() - 1;
        int end = sumB.size();

        while (start < end) {
            int mid = (start + end) / 2;

            if (sumB.get(mid) < sum) {  // 더 이길 수 있음
                start = mid + 1;
            } else {    // mid를 더 앞으로 탐색해야함
                // end = mid - 1;
                end = mid;
            }
        }

        return start;
    }

    public void rollDice(int[][] dice, int[] comb, int diceIdx, int sum, List<Integer> list) {
        if (diceIdx == n / 2) {
            list.add(sum);
            return;
        }

        for (int i = 0; i < 6; i++) {
            rollDice(dice, comb, diceIdx + 1, sum + dice[comb[diceIdx] - 1][i], list);
        }
    }

    public void combDice(int dice, int size, int[] cur) {
        // if (dice > n) return;

        if (size == n / 2) {
            diceCombs.add(cur.clone());
            return;
        }

        if (dice > n) return;

        cur[size] = dice;
        combDice(dice + 1, size + 1, cur);
        combDice(dice + 1, size, cur);
    }
}