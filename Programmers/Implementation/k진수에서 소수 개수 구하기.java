import java.util.*;

/**
 * title : k진수에서 소수 개수 구하기
 * date : 2025-10-03
 */
class Solution {
    // *** 배열의 최대 인덱스 범위는 Integer,,,,,, 그래서 에라토스테네스의 체를 사용하면 안됨
    // static boolean[] isPrime;
    public int solution(int n, int k) {

        // 양의 정수 n > k진수 > 조건에 맞는 소수가 몇개인지 찾기
        // 0소수0, 소수0, 0소수, 소수 (소수에 0이 있으면 안 됨)

        // [1] 진법 변환
        String converted = convert(n, k);

        // [2] 순회하면서 소수 판별하기 (투포인터?)
        // makePrime();
        int cnt = 0;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < converted.length(); i++) {
            if (converted.charAt(i) == '0' && !sb.toString().equals("")) {
                long cur = Long.parseLong(sb.toString());
                if (isPrimeNumber(cur)) cnt++;
                sb = new StringBuilder();
                continue;
            }

            sb.append(converted.charAt(i));
        }

        if (!sb.toString().equals("")) {
            long cur = Long.parseLong(sb.toString());
            if (isPrimeNumber(cur)) cnt++;
        }

        return cnt;
    }

    static boolean isPrimeNumber(long num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;

        for (long i = 3; i * i <= num; i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }

    static String convert(int n, int k) {
        StringBuilder result = new StringBuilder();
        int temp = n;

        while (temp > 0) {
            result.append(temp % k);
            temp = temp / k;
        }

        return result.reverse().toString();
    }
}
