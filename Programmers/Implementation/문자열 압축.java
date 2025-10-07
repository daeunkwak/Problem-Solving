import java.util.*;

/**
 * title : 문자열 압축
 * date : 2025-10-07
 */
class Solution {
    public int solution(String s) {
        int answer = 0;

        // 연속 문자 > 반복되는 값으로 표현
        // n개 단위로 반복해서 압축 가능
        // 가장 짧은 단위로 압축한 길이

        int minLength = s.length();
        for (int i = 1; i <= s.length() / 2; i++) {
            minLength = Math.min(minLength, getLength(s, i));
        }

        return minLength;
    }

    private static int getLength(String s, int size) {
        int idx = 0;
        StringBuilder sb = new StringBuilder();

        while (idx < s.length()) {
            // 남은거 추가하기
            if (idx + size >= s.length()) {
                for (int i = idx; i < s.length(); i++) sb.append(s.charAt(i));
                break;
            }

            String cur = s.substring(idx, idx + size);

            int cnt = 1;
            while (idx + size * (cnt + 1) <= s.length()) {
                String next = s.substring(idx + size * cnt, idx + size * (cnt + 1));
                // System.out.println("cur : " + cur + " next : " + next);
                if (cur.equals(next)) {
                    cnt++;
                } else {
                    break;
                }
            }

            if (cnt >= 2) {
                sb.append(cnt);
                sb.append(cur);
                idx += (cnt) * size;
            } else {
                for (int i = 0; i < size; i++) sb.append(s.charAt(idx + i));
                idx += size;
                // sb.append(s.charAt(idx));
                // idx++;
            }
        }
        // System.out.println("size : " + size + " str : " + sb.toString());

        return sb.toString().length();
    }
}


