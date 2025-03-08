import java.io.*;
import java.util.*;

/**
 * title : 회의실 배정
 * date : 2025-03-07
 */

public class BOJ_1931 {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] meetings = new int[n][2];
        for (int i = 0; i < n; i++) {
            meetings[i][0] = scanner.nextInt();
            meetings[i][1] = scanner.nextInt();
        }

        // 회의 시작시간 > 끝나는시간 오름차순 정렬
        Arrays.sort(meetings, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                return a[1] - b[1];
            }
        });

        int answer = 0;
        int start = 0;
        int end = 0;

        start = meetings[0][0];
        end = meetings[0][1];

        // 정렬했으니까 뒤로 갈수록 start는 무조건 더 커짐
        // start >= end가 될 때까지, end가 최소인 회의를 선택함
        // start >= end가 되면 해당 회의로 start, end 값 교체
        for (int idx = 1; idx < n; idx++) {
            if (meetings[idx][0] >= end) {
                start = meetings[idx][0];
                end = meetings[idx][1];
                answer++;
            } else {
                if (meetings[idx][1] < end) {
                    start = meetings[idx][0];
                    end = meetings[idx][1];
                }
            }

            // 끝날때 조건 잘 확인하기
            if (idx == n - 1) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}