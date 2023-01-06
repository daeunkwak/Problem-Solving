/**
 * author : https://github.com/daeunkwak
 * date : 2022-01-06
 * title : 좌표 정렬하기
 * description : 정렬
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_11650 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Coordinate> list = new ArrayList<>();

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            Coordinate coordinate = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            list.add(coordinate);
        }

        list.sort(Comparator.comparing(Coordinate::getX).thenComparing(Coordinate::getY));

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){
            sb.append(list.get(i).getX()).append(" ").append(list.get(i).getY()).append('\n');
        }

        System.out.println(sb);
    }

    protected static class Coordinate{
        private int x;
        private int y;

        protected Coordinate(int x, int y){
            this.x = x;
            this.y = y;
        }

        protected int getX(){
            return this.x;
        }

        protected int getY(){
            return this.y;
        }

    }
}
