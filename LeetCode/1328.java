
/**
 * title : Break a Palindrome
 * date : 2025-09-09
 */
class Solution {
    public String breakPalindrome(String palindrome) {
        // 소문자 하나 바꿔서 팰린드롬이 아니게 만들기
        // 최대한 앞 문자를 a로 바꾸기

        int size = palindrome.length();
        if (size == 1) return "";

        // abccba
        char[] chars = palindrome.toCharArray();
        boolean flag = false;
        for (int i = 0; i < size / 2; i++) {
            if (chars[i] != 'a') {
                chars[i] = 'a';
                flag = true;
                break;
            }
        }

        if (!flag) chars[size - 1] = 'b';

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            result.append(chars[i]);
        }

        return result.toString();
    }
}