
import java.io.IOException;
import java.util.Scanner;

/**
 author : Kwak Daeun
 github : https://github.com/daeunkwak
 title : 아기 석환 두루루 뚜루
 description :
 */

public class BOJ_15947 {
    public static void main(String[] args) throws IOException {
        String lyrics = "baby sukhwan tururu turu very cute tururu turu in bed tururu turu baby sukhwan";
        String[] arr = lyrics.split(" ");

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.close();

        int rep = N / 14;
        int rest = N % 14;

        String cur_str = arr[rest - 1];

        if ( cur_str.contains("tururu")){
            if (rep >= 3) {
                System.out.println("tu+" + "ru*" + (rep + 2));
            }else{
                System.out.println(arr[rest - 1] + addRuru(rep));
            }
        }else if ( cur_str.contains("turu")){
            if (rep >= 4) {
                System.out.println("tu+" + "ru*" + (rep + 1));
            }else{
                System.out.println(arr[rest - 1] + addRuru(rep));
            }
        }else{
            System.out.println(arr[rest - 1]);
        }
    }

    public static String addRuru(int rep){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < rep; i++){
            sb.append("ru");
        }
        return sb.toString();
    }
}
