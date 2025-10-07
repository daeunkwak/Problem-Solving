import java.util.*;

/**
 * title : 괄호 변환
 * date : 2025-10-07
 */
class Solution {
    public String solution(String p) {
        String answer = "";
        // 개수 맞으면 > 균형, 짝도 맞으면 > 올바른

        // 1.
        if (p.equals("")) return "";

        // 2.
        int open = 0;
        int close = 0;
        int idx = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') open++;
            else close++;
            if (open == close) {
                idx = i;
                break;
            }
        }
        String u = p.substring(0, idx + 1);
        String v = p.substring(idx + 1);

        // 3. u가 올바른 문자열인지 확인
        open = 0;
        close = 0;
        boolean isRight = true;
        for (int i = 0; i < u.length(); i++) {
            if (p.charAt(i) == '(') open++;
            else close++;
            if (close > open) {
                isRight = false;
                break;
            }
        }

        // 올바른 문자열이라면 v에 대해 1단계부터 수행
        if (isRight) {
            String newV = solution(v);
            return (u + newV);
        }

        // 4. 올바르지 않다면
        StringBuilder sb = new StringBuilder();
        // 4-1 ~ 4-3.
        sb.append("(");
        sb.append(solution(v));
        sb.append(")");

        // 4-4
        StringBuilder newU = new StringBuilder();
        for (int i = 1; i < u.length() - 1; i++) {
            if (u.charAt(i) == '(') newU.append(")");
            else newU.append("(");
        }
        sb.append(newU.toString());

        return sb.toString();
    }
}