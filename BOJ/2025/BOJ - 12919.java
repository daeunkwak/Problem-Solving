
import java.util.*;

/**
 * title : A와 B 2
 * date : 2025-08-04
 */
public class boj12919 {
    private static int res = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String S = sc.next();
        String T = sc.next();

        dfs(S, T);  // 거꾸로 생각하기
        System.out.println(res);
    }

    private static void dfs(String S, String T) {
        if (T.length() == S.length()) {
            if (T.equals(S)) {
                res = 1;
            }
            return;
        }

        if (T.charAt(T.length() - 1) == 'A') {
            dfs(S, T.substring(0, T.length() - 1));
        }

        if (T.charAt(0) == 'B') {
            dfs(S, new StringBuilder(T.substring(1)).reverse().toString());
        }
    }
}
