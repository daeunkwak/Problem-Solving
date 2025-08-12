package just.p_0812;

import java.util.*;

/**
 * title : 스카이라인 쉬운거
 * date : 2025-08-12
 */
public class boj1863 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][] buildings = new int[n][2];
        for (int i = 0; i < n; i++) {
            buildings[i][0] = sc.nextInt();
            buildings[i][1] = sc.nextInt();
        }

        Arrays.sort(buildings, (a, b) -> a[0] - b[0]);

        // set에 높이 저장
        // 중간에 0만나면 set초기화 -> 그만큼 cnt증가
        // n번째 건물 높이보다 큰 값은 set에서 제거 -> 그만큼 cnt 증가
        Set<Integer> set = new HashSet<>();

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int h = buildings[i][1];

            if (h == 0) {
                // 남아있는 활성 블록 모두 종료
                cnt += set.size();
                set.clear();
                continue;
            }

            // 1) 항상: 현재 높이보다 큰 값들 제거 + 카운트
            List<Integer> remove = new ArrayList<>();
            for (int height : set) {
                if (height > h) {
                    remove.add(height);
                    cnt++;
                }
            }
            for (int height : remove) {
                set.remove(height);
            }

            // 2) 그 다음: 현재 높이 추가
            // ***** 같은 높이가 이미 있으면 무시
            if (h > 0 && !set.contains(h)) {
                set.add(h);
            }
        }

        cnt += set.size();
        System.out.println(cnt);
    }
}
