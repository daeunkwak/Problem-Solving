package just.p_0909;

import java.util.*;

/**
 * title : 트리 순회
 * date : 2025-09-17
 */
public class boj22856 {

    private static class Node {
        int val;
        Node left;
        Node right;
        Node parent;

        Node (int val) {
            this.val = val;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();  // 노드 개수

        Map<Integer, Node> nodeMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int val = sc.nextInt();
            int left = sc.nextInt();
            int right = sc.nextInt();

            nodeMap.putIfAbsent(val, new Node(val));
            Node current = nodeMap.get(val);

            if (left != -1) {
                nodeMap.putIfAbsent(left, new Node(left));
                current.left = nodeMap.get(left);
                nodeMap.get(left).parent = current;
            }

            if (right != -1) {
                nodeMap.putIfAbsent(right, new Node(right));
                current.right = nodeMap.get(right);
                nodeMap.get(right).parent = current;
            }
        }

        Node lastNode = nodeMap.get(1);
        while (lastNode.right != null) lastNode = lastNode.right;

        Set<Integer> visited = new HashSet<>();
        visited.add(1);
        Node curNode = nodeMap.get(1);

        // 루트 -> 끝까지 왼쪽자식 -> 오른쪽자식 -> 끝이면 종료, 아니면 부모로 이동 -> 왼 -> 오 반복
        int cnt = 0;
        while (visited.size() < N) {
            // 왼쪽자식, 오른쪽자식으로 이동
            if (curNode.left != null && !visited.contains(curNode.left.val)) {
                curNode = curNode.left;
                cnt++;
            } else if (curNode.right != null && !visited.contains(curNode.right.val)) {
                curNode = curNode.right;
                cnt++;
            } else {  // 다 이동했으면 처리
                visited.add(curNode.val);

                if (curNode == lastNode) break;  // 순회 종료

                if (curNode.parent != null) {
                    curNode = curNode.parent;
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }
}
