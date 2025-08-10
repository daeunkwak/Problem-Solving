"""
author : https://github.com/daeunkwak
date : 2024-06-27
title : 혼자 놀기의 달인
"""

import java.util.*;

// 무작위로 상자 섞음 > 1번부터 번호 붙이기
// 임의로 상자 선택 > 상자 안 g카드 확인 > 확인한 번호에 해당되는 상자 선택
// > 열어야 하는 상자가 열려있을 때까지 반복
// 또 임의로 반복 > 2번 상자 그룹
// 1번 그룹 개수 x 2번 그룹 개수 > 점수
// 게임에서 얻을 수 있는 최고 점수 구하기

class Solution {

    static boolean[] visit;
    static int depth;

    public int solution(int[] cards) {

        int answer = 1;
        visit = new boolean[cards.length + 1];

        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < cards.length; i++) {
            if(!visit[i + 1]) {
                depth = 1;
                visit[i + 1] = true;
                dfs(cards[i], cards);
                list.add(depth);
            }
        }

        if(list.size() < 2) {
            return 0;
        }

        // 내림차순 정렬 > 가장 큰 값 2개 곱함
        Collections.sort(list, Collections.reverseOrder());
        answer = list.get(0) * list.get(1);
        return answer;
    }

    private static void dfs(int idx, int[] cards) {
        if(!visit[idx]) {
            visit[idx] = true;
            depth += 1;
            dfs(cards[idx - 1], cards);
        }
    }
}
