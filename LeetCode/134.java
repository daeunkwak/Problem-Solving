
/**
 * title : Gas Station
 * date : 2025-09-19
 */
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // i > i + 1 station costs cost[i]
        // 시계방향 완주 가능 ? return idx : -1
        // gas[i]만큼 채우고 cost[i]만큼 사용

        // gas - cost 구하고 누적합이 계속 >= 0이어야함
        int[] gap = new int[gas.length];
        for (int i = 0; i < gas.length; i++) {
            gap[i] = gas[i] - cost[i];
        }

        int idx = -1;
        int i = 0;
        while (i < gas.length) {
            if (gap[i] < 0) {
                i++;
                continue;   // ** 여기도 최적화
            }

            int sum = 0;
            boolean flag = true;

            for (int j = 0; j < gas.length; j++) {
                sum += gap[(i + j) % gas.length];
                if (sum < 0) {
                    flag = false;
                    i = i + j;  // *** 핵심 최적화
                    break;
                }
            }

            if (flag) {
                idx = i;
                break;
            }
        }

        return idx;
    }
}