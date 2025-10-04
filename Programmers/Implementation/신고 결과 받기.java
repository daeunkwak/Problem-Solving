import java.util.*;

/**
 * title : 신고 결과 받기
 * date : 2025-10-03
 */
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {

        // 각 유저는 한 번에 한 명 신고 가능, 계속 신고 가능, 동일 유저는 여러번 신고해도 1번으로 간주
        // K번 이상 신고된 유저 > 정지 메일 발송
        // 처리 결과 메일을 받은 횟수를 배열에 담기

        // 신고 기록
        Map<String, Set<String>> reportMap = new HashMap<>();
        for (String id : id_list) reportMap.put(id, new HashSet<>());

        for (int i = 0; i < report.length; i++) {
            String[] splitted = report[i].split(" ");
            String reporter = splitted[0];
            String reportee = splitted[1];

            reportMap.get(reporter).add(reportee);
        }

        // k번 이상인지 확인
        List<String> stopped = new ArrayList<>();
        Map<String, Integer> countMap = new HashMap<>();

        for (String id : reportMap.keySet()) {
            for (String reported : reportMap.get(id)) {
                countMap.put(reported, countMap.getOrDefault(reported, 0) + 1);
            }
        }

        for (String id : id_list) {
            if (countMap.containsKey(id) && countMap.get(id) >= k) stopped.add(id);
        }

        // 결과 메일 count
        int[] result = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++) {
            String id = id_list[i];
            for (String reported : reportMap.get(id)) {
                if (stopped.contains(reported)) result[i]++;
            }
        }


        return result;
    }
}