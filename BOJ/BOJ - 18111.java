/**
 author : Kwak Daeun
 github : https://github.com/daeunkwak

 title : 마인크래프트
 description : 구현, 브루트포스 알고리즘
 */

import java.io.*;

public class BOJ_18111 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // BufferedReader로 N, M, B 입력받기
        String[] num = br.readLine().split(" "); // -> IOExeption
        int N = Integer.parseInt(num[0]);
        int M = Integer.parseInt(num[1]);
        int B = Integer.parseInt(num[2]);

        // 밭의 크기만큼 배열 생성
        int[][] ground = new int[N][M];

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        // 배열 값 채우기
        for (int i = 0; i < ground.length; i++) {
            String[] groundRow = br.readLine().split(" ");

            for (int j = 0; j < M; j++) {
                int val = Integer.parseInt(groundRow[j]);
                // System.out.println("val : " + val);
                ground[i][j] = val;

                max = Math.max(max, val);
                min = Math.min(min, val);
            }
        }

        // 시간, 층
        int answerTime = Integer.MAX_VALUE;
        int answerHeight = -1;

        // 최소층 <-> 최대층
        for (int i = min; i <= max; i++) {
            int secc = 0;
            int inven = B;

            for (int j = 0; j < ground.length; j++) {
                for (int k = 0; k < ground[j].length; k++) {
                    int diff = ground[j][k] - i;

                    if (diff > 0) {     // 제거
                        secc += Math.abs(diff) * 2;
                        inven += Math.abs(diff);
                    } else if (diff < 0) {     // 추가
                        secc += Math.abs(diff);
                        inven -= Math.abs(diff);
                    }
                }
            }
            if (inven >= 0) {
                if (secc <= answerTime) {
                    answerTime = secc;
                    answerHeight = i;
                }
            }
        }
        bw.write(answerTime + " " + answerHeight);

        bw.flush();
        bw.close();
    }
}






















