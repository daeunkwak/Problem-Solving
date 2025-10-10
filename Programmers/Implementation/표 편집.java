import java.util.*;

/**
 * title : 표 편집
 * date : 2025-10-10
 */
class Solution {

    static class Node{
        int idx;
        Node next;
        Node prev;

        Node(int idx) {
            this.idx = idx;
        }
    }


    public String solution(int n, int k, String[] cmd) {

        Map<Integer, Node> map = new HashMap<>();

        // 노드 초기화
        Node start = new Node(0);
        Node cur = start;

        // for (int i = 1; i < n; i++) {
        //     Node newNode = new Node(i);
        //     newNode.prev = cur;
        //     cur.next = newNode;
        //     cur = newNode;
        // }
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) nodes[i] = new Node(i);

        for (int i = 0; i < n; i++) {
            if (i > 0) nodes[i].prev = nodes[i - 1];
            if (i < n - 1) nodes[i].next = nodes[i + 1];
        }


        // 삭제된 노드
        Stack<Node> deleted = new Stack<>();
        // Map<Node, Node> beforeNode = new HashMap<>();

        // 시작 위치로 이동
        cur = nodes[k];

        // 현재 커서 위치가 cur
        for (int i = 0; i < cmd.length; i++) {
            String[] split = cmd[i].split(" ");
            if (split[0].equals("U")) {
                int move = Integer.parseInt(split[1]);
                while (move > 0) {
                    cur = cur.prev;
                    move--;
                }
            }
            else if (split[0].equals("D")) {
                int move = Integer.parseInt(split[1]);
                while (move > 0) {
                    cur = cur.next;
                    move--;
                }
            }
            else if (split[0].equals("C")) {
                // stack에 넣고
                deleted.push(cur);

                // linkedlist 수정
                // if (cur.prev != null) cur.prev.next = cur.next;
                // if (cur.next != null) cur.next.prev = cur.prev;
                Node prevNode = cur.prev;
                Node nextNode = cur.next;

                if (prevNode != null) prevNode.next = nextNode;
                if (nextNode != null) nextNode.prev = prevNode;

                // 커서 이동
                if (cur.next != null) cur = cur.next;
                else cur = cur.prev;  // 이런거 예외처리좀
            }
            else {
                // 원상복구
                Node restored = deleted.pop();

                Node prevNode = restored.prev;
                Node nextNode = restored.next;

                if (prevNode != null) {
                    prevNode.next = restored;
                    restored.prev = prevNode;
                }
                if (nextNode != null) {
                    nextNode.prev = restored;
                    restored.next = nextNode;
                }
            }
            // System.out.println("stack size : " + stack.size());
        }

        Set<Integer> deletedSet = new HashSet<>();
        while (!deleted.isEmpty()) {
            deletedSet.add(deleted.pop().idx);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(deletedSet.contains(i) ? "X" : "O");
        }

        return sb.toString();
    }
}