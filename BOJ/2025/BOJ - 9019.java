package just;

import java.util.*;

/**
 * title : DSLR
 * date : 2025-07-19
 */
public class BOJ_9019 {
    static class Node {
        int num;
        String commands;

        Node(int num, String commands) {
            this.num = num;
            this.commands = commands;
        }
    }

    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int t = 0; t < T; t++) {
            int A = sc.nextInt();
            int B = sc.nextInt();
            visited = new boolean[10000];

            String result = bfs(A, B);
            System.out.println(result);
        }
    }

    static String bfs(int start, int target) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start, ""));
        visited[start] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.num == target) return cur.commands;

            // D
            int d = (cur.num * 2) % 10000;
            if (!visited[d]) {
                visited[d] = true;
                queue.add(new Node(d, cur.commands + "D"));
            }

            // S
            int s = (cur.num == 0) ? 9999 : cur.num - 1;
            if (!visited[s]) {
                visited[s] = true;
                queue.add(new Node(s, cur.commands + "S"));
            }

            // L
            int l = (cur.num % 1000) * 10 + (cur.num / 1000);
            if (!visited[l]) {
                visited[l] = true;
                queue.add(new Node(l, cur.commands + "L"));
            }

            // R
            int r = (cur.num % 10) * 1000 + (cur.num / 10);
            if (!visited[r]) {
                visited[r] = true;
                queue.add(new Node(r, cur.commands + "R"));
            }
        }

        return "";
    }
}
