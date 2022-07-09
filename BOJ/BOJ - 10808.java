/**
 author : https://github.com/daeunkwak
 title : 알파벳 개수
 description : 구현, 문자열
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10808 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int[] cnt = new int[26];
        for(int i = 0; i < s.length(); i++) {
            cnt[s.charAt(i) - 97]++;
        }
        for(int i = 0; i < 26; i++) {
            System.out.print(cnt[i]+" ");
        }
    }
}
