/**
 author : https://github.com/daeunkwak
 title : 요세푸스 문제
 description : 자료구조, 큐
 date : 2022-07-10
 */

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        sb.append("<");

        LinkedList<Integer> queue = new LinkedList<Integer>();
        for(int i = 1; i <= N; i++){
            queue.add(i);
        }

        while(queue.size() > 1){
            // K번째면 빼서 sb에 추가, 아니면 queue 뒤로 추가
            for (int i = 0; i < K; i++){
                if (i == K-1){
                    sb.append(queue.poll() + ", ");
                } else{
                    queue.add(queue.poll());
                }
            }
        }

        sb.append(queue.poll() + ">");
        System.out.println(sb);
    }
}

