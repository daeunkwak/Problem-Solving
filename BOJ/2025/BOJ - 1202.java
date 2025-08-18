package just.p_0818;

import java.util.*;

/**
 * title : 보석 도둑
 * date : 2025-08-18
 */
public class boj1202 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        PriorityQueue<int[]> gems = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (int i = 0; i < N; i++) {
            int m = sc.nextInt();
            int v = sc.nextInt();
            gems.add(new int[]{m, v});
        }

        // 완탐하면 안되니까 정렬해서 이분탐색 ?
//        TreeSet<Integer> bags = new TreeSet<>();
//        for (int i = 0; i < K; i++) {
//            bags.add(sc.nextInt());
//        }

        // 삭제가 O(N)이 아닌 자료구조 + 정렬
        // 가방 무게 중복 허용
        TreeMap<Integer, Integer> bags = new TreeMap<>();
        for (int i = 0; i < K; i++) {
            int cap = sc.nextInt();
            bags.put(cap, bags.getOrDefault(cap, 0) + 1);
        }

        long sum = 0;
        while (!gems.isEmpty() && !bags.isEmpty()) {
            int[] cur = gems.poll();
            int weight = cur[0];
            int cost  = cur[1];

            Map.Entry<Integer, Integer> bag = bags.ceilingEntry(weight);
            if (bag != null) {
                int cap = bag.getKey();
                int cnt = bag.getValue();

                if (cnt == 1) {
                    bags.remove(cap);
                }
                else {
                    bags.put(cap, cnt - 1);
                }

                sum += cost;
            }
        }

        System.out.println(sum);
    }
}
