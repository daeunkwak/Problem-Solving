

/**
 * title : Merge Intervals
 * date : 2025-08-15
 */
class Solution {
    public int[][] merge(int[][] intervals) {
        // [1, 3] & [2, 6] -> [1, 6] 겹치면 합치기
        // 시작점 오름차순 정렬 -> 끝점과 다음 시작점 비교 -> 다음 시작점이 끝점보다 클 때까지 반복
        // -> 다음 시작점의 max값 갱신 -> answer에 추가 -> O(nlogn)

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> result = new ArrayList<>();
        int curStart = intervals[0][0];
        int curEnd = intervals[0][1];
        int idx = 0;

        while (idx < intervals.length) {
            curStart = intervals[idx][0];
            curEnd = intervals[idx][1];
            idx++;
            while (idx < intervals.length && intervals[idx][0] <= curEnd) {
                curEnd = Math.max(curEnd, intervals[idx][1]);
                idx++;
            }
            result.add(new int[]{curStart, curEnd});
        }

        return result.toArray(new int[result.size()][]);
    }
}