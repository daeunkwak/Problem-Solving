import java.io.*;
import java.util.*;

/**
 * title : 택배
 * date : 2025-03-20
 */
public class BOJ_8980 {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();  // 마을 수
        int C = scanner.nextInt();  // 트럭의 용량
        int M = scanner.nextInt();  // 박스 정보 개수

        int[][] boxInfo = new int[M][3];
        for (int i = 0; i < M; i++) {
            boxInfo[i][0] = scanner.nextInt();
            boxInfo[i][1] = scanner.nextInt();
            boxInfo[i][2] = scanner.nextInt();
        }

        // 알잘딱 정렬 >> 그리디
        Arrays.sort(boxInfo, (a, b) -> {
            if (a[1] != b[1]) {
                return a[1] - b[1];
            } else {
                return a[0] - b[0];
            }
        });

        int[] town = new int[N];
        Arrays.fill(town, C);
        int answer = 0;

        for (int i = 0; i < M; i++) {
            // 정렬하고 시작하므로,
            // 다음에 방문할 마을이 이전에 방문한 마을보다 박스 개수가 더 조금 남아있을 수 없음
            int availableBox = boxInfo[i][2];
            for (int j = boxInfo[i][0]; j < boxInfo[i][1]; j++) {
                availableBox = Math.min(availableBox, town[j]);
                town[j] -= availableBox;
            }
            answer += availableBox;
        }

        System.out.println(answer);

    }

}