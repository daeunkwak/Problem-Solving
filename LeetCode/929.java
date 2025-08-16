import java.util.*;

/**
 * title : Unique Email Addresses
 * date : 2025-08-10
 */
class Solution {
    public int numUniqueEmails(String[] emails) {
        // . of local name > ignored
        // after + of local name > ignored
        // return the number of addresses that receive mails

        Set<String> set = new HashSet<>();
        for (int i = 0; i < emails.length; i++) {
            String[] names = emails[i].split("@");
            String localName = names[0];

            // + 앞부분만 사용할 경우, indexOf + substring이 좀 더 빠름
            // localName = localName.split("\\+")[0];
            int plusIdx = localName.indexOf('+');
            if (plusIdx != -1) {
                localName = localName.substring(0, plusIdx);
            }

            localName = localName.replace(".", "");
            set.add(localName + '@' + names[1]);
        }
        return set.size();
    }
}