
import java.util.*;

/**
 * title : 0 만들기
 * date : 2025-07-29
 */
public class boj7490 {
    private static List<String> exprs;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int i = 0; i < T; i++) {
            exprs = new ArrayList<>();
            int N = sc.nextInt();
            search("1", 2, N);

            Collections.sort(exprs);
            for (String exp : exprs) {
                System.out.println(exp);
            }
            System.out.println();
        }

    }

    private static void search( String curExp, int i, int N) {
        if (i > N) {
            if (cal(curExp) == 0) {
                exprs.add(curExp);
            }
            return;
        }

        search(curExp + " " + i, i + 1, N);
        search(curExp + "+" + i, i + 1, N);
        search(curExp + "-" + i, i + 1, N);
    }

    private static int cal(String expr) {
        expr = expr.replaceAll("\\s+", "");
        int res = 0;
        int num = 0;
        char op = '+';

        for (int i = 0; i < expr.length(); i++) {
            char ch = expr.charAt(i);

            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            }

            if (!Character.isDigit(ch) || i == expr.length() - 1) {
                switch (op) {
                    case '+':
                        res += num;
                        break;
                    case '-':
                        res -= num;
                        break;
                }
                op = ch;
                num = 0;
            }
        }
        return res;
    }
}
