import java.io.IOException;
import java.util.*;

/**
 * title : 카드 정렬하기
 * date : 2025-03-19
 */
public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();  // 묶음 수

        PriorityQueue<Integer> heap = new PriorityQueue<>();  // 오름차순 정렬
        for (int i = 0; i < n; i++) {
            heap.add(scanner.nextInt());
        }

        int sum = 0;
        while (!heap.isEmpty()) {
            if (heap.size() >= 2) {
                int add = heap.poll() + heap.poll();
                heap.add(add);
                sum += add;
            } else {
                heap.poll();
            }
        }

        System.out.println(sum);

    }


}