import java.util.*;

/**
 * title : 성격 유형 검사하기
 * date : 2025-03-07
 */

class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";

        // 점수 관리
        Map<Character, Integer> scoreMap = new HashMap<>();
        scoreMap.put('R', 0);
        scoreMap.put('T', 0);
        scoreMap.put('C', 0);
        scoreMap.put('F', 0);
        scoreMap.put('J', 0);
        scoreMap.put('M', 0);
        scoreMap.put('A', 0);
        scoreMap.put('N', 0);

        // choice > 점수 환산
        Map<Integer, Integer> parseScore = new HashMap<>();
        parseScore.put(1, 3);
        parseScore.put(2, 2);
        parseScore.put(3, 1);
        parseScore.put(4, 0);
        parseScore.put(5, 1);
        parseScore.put(6, 2);
        parseScore.put(7, 3);

        for (int i = 0; i < survey.length; i++) {
            Character first = survey[i].charAt(0);
            Character second = survey[i].charAt(1);
            int score = parseScore.get(choices[i]);

            if (choices[i] < 4) {
                scoreMap.put(first, scoreMap.get(first) + score);
            } else if (choices[i] > 4) {
                scoreMap.put(second, scoreMap.get(second) + score);
            }
        }

        // RT
        if (scoreMap.get('R') < scoreMap.get('T')) {
            answer += "T";
        } else {
            answer += "R";
        }

        // CF
        if (scoreMap.get('C') < scoreMap.get('F')) {
            answer += "F";
        } else {
            answer += "C";
        }

        // JM
        if (scoreMap.get('J') < scoreMap.get('M')) {
            answer += "M";
        } else {
            answer += "J";
        }

        // AN
        if (scoreMap.get('A') < scoreMap.get('N')) {
            answer += "N";
        } else {
            answer += "A";
        }


        return answer;
    }
}