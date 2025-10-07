import java.util.*;

/**
 * title : 외벽 점검
 * date : 2025-10-07
 */
class Solution {

    public int solution(int n, int[] weak, int[] dist) {
        // 1시간만 점검
        // 투입 위치는 자유
        // 그리디 - dist가 큰 친구부터 투입 > 반례나옴

        int weakLen = weak.length;
        int friendCnt = dist.length;

        // dist 배열을 내림차순으로 정렬
        Arrays.sort(dist);
        for (int i = 0; i < dist.length / 2; i++) {
            int temp = dist[i];
            dist[i] = dist[dist.length - 1 - i];
            dist[dist.length - 1 - i] = temp;
        }

        int answer = friendCnt + 1;

        // 친구를 1명씩 늘려가면서 확인
        for (int friendNum = 1; friendNum <= friendCnt; friendNum++) {
            // 투입 순서 순열 생성
            List<int[]> distPerms = new ArrayList<>();
            getPerms(dist, friendNum, new boolean[friendCnt], new ArrayList<>(), distPerms);

            for (int start = 0; start < weakLen; start++) {
                for (int[] perm : distPerms) {
                    if (canCoverClockwise(n, weak, start, perm)) {
                        answer = Math.min(answer, friendNum);
                    }
                }
            }

            if (answer <= friendNum) {
                return answer;
            }
        }

        return answer > friendCnt ? -1 : answer;
    }

    private boolean canCoverClockwise(int n, int[] weak, int start, int[] friends) {
        int weakLen = weak.length;

        List<Integer> targets = new ArrayList<>();
        for (int i = 0; i < weakLen; i++) {
            int idx = (start + i) % weakLen;
            int pos = weak[idx];

            // 시작점보다 작으면 한 바퀴 돈 것
            if (i > 0 && pos <= weak[start]) {
                pos += n;
            }
            targets.add(pos);
        }

        int friendIdx = 0;
        int position = targets.get(0) + friends[friendIdx];  // 커버 범위

        for (int i = 1; i < targets.size(); i++) {
            // 현재 친구가 커버 못하면
            if (targets.get(i) > position) {
                friendIdx++;

                if (friendIdx >= friends.length) {
                    return false;
                }

                position = targets.get(i) + friends[friendIdx];
            }
        }

        return true;
    }

    private void getPerms(int[] dist, int size, boolean[] used, List<Integer> cur, List<int[]> result) {
        if (cur.size() == size) {
            int[] perm = new int[size];
            for (int i = 0; i < size; i++) {
                perm[i] = cur.get(i);
            }
            result.add(perm);
            return;
        }

        for (int i = 0; i < dist.length; i++) {
            if (!used[i]) {
                used[i] = true;
                cur.add(dist[i]);
                getPerms(dist, size, used, cur, result);
                cur.remove(cur.size() - 1);
                used[i] = false;
            }
        }
    }
}