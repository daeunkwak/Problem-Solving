/**
 author : Kwak Daeun
 github : https://github.com/daeunkwak

 title : 카드2
 description : 자료구조, 큐
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_2164 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++){
            queue.add(i);
        }

        while(queue.size() != 1){
            queue.poll();
            // poll() -> 맨 앞 원소 제거와 동시에 리턴값으로 추가
            queue.add(queue.poll());
        }

        System.out.println(queue.poll());
    }
}
