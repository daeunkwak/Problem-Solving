import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * title : 부분합
 * date : 2025-03-07
 */

public class BOJ_1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int sum = 0;
        int answer = Integer.MAX_VALUE;

        while (end < n) {
            sum += arr[end];

            while (sum >= s) {
                answer = Math.min(answer, end - start + 1);
                sum -= arr[start];
                start++;
            }

            end++;
        }

        if (answer == Integer.MAX_VALUE) {
            System.out.println("0");
        } else {
            System.out.println(answer);
        }
    }
}