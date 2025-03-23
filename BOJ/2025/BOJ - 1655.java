import java.io.*;
import java.util.*;

/**
 * title : 가운데를 말해요
 * date : 2025-03-20
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // maxHeap 부터 채움
        // reverseOrder() 조건좀
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (maxHeap.size() <= minHeap.size()) {
                maxHeap.add(num);
            } else {
                minHeap.add(num);
            }

            // 중간값 비교해서 바꾸기
            // size() 확인 꼭꼭
            while (maxHeap.size() > 0 && minHeap.size() > 0 && maxHeap.peek() > minHeap.peek()) {
                int temp = maxHeap.poll();
                maxHeap.add(minHeap.poll());
                minHeap.add(temp);
            }

            sb.append(maxHeap.peek()).append('\n');
        }
        System.out.print(sb);
    }
}