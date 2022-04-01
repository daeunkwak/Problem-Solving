/**
 * author : Kwak Daeun
 * github : https://github.com/daeunkwak
 *
 * title : 쇠막대기
 * description :
 */

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.io.BufferedReader;

public class BOJ_10799 {
    public static void main(String[] args) throws IOException {
        Queue<Character> queue = new LinkedList<>();

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String input = bf.readLine();

        for(int i = 0; i < input.length(); i++){
            queue.add(input.charAt(i));
        }

        int sticks = 0;
        int res = 0;

        while(queue.size() > 0){
            // System.out.println(queue);
            // "("라고 해서 한참 고민함..;
            if (queue.poll() == '('){
                if (queue.peek() == ')'){
                    if (sticks == 0){
                        queue.poll();
                    }
                    else{
                        queue.poll();
                        res += sticks;
                    }
                }
                else{
                    sticks += 1;
                }
            }
            else{
                sticks -= 1;
                res += 1;
            }
            // System.out.println("res" + res);
        }
        System.out.println(res);
    }
}
