import java.util.*;

/**
 * title : 프로그래머스 - 할인 행사
 * date : 2025-03-26
 */
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;

        // 원하는 개수
        Map<String, Integer> wantMap = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            wantMap.put(want[i], number[i]);
        }

        int start = 0;
        int end = 9;
        while (end < discount.length) {
            Map<String, Integer> curMap = new HashMap<>();
            for (int i = start; i <= end; i++) {
                curMap.put(discount[i], curMap.getOrDefault(discount[i], 0) + 1);
            }

            // 포함되어있는지 확인
            boolean flag = true;

            for (String prod : curMap.keySet()) {
                if (wantMap.get(prod) == null || wantMap.get(prod) != curMap.get(prod)) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                answer++;
            }
            start++;
            end++;
        }

        return answer;
    }
}