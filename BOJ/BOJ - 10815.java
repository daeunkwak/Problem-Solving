/**
 author : Kwak Daeun
 github : https://github.com/daeunkwak
 title : 숫자 카드
 description : 정렬, 이분 탐색
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10815 {
    public static int N, M;
    static int[] arr_N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr_N = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            arr_N[i] = Integer.parseInt(st.nextToken());
        }
        // 오름차순 정렬
        Arrays.sort(arr_N);

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        // binary search
        for (int i = 0; i < M; i++){
            // 탐색 -> 0, 1 출력
            if(binarySearch(Integer.parseInt(st.nextToken()), 0, N - 1)){
                sb.append(1 + " ");
            } else{
                sb.append(0 + " ");
            }
        }
        System.out.println(sb);
    }

    public static boolean binarySearch(int key, int low, int high){
        int mid;

        if(low <= high) {
            mid = (low + high) / 2;

            if(key == arr_N[mid]) { // 탐색 성공
                return true;
            } else if(key < arr_N[mid]) {
                // 왼쪽 부분 arr[0]부터 arr[mid-1]에서의 탐색
                return binarySearch(key ,low, mid-1);
            } else {
                // 오른쪽 부분 - arr[mid+1]부터 arr[high]에서의 탐색
                return binarySearch(key, mid+1, high);
            }
        }
        return false; // 탐색 실패
    }
}
