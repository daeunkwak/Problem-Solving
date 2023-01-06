/**
 * author : https://github.com/daeunkwak
 * date : 2022-01-06
 * title : 덱
 * description : 자료구조, 덱
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_10866 {

    public static void main(String[] args) throws IOException {

        int N;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String S = st.nextToken();

            switch (S) {
                case "push_front":
                    int B = Integer.parseInt(st.nextToken());
                    deque.addFirst(B);
                    break;

                case "push_back":
                    int C = Integer.parseInt(st.nextToken());
                    deque.addLast(C);
                    break;

                case "pop_front":
                    if (deque.size() == 0) {
                        System.out.println(-1);
                    } else {
                        System.out.println(deque.removeFirst());
                    }
                    break;

                case "pop_back":
                    if (deque.size() == 0) {
                        System.out.println(-1);
                    } else {
                        System.out.println(deque.removeLast());
                    }
                    break;

                case "size":
                    System.out.println(deque.size());
                    break;

                case "empty":
                    if (deque.size() == 0) {
                        System.out.println(1);
                    } else {
                        System.out.println(0);
                    }
                    break;

                case "front":
                    if (deque.size() == 0) {
                        System.out.println(-1);
                    } else {
                        System.out.println(deque.getFirst());
                    }
                    break;

                case "back":
                    if (deque.size() == 0) {
                        System.out.println(-1);
                    } else {
                        System.out.println(deque.peekLast());
                    }
                    break;

            }

        }
    }


}
