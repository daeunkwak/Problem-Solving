
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * title : List of Unique Numbers
 * date : 2025-08-02
 */
public class boj13144 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] numb = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            numb[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        long cnt = 0;

        Map<Integer, Integer> map = new HashMap<>();

        for (int end = 0; end < N; end++) {
            map.put(numb[end], map.getOrDefault(numb[end], 0) + 1);

            while (map.get(numb[end]) > 1) {
                map.put(numb[start], map.get(numb[start]) - 1);
                if (map.get(numb[start]) == 0) {
                    map.remove(numb[start]);
                }
                start++;
            }

            // start부터 end까지의 모든 부분수열이 유니크함
            // [start,end], [start+1,end], [start+2,end], ..., [end,end]
            cnt += (end - start + 1);
        }

        System.out.println(cnt);
    }
}
