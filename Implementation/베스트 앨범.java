"""
author : https://github.com/daeunkwak
date : 2024-05-07
title : 베스트 앨범
"""

import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        ArrayList<Integer> answer = new ArrayList<>();

        HashMap<String, Integer> num = new HashMap<>(); // 장르별 총 개수
        HashMap<String, HashMap<Integer, Integer>> music = new HashMap<>(); // 장르에 속하는 노래 및 재생횟수

        for(int i = 0; i < plays.length; i++) {
            if(!num.containsKey(genres[i])) {   // map에 없는 장르 > 새로 생성
                HashMap<Integer, Integer> map = new HashMap<>();
                map.put(i, plays[i]);
                music.put(genres[i], map);
                num.put(genres[i], plays[i]);
            } else {    // 이미 있는 장르 > 추가
                music.get(genres[i]).put(i, plays[i]);
                num.put(genres[i], num.get(genres[i]) + plays[i]);
            }
        }

        List<String> keySet = new ArrayList(num.keySet());
        Collections.sort(keySet, (s1, s2) -> num.get(s2) - (num.get(s1)));  // 내림차순 정렬

        // 가장 많이 재생된 노래 추가
        for(String key : keySet) {
            HashMap<Integer, Integer> map = music.get(key);
            List<Integer> genre_key = new ArrayList(map.keySet());

            Collections.sort(genre_key, (s1, s2) -> map.get(s2) - (map.get(s1)));

            // 최대 2개 추가
            answer.add(genre_key.get(0));
            if(genre_key.size() > 1){
                answer.add(genre_key.get(1));
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}