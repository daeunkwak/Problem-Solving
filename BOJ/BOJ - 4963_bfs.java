package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4963_bfs {
    static int w,h;
    static int map[][];
    static int dx[] = {0,0,-1,1,-1,-1,1,1};
    static int dy[] = {-1,1,0,0,-1,1,-1,1};
    static boolean isVisited[][];

    static Queue<Node> q = new LinkedList<>();

    static int island = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if(w ==0 && h == 0)
                break;

            map = new int[h][w];
            isVisited = new boolean[h][w];
            island = 0 ;

            for(int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i = 0; i < h; i++) {
                for(int j = 0; j < w; j++) {
                    if(map[i][j] ==1 && !isVisited[i][j]) {
                        isVisited[i][j] = true;
                        q.offer(new Node(i,j));
                        bfs();
                    }
                }
            }
            System.out.println(island);
            q.clear();

        }
    }

    static void bfs() {
        // 큐에 뭐라도 있으면
        while(!q.isEmpty()) {
            // 꺼낸다
            Node node = q.poll();

            for(int d = 0; d < 8; d++) {
                int nx = node.x + dx[d];
                int ny = node.y + dy[d];

                if(nx<0 || nx>=h || ny<0 || ny>=w || map[nx][ny] ==0 || isVisited[nx][ny]) continue; //배열 범위 벗어나면 그만두기

                //방문하지 않은 섬 방문처리 -> 큐에 넣기
                isVisited[nx][ny] = true;
                q.offer(new Node(nx,ny));
            }
        }
        island++;
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }
    }
}
