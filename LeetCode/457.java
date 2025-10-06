
/**
 * title : Circular Array Loop
 * date : 2025-10-06
 */
class Solution {
    static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    public boolean circularArrayLoop(int[] nums) {
        // i -> move to nums[i]
        // 사이클 있으면 true
        int n = nums.length;
        Map<Integer, Node> nodeMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Node cur = new Node(i);
            nodeMap.put(i, cur);
        }

        for (int i = 0; i < nums.length; i++) {
            // *** 음수 모듈러연산 표준 패턴
            int next =  ((i + nums[i]) % n + n) % n;
            nodeMap.get(i).next = nodeMap.get(next);
        }

        for (int i = 0; i < n; i++) {
            Set<Node> visited = new HashSet<>();
            Node cur = nodeMap.get(i);
            boolean isForward = nums[i] > 0;

            while (cur != null) {
                // 사이클 감지
                if (visited.contains(cur)) {
                    if (visited.size() > 1) {
                        return true;
                    }
                    break;
                }

                visited.add(cur);

                // 이럴 수가 없음 모듈러 틀린거임
                // if (cur.next == null || cur.next == cur) break;

                // 자기자신 사이클은 제외
                if (cur.next == cur) break;

                // all pos or all neg
                if ((nums[cur.next.val] > 0) != isForward) break;

                cur = cur.next;
            }
        }

        return false;
    }
}