/**
 author : https://github.com/daeunkwak
 title : 카드
 description : 자료구조, 정렬, 해시를 사용한 집합과 맵
 */

import java.io.*;
import java.util.Arrays;

public class BOJ_11652 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] arr = new long[N];

        for(int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        int cnt = 1;
        int max = 1;
        int res = 0;
        Arrays.sort(arr);
        for(int i = 1; i < N; i++){
            if(arr[i] == arr[i-1]){
                cnt += 1;
            } else {
                cnt = 1;
            }
            if(cnt > max){
                max = cnt;
                res = i;
            }
        }
//        System.out.println(res);
        br.close();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(arr[res] + "\n");
        bw.flush();
        bw.close();
    }
}



