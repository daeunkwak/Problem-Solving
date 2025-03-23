import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * title : 멀티탭 스케줄링
 * date : 2025-03-17
 */
public class BOJ_1700 {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        // n개 구멍, k개 사용순서
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int[] arr = new int[k];
        for (int i = 0; i < k; i++) {
            arr[i] = scanner.nextInt();
        }

        // 멀티탭 이용현황
        int answer = 0;
        HashSet<Integer> plugged = new HashSet<>();
        for (int i = 0; i < k; i++) {
            // 빈 구멍이 있는 경우
            if (plugged.size() < n) {
                plugged.add(arr[i]);

                // 하나 뽑아야 하는 경우
            } else if (!plugged.contains(arr[i])) {
                int outDevice = 0;
                int farDistance = -1;

                // 구멍별로 앞으로 사용할 제품 순회
                for (int device : plugged) {
                    int thisDistance = k; // 앞으로 안쓴다 가정하고 시작
                    for (int j = i + 1; j < k; j++) {
                        if (device == arr[j]) {
                            thisDistance = j;
                            break;
                        }
                    }
                    if (farDistance < thisDistance) {
                        farDistance = thisDistance;
                        outDevice = device;
                    }
                }
                plugged.remove(outDevice);
                plugged.add(arr[i]);    // remove만 하고 add를 까먹음
                answer ++;
            }
        }
        System.out.println(answer);
    }
}