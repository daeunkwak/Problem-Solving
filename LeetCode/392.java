import java.util.*;

/**
 * title : Is Subsequence
 * date : 2025-08-10
 */
class Solution {
    public boolean isSubsequence(String s, String t) {
        // a b c d e > ace, abc,
        // O(N)

        if (s.length() == 0) {
            return true;
        }

        int subIdx = 0;
        char subChar = s.charAt(0);
        int endIdx = s.length() - 1;
        int idx = 0;
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == subChar) {
                if (subIdx == endIdx) {
                    return true;
                } else {
                    subIdx++;
                    subChar = s.charAt(subIdx);
                    // System.out.println("subIdx : " + subIdx + " subChar : " + subChar);
                }
            }
            idx++;
        }

        return false;
    }
}