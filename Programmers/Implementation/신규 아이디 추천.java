import java.util.*;

/**
 * title : 신규 아이디 추천
 * date : 2025-10-06
 */
class Solution {
    public String solution(String new_id) {

        List<Character> letters = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            letters.add(c);
        }
        for (char c = '0'; c <= '9'; c++) {
            letters.add(c);
        }
        letters.add('.');
        letters.add('-');
        letters.add('_');

        // 1. 소문자로
        new_id = new_id.toLowerCase();

        // 2. 문자제거
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < new_id.length(); i++) {
            char c = new_id.charAt(i);
            if (letters.contains(c)) sb.append(c);
        }
        new_id = sb.toString();

        // 3. 연속된 마침표 제거
        while (isExists(new_id)) new_id = new_id.replace("..", ".");

        // 4. 처음 or 끝
        if (new_id.length() > 0 && new_id.charAt(0) == '.') {
            if (new_id.length() >= 2) new_id = new_id.substring(1);
            else new_id = "";
        }
        if (new_id.length() > 0 && new_id.charAt(new_id.length() - 1) == '.') {
            if (new_id.length() >= 2) new_id = new_id.substring(0, new_id.length() - 1);
            else new_id = "";
        }

        // 5. 빈 문자열이라면 a 대입
        if (new_id.equals("")) new_id = "a";

        // 6. 16자 이상이면 제거
        if (new_id.length() >= 16) new_id = new_id.substring(0, 15);
        if (new_id.length() > 0 && new_id.charAt(new_id.length() - 1) == '.') {
            if (new_id.length() >= 2) new_id = new_id.substring(0, new_id.length() - 1);
            else new_id = "";
        }

        // 7. 2자 이하라면 마지막 문자를 반복
        if (new_id.length() <= 2) {
            char last = new_id.charAt(new_id.length() - 1);
            while (new_id.length() <= 2) new_id += last;
        }

        return new_id;
    }

    private static boolean isExists(String str) {
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == '.' && str.charAt(i + 1) == '.') return true;
        }
        return false;
    }
}



