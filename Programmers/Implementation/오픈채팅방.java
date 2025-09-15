import java.util.*;

/**
 * title : 오픈채팅방
 * date : 2025-09-15
 */
class Solution {
    public String[] solution(String[] record) {
        List<String> process = new ArrayList<>();
        Map<String, List<Integer>> map = new HashMap<>();
        Map<String, String> nicknames = new HashMap<>();

        int idx = 0;
        for (int i = 0; i < record.length; i++) {
            String[] cur = record[i].split(" ");
            String action = cur[0];
            String uid = cur[1];

            switch (action) {
                case "Enter" :
                    process.add("님이 들어왔습니다.");

                    if (!map.containsKey(uid)) {
                        map.put(uid, new ArrayList<>());
                    }
                    map.get(uid).add(idx);

                    nicknames.put(uid, cur[2]);
                    idx++;
                    break;

                case "Leave":
                    process.add("님이 나갔습니다.");

                    map.get(uid).add(idx);
                    idx++;
                    break;

                case "Change":
                    nicknames.put(uid, cur[2]);
                    break;
            }
        }

        for (String uid : map.keySet()) {
            String name = nicknames.get(uid);
            for (int index : map.get(uid)) {
                String message = process.get(index);
                if (message.endsWith("님이 들어왔습니다.")) {
                    process.set(index, name + "님이 들어왔습니다.");
                } else if (message.endsWith("님이 나갔습니다.")) {
                    process.set(index, name + "님이 나갔습니다.");
                }
            }
        }

        return process.toArray(new String[0]);
    }
}