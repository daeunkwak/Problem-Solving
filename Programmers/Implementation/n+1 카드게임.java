import java.util.*;

/**
 * title : n + 1 카드게임
 * date : 2025-10-10
 */
class Solution {
    public int solution(int coin, int[] cards) {

        int n = cards.length;

        Set<Integer> cur = new HashSet<>();
        Set<Integer> left = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();

        // 초기값 매칭
        for (int i = 0; i < n / 3; i++) {
            if (cur.contains(n + 1 - cards[i])) {
                queue.offer(new int[]{cards[i], n + 1 - cards[i]});
                cur.remove(n + 1 - cards[i]);
            } else {
                cur.add(cards[i]);
            }
        }

        int round = 1;
        int idx = n / 3;

        while (idx + 1 < n) {
            // 카드 2장 뽑기
            int c1 = cards[idx];
            int c2 = cards[idx + 1];
            left.add(c1);
            left.add(c2);

            boolean paired = false;

            // 이미 있으면 패스
            if (!queue.isEmpty()) {
                queue.poll();
                paired = true;
            }

            // 새로운 카드와 매칭
            else if (coin >= 1) {
                if (cur.contains(n + 1 - c1)) {
                    cur.remove(n + 1 - c1);
                    left.remove(c1);
                    coin--;
                    paired = true;
                } else if (cur.contains(n + 1 - c2)) {
                    cur.remove(n + 1 - c2);
                    left.remove(c2);
                    coin--;
                    paired = true;
                } else {
                    // 기존에 뽑았던 카드와 매칭
                    int foundCur = -1;
                    int foundLeft = -1;

                    for (int num : cur) {
                        int target = n + 1 - num;
                        if (left.contains(target)) {
                            foundCur = num;
                            foundLeft = target;
                            break;
                        }
                    }

                    if (foundCur != -1) {
                        cur.remove(foundCur);
                        left.remove(foundLeft);
                        coin--;
                        paired = true;
                    }
                }
            }

            // 기존 카드끼리 매칭
            if (!paired && coin >= 2) {
                int foundLeft1 = -1;
                int foundLeft2 = -1;

                for (int num : left) {
                    int target = n + 1 - num;
                    if (left.contains(target) && num != target) {
                        foundLeft1 = num;
                        foundLeft2 = target;
                        break;
                    }
                }

                if (foundLeft1 != -1) {
                    left.remove(foundLeft1);
                    left.remove(foundLeft2);
                    coin -= 2;
                    paired = true;
                }
            }

            if (!paired) {
                break;
            }

            round++;
            idx += 2;
        }

        return round;
    }
}