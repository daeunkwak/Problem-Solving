import java.io.*;
import java.util.LinkedList;
import java.util.Stack;

public class BOJ_1406 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();
        int M = Integer.parseInt(br.readLine());

        Stack<String> leftSt = new Stack<String>();
        Stack<String> rightSt = new Stack<String>();

        String[] arr = str.split("");
        for(String s : arr) {
            leftSt.push(s);
        }

        for(int i = 0; i < M; i++){
            String cmd = br.readLine();

            switch (cmd.charAt(0)){
                case 'L' :
                    if(!leftSt.isEmpty())
                        rightSt.push(leftSt.pop());

                    break;
                case 'D' :
                    if(!rightSt.isEmpty())
                        leftSt.push(rightSt.pop());

                    break;
                case 'B' :
                    if(!leftSt.isEmpty()) {
                        leftSt.pop();
                    }
                    break;
                case 'P' :
                    char t = cmd.charAt(2);
                    leftSt.push(String.valueOf(t));
                    break;
                default :
                    break;
            }
        }
        while(!leftSt.isEmpty())
            rightSt.push(leftSt.pop());

        while(!rightSt.isEmpty())
            bw.write(rightSt.pop());

        bw.flush();
        bw.close();
    }
}
