import java.io.*;
import java.util.*;

/**
 * title : 공주님의 정원
 * date : 2025-03-07
 */

public class BOJ_2457 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] flowers = new int[n][4];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                flowers[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // flowers 배열 [start, end]로 바꾸기
        int[][] flowerDays = new int[n][2];
        for (int i = 0; i < n; i++) {
            // 피는 날 (start)
            flowerDays[i][0] = parseDays(flowers[i][0], flowers[i][1]);
            // 지는 날 (end)
            flowerDays[i][1] = parseDays(flowers[i][2], flowers[i][3]);
        }

        // 3월 1일과 11월 30일의 일수 계산
        int bloomStart = parseDays(3, 1);
        int bloomEnd = parseDays(12, 1);

        // 정렬 - 피는 날짜 순, 같으면 지는 날짜가 더 늦은 것부터
        Arrays.sort(flowerDays, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                return b[1] - a[1];
            }
        });

        int count = 0;
        int current = bloomStart;

        while (current < bloomEnd) {
            int maxEnd = current;
            boolean found = false;

            // 현재 날짜 이전에 피는 모든 꽃 검사 > 가장 늦게 지는 꽃 찾기
            for (int i = 0; i < n; i++) {
                if (flowerDays[i][0] <= current && flowerDays[i][1] > maxEnd) {
                    maxEnd = flowerDays[i][1];
                    found = true;
                }
            }

            // 아직 bloomEnd 까지 못왔는데 이어질 꽃을 못찾은 경우 0
            if (!found) {
                System.out.println(0);
                return;
            }

            count++;
            current = maxEnd;

            if (current >= bloomEnd) {
                System.out.println(count);
                return;
            }
        }

        System.out.println(0);
    }

    private static int parseDays(int month, int day) {
        int[] months = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int sum = 0;
        for (int i = 1; i < month; i++) {
            sum += months[i];
        }
        return sum + day;
    }
}