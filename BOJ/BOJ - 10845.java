/**
 * author : https://github.com/daeunkwak
 * date : 2022-01-04
 * title : 큐
 * description : 자료구조, 큐
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_10845 {

    public static void main(String[] args) throws IOException {

        Queue<Integer> queue = new LinkedList<Integer>();

        int N;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int last = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String S = st.nextToken();

            switch (S) {
                case "push":
                    int B = Integer.parseInt(st.nextToken());
                    last = B;
                    queue.add(B);
                    break;

                case "pop":
                    if (queue.size() == 0) {
                        System.out.println(-1);
                    } else {
                        System.out.println(queue.poll());
                    }
                    break;

                case "size":
                    System.out.println(queue.size());
                    break;

                case "empty":
                    if (queue.size() == 0) {
                        System.out.println(1);
                    } else {
                        System.out.println(0);
                    }
                    break;

                case "front":
                    if (queue.size() == 0) {
                        System.out.println(-1);
                    } else {
                        System.out.println(queue.peek());
                    }
                    break;

                case "back":
                    if (queue.size() == 0) {
                        System.out.println(-1);
                    } else {
                        System.out.println(last);
                    }
                    break;

            }

        }
    }
}
