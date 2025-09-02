
/**
 * title : Find the Number of Ways to Place People I
 * date : 2025-09-02
 */
class Solution {
    public int numberOfPairs(int[][] points) {
        // A, B : A가 B보다 왼쪽 위 && 사각형 만들었을 때 포함되는 점 X

        Arrays.sort(points, (a, b) -> {
            if (a[0] == b[0]) return b[1] - a[1];
            else return a[0] - b[0];
        });

        int cnt = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                boolean flag = true;
                if (points[i][1] >= points[j][1]) {
                    for (int k = i + 1; k < j; k++) {
                        if (points[k][1] >= points[j][1] && points[k][1] <= points[i][1]) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) cnt++;
                }
            }
        }

        return cnt;
    }
}