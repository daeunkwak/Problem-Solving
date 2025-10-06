import java.util.*;

/**
 * title : 메뉴 리뉴얼
 * date : 2025-10-06
 */
class Solution {
    static List<String> combs;
    public String[] solution(String[] orders, int[] course) {

        String[] answer = {};

        // 이전 손님들이 가장 많이 주문한 메뉴들을 코스로 구성
        // 최소 2개 이상의 메뉴로 구성, 최소 2명 이상이 주문한 메뉴 조합
        // 휴리스틱 ㄴㄴ

        // <course수, <조합, count>>
        Map<Integer, Map<String, Integer>> map = new HashMap<>();
        for (int i = 0; i < course.length; i++) {
            map.put(course[i], new HashMap<>());
        }

        for (int i = 0; i < orders.length; i++) {
            String order = orders[i];

            // 알파벳순 정렬 (조합 중복 제거용)
            char[] arr = order.toCharArray();
            Arrays.sort(arr);
            order = new String(arr);

            combs = new ArrayList<>();
            for (int j = 0; j < course.length; j++) {
                if (course[j] > order.length()) continue; // 주문 길이보다 긴 코스는 불가능
                getCombs(order, course[j], "", 0);
            }

            // map 채우기
            for (String comb : combs) {
                int key = comb.length();
                Map<String, Integer> inner = map.get(key);
                inner.put(comb, inner.getOrDefault(comb, 0) + 1);
            }
        }

        // 조합 count
        List<String> maxCourses = new ArrayList<>();

        for (int c : course) {
            Map<String, Integer> inner = map.get(c);
            if (inner == null || inner.isEmpty()) continue;

            int maxCount = 0;
            for (int val : inner.values()) {
                if (val >= 2) maxCount = Math.max(maxCount, val);
            }
            if (maxCount < 2) continue; // 최소 2명 이상 조건

            for (Map.Entry<String, Integer> e : inner.entrySet()) {
                if (e.getValue() == maxCount) {
                    maxCourses.add(e.getKey());
                }
            }
        }

        Collections.sort(maxCourses);
        answer = maxCourses.toArray(new String[0]);
        return answer;
    }

    private static void getCombs(String order, int num, String cur, int idx) {
        // num개 조합 모두 구하기
        if (cur.length() == num) {
            combs.add(cur);
            return;
        }

        if (idx == order.length()) return;

        getCombs(order, num, cur, idx + 1);
        String newOrder = cur + order.charAt(idx);
        getCombs(order, num, newOrder, idx + 1);
    }
}
