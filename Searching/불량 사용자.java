"""
author : https://github.com/daeunkwak
date : 2024-03-28
title : 불량 사용자
"""

import java.util.*;

class Solution {

    String[] userIds;
    String[] bannedIds;
    boolean[] visited;
    HashSet<HashSet<String>> result = new HashSet<>();  // 결과값(중복 제거)

    public int solution(String[] user_id, String[] banned_id) {

        userIds = user_id;
        bannedIds = banned_id;
        visited = new boolean[userIds.length];

        dfs(new HashSet<>(), 0);

        return result.size();
    }

    public void dfs(HashSet<String> set, int depth){

        if (depth == bannedIds.length) {    // 탐색 완료
            result.add(set);
            return;
        }

        for (int i = 0; i < userIds.length; i++) {
            if (set.contains(userIds[i])) {  // 이미 선택한 id인 경우
                continue;
            }

            if (check(userIds[i], bannedIds[depth])) {  // 조건 확인
                set.add(userIds[i]);
                dfs(new HashSet<>(set), depth + 1);
                set.remove(userIds[i]);     // 백트래킹
            }
        }


    }

    boolean check(String userId, String bannedId) {
        // 1. 길이 확인
        if (userId.length() != bannedId.length()) {
            return false;
        }

        // 2. * 제외 일치여부 학인
        boolean match = true;
        for (int i = 0; i < userId.length(); i++) {
            if (bannedId.charAt(i) != '*' && userId.charAt(i) != bannedId.charAt(i)) {
                match = false;
                break;
            }
        }

        return match;
    }
}