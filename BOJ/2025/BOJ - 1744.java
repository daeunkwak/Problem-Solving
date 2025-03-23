import java.io.IOException;
import java.util.*;

/**
 * title : 수 묶기
 * date : 2025-03-18
 */
public class BOJ_1744.java {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();  // 수열의 크기

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        Arrays.sort(arr);

        int start = 0;
        int end = n - 1;
        int answer = 0;

        // 음수끼리 곱하기
        while (start < n - 1) {
            if (arr[start + 1] < 0) {
                answer += arr[start] * arr[start + 1];
                start += 2;
            } else if (arr[start] < 0 && arr[start + 1] == 0) {
                answer += 0;
                start += 2;
                break;
            } else {
                break;
            }
        }


        // 양수끼리 곱하기
        while (end > 0) {
            if (arr[end - 1] > 0) {
                if (arr[end] == 1 || arr[end-1] == 1) {
                    answer += arr[end] + arr[end - 1];
                } else {
                    answer += arr[end] * arr[end - 1];
                }
                end -= 2;
            } else if (arr[end] > 0 && arr[end] <= 0) {
                answer += arr[end];
                end -= 1;
                break;
            } else {
                break;
            }
        }

        // 남은 수 더하기
        for (int i = start; i <= end; i++) {
            answer += arr[i];
        }

        System.out.println(answer);

    }

}