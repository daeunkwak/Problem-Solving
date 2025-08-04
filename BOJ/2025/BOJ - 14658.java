
import java.util.*;

/**
 * title : 하늘에서 별똥별이 빗발친다
 * date : 2025-08-04
 */
public class boj14658 {

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static ArrayList<Node> list;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int L = sc.nextInt();
        int K = sc.nextInt();

        list = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            list.add(new Node(x, y));
        }

        // *** N, M을 기준으로 탐색하면 터지니까, K를 기준으로 탐색할 생각을 해야함
        // 1개를 기준으로 잡고, 잘 안풀리면 2개로 어떻게 조건을 만들까 생각
        int res = 0;
        for (Node a : list) {
            for (Node b : list) {
                res = Math.max(res, inBound(a.x, b.y, L));
            }
        }

        System.out.println(K - res);
    }

    private static int inBound(int i, int j, int l) {
        int cnt = 0;
        for (Node s : list) {
            if (s.x >= i && s.x <= i + l && s.y >= j && s.y <= j + l) {
                cnt++;
            }
        }

        return cnt;
    }
}
