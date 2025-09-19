import java.util.*;

/**
 * title : 크레인 인형뽑기 게임
 * date : 2025-09-19
 */
class Solution {
    public int solution(int[][] board, int[] moves) {

        // 터트려져 사라진 인형 개수 반환하기
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int[] cnt = new int[board.length + 1];  // 집으면 cnt++;

        int answer = 0;
        for (int i = 0; i < moves.length; i++) {
            int cur = moves[i] - 1;   // 인덱스 기준
            int idx = cnt[moves[i]];

            // 해당 열에서 인형이 있는 위치까지 내려가기
            while (idx < board.length && board[idx][cur] == 0) {
                idx++;
            }

            if (idx < board.length) {
                int doll = board[idx][cur];
                if (!stack.isEmpty() && stack.peek() == doll) {
                    stack.pop();
                    answer += 2;
                } else {
                    stack.push(doll);
                }
                cnt[moves[i]] = idx + 1;
            }
        }

        return answer;
    }
}