
/**
 * title : Longest Subset Without Repeating Characters
 * date : 2025-08-23
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // abcabcbb
        // hashset > a, b, c -> a -> (while.. remove start) remove a -> b, c, a
        // O(n)

        int start = 0;
        Set<Character> set = new HashSet<>();

        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            while (set.contains(s.charAt(i))) {
                set.remove(s.charAt(start)); // 조심
                start++;
            }
            set.add(s.charAt(i));
            maxLength = Math.max(set.size(), maxLength);
        }

        return maxLength;
    }
}