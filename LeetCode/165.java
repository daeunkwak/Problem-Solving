import java.util.*;

/**
 * title : Compare Version Numbers
 * date : 2025-08-12
 */
class Solution {
    public int compareVersion(String version1, String version2) {
        // version 1 > version2 -> 1
        // version 1 < verstion 2 -> -1
        // ** escape 조심
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        int size = Math.max(v1.length, v2.length);
        for (int i = 0; i < size; i++) {
            // 특정 version이 .0.1.1 이렇게 계속 혼자 길더라도, 0보다만 크면 조건 만족하므로 0으로 초기화해도 ㄱㅊ
            int num1 = 0;
            int num2 = 0;

            // 복잡하게 변수 설정안하고, 매번 그냥 비교하는 방법도 있음
            if (v1.length > i) {
                num1 = Integer.parseInt(v1[i]);
            }
            if (v2.length > i) {
                num2 = Integer.parseInt(v2[i]);
            }
            // System.out.println("i : " + i + " num1 : " + num1 + " num2 : " + num2);

            if (num1 > num2) {
                return 1;
            } else if (num1 < num2) {
                return -1;
            }
        }

        return 0;
    }
}