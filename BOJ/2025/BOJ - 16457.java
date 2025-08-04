
import java.util.*;

/**
 * title : 단풍잎 이야기
 * date : 2025-08-04
 */
public class boj16457 {
    private static int maxCnt;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   // 키 개수
        int m = sc.nextInt();   // 퀘스트 개수
        int k = sc.nextInt();   // 퀘스트 당 사용하는 스킬 수

        int[][] quests = new int[m][k];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < k; j++) {
                quests[i][j] = sc.nextInt();
            }
        }

        // 2n C n
        search(n, 1, new ArrayList<>(), quests);
        System.out.println(maxCnt);
    }

    private static void search(int n, int start, List<Integer> skills, int[][] quests) {
        if (skills.size() == n) {
            // 퀘스트 개수 비교
            boolean flag = true;
            int cnt = 0;
            for (int i = 0; i < quests.length; i++) {
                flag = true;
                for (int j = 0; j < quests[0].length; j++) {
                    if (!skills.contains(quests[i][j])) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    cnt++;
                }
            }
            maxCnt = Math.max(maxCnt, cnt);
            return;
        }

        for (int i = start; i <= 2 * n; i++) {
            skills.add(i);
            search(n, i + 1, skills, quests);
            skills.remove(skills.size() - 1);
        }
    }
}
