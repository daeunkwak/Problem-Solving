import java.util.*;

/**
 * title : 주사위 고르기
 * date : 2025-04-04
 */

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = {0, 0};
        int minSize = gems.length + 1;  // ****** 최대 최소 디폴트 넉넉하게 설정

        Set<String> gemSet = new HashSet<>();
        for (String gem : gems) {
            gemSet.add(gem);
        }
        int gemSize = gemSet.size();

        int start = 0;
        Map<String, Integer> map = new HashMap<>();
        for (int end = 0; end < gems.length; end++) {

            // ** if (map.size() < gemSize) 필요 없음
            map.put(gems[end], map.getOrDefault(gems[end], 0) + 1);

            while (map.size() == gemSize) {
                if (end - start + 1 < minSize) {
                    minSize = end - start + 1;
                    answer[0] = start + 1;
                    answer[1] = end + 1;
                }

                int cnt = map.get(gems[start]);
                if (cnt == 1) {
                    map.remove(gems[start]);
                } else {
                    map.put(gems[start], cnt - 1);
                }
                start++;
            }

        }


        return answer;
    }
}