import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {

        int answer = 0;
        boolean[] visited = new boolean[words.length];
        Queue<String> queue = new LinkedList<>();

        queue.offer(begin);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                if (current.equals(target)) {
                    return answer;
                }

                for (int j = 0; j < words.length; j++) {
                    if ((!visited[j]) && compare(current, words[j])) {
                        visited[j] = true;
                        queue.offer(words[j]);
                    }
                }
            }
            answer++; // 같은 레벨 처리 > answer 1씩 증가
        }
        return 0;
    }

    static boolean compare(String s1, String s2) {

        int n = 0;
        for (int i = 0; i < s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i)){
                n++;
            }
        }

        return n == 1;
    }
}