"""
author : https://github.com/daeunkwak
date : 2024-06-22
title : 광물 캐기
"""

import java.util.*;

class Mineral{
    List<String> minerals;
    Integer diaNum;
    Integer ironNum;
    Integer stoneNum;
    Integer sum;

    public Mineral(List<String> minerals, Integer diaNum, Integer ironNum, Integer stoneNum, Integer sum) {
        this.minerals = minerals;
        this.diaNum = diaNum;
        this.ironNum = ironNum;
        this.stoneNum = stoneNum;
        this.sum = sum;
    }

    public List<String> getMinerals() {
        return minerals;
    }

    public Integer getSum() {
        return sum;
    }
}

class Solution {
    public int solution(int[] picks, String[] minerals) {

        // 곡괭이 개수가 부족할 경우 예외처리 > 리스트 자르기
        int pickSum = 0;
        for(int i : picks){
            pickSum += i;
        }

        if(pickSum * 5 < minerals.length){
            minerals = Arrays.copyOfRange(minerals, 0, pickSum * 5);
        }

        int answer = 0;

        int score;
        int diaNum;
        int stoneNum;
        int ironNum;

        int cnt = 0;
        List<Mineral> mineralList = new ArrayList<>();
        while(cnt < minerals.length){

            List<String> str = new ArrayList<>();
            score = 0; diaNum = 0; ironNum = 0; stoneNum = 0;

            for(int i = 0; i < 5; i++){
                if(cnt >= minerals.length){
                    break;
                }
                str.add(minerals[cnt]);
                if(minerals[cnt].equals("diamond")){
                    score += 25;
                    diaNum += 1;
                } else if(minerals[cnt].equals("iron")){
                    score += 5;
                    ironNum += 1;
                } else{
                    score += 1;
                    stoneNum += 1;
                }
                cnt++;
            }
            Mineral mineral = new Mineral(str, diaNum, ironNum, stoneNum, score);
            mineralList.add(mineral);
        }

        // sum 필드를 기준으로 내림차순 정렬
        mineralList.sort(Comparator.comparing(Mineral::getSum).reversed());
        int idx = 0;
        cnt = 0;

        // 다이아 곡괭이
        for(int i = 0; i < picks[0]; i++){
            if(cnt >= minerals.length){
                break;
            }
            for(int j = 0; j < mineralList.get(idx).getMinerals().size(); j++){
                answer ++;
                cnt ++;
            }
            idx ++;
        }

        // 철 곡괭이
        for(int i = 0; i < picks[1]; i++){
            if(cnt >= minerals.length){
                break;
            }
            for(int j = 0; j < mineralList.get(idx).getMinerals().size(); j++){
                if(mineralList.get(idx).getMinerals().get(j).equals("diamond")){
                    answer += 5;
                } else {
                    answer += 1;
                }
                cnt ++;
            }
            idx ++;
        }

        // 돌 곡괭이
        for(int i = 0; i < picks[2]; i++){
            if(cnt >= minerals.length){
                break;
            }
            for(int j = 0; j < mineralList.get(idx).getMinerals().size(); j++){
                if(mineralList.get(idx).getMinerals().get(j).equals("diamond")){
                    answer += 25;
                } else if(mineralList.get(idx).getMinerals().get(j).equals("iron")){
                    answer += 5;
                } else{
                    answer += 1;
                }
                cnt ++;
            }
            idx ++;
        }

        return answer;
    }
}
