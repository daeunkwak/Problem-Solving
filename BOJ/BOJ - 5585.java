import java.util.Scanner;

/**
 author : Kwak Daeun
 github : https://github.com/daeunkwak
 title : 거스름돈
 description : 그리디
 */

public class BOJ_5585 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.close();

        int[] coin = {500, 100, 50, 10, 5, 1};
        N = 1000 - N;
        int cnt = 0;

        for(int i = 0; i < 6; i++){
            if (N / coin[i] > 0){
                cnt += N / coin[i];
                N = N % coin[i];
            }
        }

        System.out.println(cnt);
    }

}