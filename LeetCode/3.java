
/**
 * title : Longest Subset Without Repeating Characters
 * date : 2025-08-16
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // 중복된 문자 없는 가장 긴 부분문자열 길이 구하기
        // abcabcbb
        // 투포인터 + set
        // a b c -> remove a -> b c -> add a -> b c a

        Set<Character> set = new HashSet<>();
        int start = 0;
        int maxLength = 0;
        for (int end = 0; end < s.length(); end++) {
            char cur = s.charAt(end);
            if (!set.contains(cur)) {
                set.add(cur);
            } else {
                while (set.contains(cur)) {
                    set.remove(s.charAt(start));
                    start++;
                }
                set.add(cur);
            }
            maxLength = Math.max(maxLength, set.size());
        }

        return maxLength;
    }
}