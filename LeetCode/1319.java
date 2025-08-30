

/**
 * title : Number of Operations to Make Network Connected
 * date : 2025-08-30
 */
class Solution {
    public int makeConnected(int n, int[][] connections) {
        // 기존 연결선을 뽑아서 다른 컴퓨터 두 대 연결 -> ST만드는 최소 횟수 구하기
        // 기존 연결선 사이클 감지 -> 뽑을 수 있는 연결선 세기 -> 모두 합칠 수 있는지 확인

        // 컴퓨터 0 ~ n-1
        int[] parents = new int[n];
        for (int i = 0; i < n; i++) parents[i] = i;

        int canExtract = 0;
        int connectCnt = 0;
        for (int i = 0; i < connections.length; i++) {
            int c1 = connections[i][0];
            int c2 = connections[i][1];

            // 사이클 체크 - 이미 같은 집합이면 사이클
            int p1 = find(c1, parents);
            int p2 = find(c2, parents);

            if (p1 == p2) {
                canExtract++;
            } else {
                union(c1, c2, parents);
                connectCnt++;
            }
        }

        // 집합개수 count
        Set<Integer> diff = new HashSet<>();
        for (int i = 0; i < n; i++) {
            diff.add(find(i, parents));
        }

        // System.out.println("diff size : " + diff.size() + " connected size : " + connectCnt + " extract cnt : " + canExtract);

        if (diff.size() - 1 > canExtract) {
            return -1;
        } else {
            return (diff.size() - 1);
        }
    }

    private void union(int x, int y, int[] parents) {
        int px = find(x, parents);
        int py = find(y, parents);
        // *** parents[y] 아니고 py에 대입
        if (px != py) parents[py] = px;
    }

    private int find(int i, int[] parents) {
        if (parents[i] != i) {
            parents[i] = find(parents[i], parents);
        }
        return parents[i];
    }
}