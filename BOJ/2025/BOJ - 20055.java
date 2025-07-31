
import java.util.*;


/**
 * title : 컨베이어 벨트 위의 로봇
 * date : 2025-07-31
 */
public class boj20055 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        int[] dur = new int[N * 2];
        for (int i = 0; i < N * 2; i++) {
            dur[i] = sc.nextInt();
        }

        boolean[] robot = new boolean[N];

        int step = 0;
        int curUpIdx = 0;
        int curDownIdx = N - 1;

        while (true) {
            step++;

            // 회전
            int temp = dur[2 * N - 1];
            for (int i = 2 * N - 1; i > 0; i--) {
                dur[i] = dur[i - 1];
            }
            dur[0] = temp;

            boolean tempRobot = robot[N - 1];
            for (int i = N - 1; i > 0; i--) {
                robot[i] = robot[i - 1];
            }
            robot[0] = tempRobot;
            if (robot[N - 1]) {
                robot[N - 1] = false;
            }


            // 이동하기
            for (int i = N - 2; i >= 0; i--) {
                if (robot[i]) {
                    if (!robot[i + 1] && dur[i + 1] > 0) {
                        robot[i] = false;
                        robot[i + 1] = true;
                        dur[i + 1]--;
                    }
                }
            }
            if (robot[N - 1]) {
                robot[N - 1] = false;
            }

            // 올리기
            if (dur[0] > 0) {
                robot[0] = true;
                dur[0]--;
            }

            int zeroCnt = 0;
            for (int d : dur) {
                if (d == 0) {
                    zeroCnt++;
                }
            }
            if (zeroCnt >= K) {
                break;
            }
        }

        System.out.println(step);
    }
}
