/*
 author : https://github.com/daeunkwak/Problem-Solving
 title : 공유기 설치
 description : 이분 탐색, 매개변수 탐색
 date : 2022-08-14
 */

package 이것저것;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110 {

    public static int[] house;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        house = new int[N];
        for(int i = 0; i < N; i++){
            house[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(house);

        // 이분탐색
        int left = 1;
        int right = house[N - 1] - house[0] + 1;

        while(left < right){
            int mid = (left + right) / 2;

            if (find(mid) < C){
                right = mid;
            }
            else{
                left = mid + 1;
            }
        }

        System.out.println(left - 1);
    }

    public static int find(int dis){
        int cnt = 1;
        int last = house[0];

        for(int i = 1; i < house.length; i++){
            int locate = house[i];

            // 직전의 거리보다 현재 거리가 최소 거리보다 클 때 갱신
            if (locate - last >= dis){
                cnt++;
                last = locate;
            }
        }
        return cnt;
    }
}
