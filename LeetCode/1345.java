

/**
 * title : Jump Game IV
 * date : 2025-09-15
 */
class Solution {
    public int minJumps(int[] arr) {
        // 앞뒤 한칸씩 or 같은수로 점프

        if (arr.length == 1) return 0;

        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < arr.length; i++) map.computeIfAbsent(arr[i], k -> new ArrayList()).add(i);

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        int cnt = 0;
        while(!queue.isEmpty()) {
            cnt++;
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int cur = queue.poll();

                // 한칸 뒤로
                if(cur - 1 >= 0 && map.containsKey(arr[cur - 1])){
                    queue.offer(cur - 1);
                }

                // 한칸 앞으로
                if(cur + 1 < arr.length && map.containsKey(arr[cur + 1])){
                    if(cur + 1 == arr.length - 1) return cnt;
                    queue.offer(cur + 1);
                }

                // 같은수로 점프
                if(map.containsKey(arr[cur])){
                    for(int k : map.get(arr[cur])){
                        if(k != cur){
                            if(k == arr.length - 1) return cnt;
                            queue.offer(k);
                        }
                    }
                }

                // 방문처리
                map.remove(arr[cur]);
            }

        }

        return cnt;
    }
}