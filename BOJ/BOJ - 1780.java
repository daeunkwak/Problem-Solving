import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1780 {
    public static int cnt_minus = 0;
    public static int cnt_0 = 0;
    public static int cnt_plus = 0;
    public static int[][] arrr; //<-> arr

    public static boolean isDiff(int r, int c, int len) {
        int t = arrr[r][c];

        for(int i = r; i < r+len; i++) {
            for(int j = c; j < c+len; j++) {
                if(t != arrr[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void splitAndCheck(int r, int c, int len) {
        if (isDiff(r, c, len)){
            if (arrr[r][c] == -1){
                cnt_minus += 1;
            } else if (arrr[r][c] == 0){
                cnt_0 += 1;
            } else {
                cnt_plus += 1;
            }
        } else{
            int newLen = len/3;

            for(int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    splitAndCheck(r + newLen*i, c + newLen*j, newLen);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        arrr = new int[N][N];
        StringTokenizer st;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++){
                arrr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        splitAndCheck(0, 0, N);

        System.out.println(cnt_minus);
        System.out.println(cnt_0);
        System.out.println(cnt_plus);
    }
}

