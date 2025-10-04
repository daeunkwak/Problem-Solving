import java.util.*;

/**
 * title : 파괴되지 않은 건물
 * date : 2025-10-03
 */
class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;

        // 공격, 회복이 모두 끝난 뒤 파괴되지 않은 건물 개수 return
        // skills [type, r1, c1, r2, c2, degree]
        // type 1 : 공격, 2 : 힐링

        int[][] sumBoard = new int[board.length + 1][board[0].length + 1];
        for (int i = 0; i < skill.length; i++) {
            // *** skill에서 꺼내야되는데 board 쓰고 있었음..
            int type = skill[i][0];
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = skill[i][5];

            // 누적합 활용
            if (type == 1) {    // 공격
                sumBoard[r1][c1] -= degree;
                if (c2 + 1 < board[0].length) sumBoard[r1][c2 + 1] += degree;
                if (r2 + 1 < board.length) sumBoard[r2 + 1][c1] += degree;
                if (r2 + 1 < board.length && c2 + 1 < board[0].length) sumBoard[r2 + 1][c2 + 1] -= degree;
            } else {    // 힐링
                sumBoard[r1][c1] += degree;
                if (c2 + 1 < board[0].length) sumBoard[r1][c2 + 1] -= degree;
                if (r2 + 1 < board.length) sumBoard[r2 + 1][c1] -= degree;
                if (r2 + 1 < board.length && c2 + 1 < board[0].length) sumBoard[r2 + 1][c2 + 1] += degree;
            }
        }

        // for (int i = 0; i < board.length; i++) {
        //     StringBuilder sb = new StringBuilder();
        //     for (int j = 0; j < board[0].length; j++) {
        //         sb.append(sumBoard[i][j]);
        //         sb.append(" ");
        //     }
        //     System.out.println(sb.toString());
        // }

        // 누적합 계산
        for (int i = 0; i < board.length; i++) {
            int sum = 0;
            for (int j = 0; j < board[0].length; j++) {
                sumBoard[i][j] += sum;
                sum = sumBoard[i][j];
            }
        }

        int cnt = 0;
        for (int j = 0; j < board[0].length; j++) {
            int sum = 0;
            for (int i = 0; i < board.length; i++) {
                sumBoard[i][j] += sum;
                sum = sumBoard[i][j];
                // ******** 인덱스 실수 제발 제발
                if (sumBoard[i][j] + board[i][j] > 0) cnt++;
            }
        }

        return cnt;
    }
}