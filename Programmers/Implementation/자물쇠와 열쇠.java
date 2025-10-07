import java.util.*;

/**
 * title : 자물쇠와 열쇠
 * date : 2025-10-07
 */
class Solution {
    static int[][][] rotated;
    public boolean solution(int[][] key, int[][] lock) {
        // 정사각형 자물쇠, 정사각형 열쇠
        // 열쇠 회전 가능, 홈 부분에 맞추어야함

        // 완탐
        // (17 x 17) x 4 x 9
        // 0 홈, 1 돌기

        // 홈 수 세기
        int cnt = 0;
        for (int i = 0; i < lock.length; i++) {
            for (int j = 0; j < lock.length; j++) {
                if (lock[i][j] == 0) cnt++;
            }
        }

        rotated = new int[4][key.length][key.length];
        makeRotated(key);

        for (int i = key.length * -1 + 1; i < lock.length; i++) {
            for (int j = key.length * -1 + 1; j < lock.length; j++) {
                // [i][j]에서 시작
                for (int d = 0; d < 4; d++) {   // 90도씩 회전

                    boolean flag = true;
                    int done = 0;
                    for (int k = 0; k < key.length; k++) {
                        for (int l = 0; l < key.length; l++) {

                            // *** 일부만 겹치는 경우
                            if (i + k < 0 || j + l < 0) continue;
                            if (i + k >= lock.length || j + l >= lock.length) continue;    // *** lock을 봐야되는데 key로함

                            // *** 확인을 이렇게만 하면 안 됨.. 모든 홈이 채워졌는지 봐야함
                            if (rotated[d][k][l] == lock[i + k][j + l]) {
                                flag = false;
                                break;
                            }

                            if (lock[i + k][j + l] == 0) done++;
                        }
                        if (!flag) break;
                    }

                    // 모든 홈이 채워졌는지 확인
                    if (flag && done == cnt) return true;
                }
            }
        }

        return false;
    }

    // *** 맞왜틀 - 이런 회전 로직부터 검토
    private static void makeRotated(int[][] key) {
        int size = key.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                rotated[0][i][j] = key[i][j];
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // rotated[1][size - 1 - j][i] = key[i][j]; 여기 틀림
                rotated[1][j][size - 1 - i] = key[i][j];
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                rotated[2][size - 1 - i][size - 1 - j] = key[i][j];
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                rotated[3][size - 1 - j][i] = key[i][j];
            }
        }
    }

}