/**
 author : https://github.com/daeunkwak
 title : 접미사 배열
 description : 문자열, 정렬
 date : 2022-07-10
 */

import java.io.*;
import java.util.Arrays;

public class BOJ_11656 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        String[] arr = new String[s.length()];
        for(int i = 0; i < s.length(); i++){
            arr[i] = s.substring(i, arr.length);
        }

        Arrays.sort(arr);

        for(int i = 0; i < s.length(); i++){
            System.out.println(arr[i]);
        }
    }
}
