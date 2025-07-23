package just.p_0723;

import java.util.*;

/**
 * title : 합
 * date : 2025-07-23
 */
public class boj1132 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();

        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = sc.nextLine();
        }

        Map<Character, Long> map = new HashMap<>();
        Set<Character> notZero = new HashSet<>();
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                if (i == 0) {
                    notZero.add(word.charAt(0));
                }
                if (word.length() - i == 1) {
                    map.put(word.charAt(i), map.getOrDefault(word.charAt(i), 0L) + 1);
                } else {
                    map.put(word.charAt(i), map.getOrDefault(word.charAt(i), 0L)
                            + (long) Math.pow(10, word.length() - i - 1));
                }
            }
        }

        // value 정렬
        List<Map.Entry<Character, Long>> sorted = new ArrayList<>(map.entrySet());
        sorted.sort((a, b) -> Long.compare(b.getValue(), a.getValue()));

        // 숫자 배정
        Map<Character, Integer> newMap = new HashMap<>();

        // 0부터 배정
        Character zero = null;
        // 모든 알파벳이 포함된다는 보장이 없으므로 9부터 시작하면 안 됨
        // 0이 필요한 경우에만 배정
        if (sorted.size() > 9) {
            for (int i = sorted.size() - 1; i >= 0; i--) {
                if (!notZero.contains(sorted.get(i).getKey())) {
                    zero = sorted.get(i).getKey();
                    break;
                }
            }
            newMap.put(zero, 0);
        }

        int num = 9;
        for (Map.Entry<Character, Long> entry : sorted) {
            if (entry.getKey() != zero) {
                newMap.put(entry.getKey(), num);
                num--;
            }
        }

        long sum = 0;
        for (String word : words) {
            long res = 0;
            for (char c : word.toCharArray()) {
                res = res * 10 + newMap.get(c);
            }
            sum += res;
        }
        System.out.println(sum);
    }

}
