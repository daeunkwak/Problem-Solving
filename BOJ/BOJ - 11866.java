/**
 * author : https://github.com/daeunkwak
 * date : 2023-01-18
 * title : 요세푸스 문제 0
 * description : 자료구조, 큐
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ_11866 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            q.add(i+1);
        }
        System.out.print("<");
        while(N-- > 0) {
            for (int i = 0; i < K; i++) {
                if(i == K - 1) {
                    if(N == 0) System.out.print(q.poll());
                    else System.out.print(q.poll() + ", ");
                }else {
                    q.add(q.poll());
                }
            }
        }
        System.out.print(">");



    }
}
