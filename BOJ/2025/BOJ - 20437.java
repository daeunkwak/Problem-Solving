
import java.util.*;

/**
 * title : 문자열 게임 2
 * date : 2025-08-01
 */
public class boj20437 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int i = 0; i < T; i++) {
            String words = sc.next();
            int K = sc.nextInt();

            Map<Character, List<Integer>> map = new HashMap<>();
            for (int j = 0; j < words.length(); j++) {
                if (map.containsKey(words.charAt(j))) {
                    map.get(words.charAt(j)).add(j);
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(j);
                    map.put(words.charAt(j), list);
                }
            }

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for (Character ch : map.keySet()) {
                if (map.get(ch).size() >= K) {
                    List<Integer> curList = map.get(ch);

                    // 어쨋든 무조건 K개를 고르는거니까 이렇게 풀어야함
                    for (int m = 0; m <= curList.size() - K; m++) {
                        int length = curList.get(m + K - 1) - curList.get(m) + 1;
                        min = Math.min(min, length);
                        max = Math.max(max, length);
                    }
                }
            }

            if (min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(min + " " + max);
            }
        }


    }
}
