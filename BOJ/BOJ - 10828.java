/**
 * author : https://github.com/daeunkwak
 * date : 2022-01-04
 * title : 스택
 * description : 자료구조, 스택
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;


public class BOJ_10828 {

    public static void main(String[] args) throws IOException {

        int N;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String S = st.nextToken();

            switch (S){
                case "push" :
                    int B = Integer.parseInt(st.nextToken());
                    stack.push(B);
                    break;

                case "pop" :
                    if(stack.size() == 0){
                        System.out.println(-1);
                    }else{
                        System.out.println(stack.pop());
                    }
                    break;

                case "size" :
                    System.out.println(stack.size());
                    break;

                case "empty" :
                    if(stack.size() == 0){
                        System.out.println(1);
                    }else{
                        System.out.println(0);
                    } break;

                case "top" :
                    if(stack.size() == 0){
                        System.out.println(-1);
                    }else{
                        System.out.println(stack.peek());
                    }
                    break;

            }

        }

    }
}
