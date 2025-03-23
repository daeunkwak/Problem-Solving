import java.io.*;
import java.util.*;

/**
 * title : 암호 만들기
 * date : 2025-03-20
 */
public class Main {
    static char[] pw;
    static int L;
    static int C;
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        L = scanner.nextInt();
        C = scanner.nextInt();
        scanner.nextLine();
        pw = new char[L];

        String characters = scanner.nextLine();
        String[] charArray = characters.split(" ");

        Arrays.sort(charArray); // 사전순 정렬
        dfs(0, 0, charArray);
    }

    private static void dfs(int length, int start, String[] charArray) {
        if (length == L) {
            if (isValid()){
                System.out.println(new String(pw));
            }
            return; // 종료!!!!!!!1
        }

        for (int i = start; i < C; i++) {
            pw[length] = charArray[i].charAt(0);
            dfs(length + 1, i + 1, charArray);
        }
    }

    private static boolean isValid() {
        int vowels = 0;
        int consonants = 0;

        for (int i = 0; i < L; i++) {
            char c = pw[i];
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vowels++;
            } else {
                consonants++;
            }
        }

        return vowels >= 1 && consonants >= 2;
    }
}