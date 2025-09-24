import java.util.*;

/**
 * title : 민트 초코 우유
 * date : 2025-09-23
 */
public class Main {

    static class Student {
        int r, c;
        int truth;
        String food;
        Student(int r, int c, int truth, String food) {
            this.r = r; this.c = c; this.truth = truth; this.food = food;
        }
    }

    // 음식 문자열 정규화: T < C < M 순으로 중복 없이 정렬
    static String norm(String s) {
        boolean t=false, c=false, m=false;
        for (int i=0;i<s.length();i++) {
            char ch = s.charAt(i);
            if (ch=='T') t=true; else if (ch=='C') c=true; else if (ch=='M') m=true;
        }
        StringBuilder sb = new StringBuilder();
        if (t) sb.append('T');
        if (c) sb.append('C');
        if (m) sb.append('M');
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int T = sc.nextInt();

        String[][] foods = new String[N][N];
        for (int i = 0; i < N; i++) {
            String line = sc.next();
            for (int j = 0; j < N; j++) {
                foods[i][j] = norm(String.valueOf(line.charAt(j)));
            }
        }

        int[][] truth = new int[N][N];
        for (int i=0;i<N;i++) for (int j=0;j<N;j++) truth[i][j] = sc.nextInt();

        int[] dr = {-1, 1, 0, 0};
        int[] dc = { 0, 0,-1, 1};

        for (int day = 0; day < T; day++) {
            // 아침
            for (int i=0;i<N;i++) for (int j=0;j<N;j++) truth[i][j]++;

            // 점심: 같은 음식 컴포넌트 대표 선정, 대표 +(size-1), 나머지 -1
            boolean[][] vis = new boolean[N][N];
            List<Student> leaders = new ArrayList<>();

            for (int r=0;r<N;r++) for (int c=0;c<N;c++) {
                if (vis[r][c]) continue;

                String f = foods[r][c];
                Queue<int[]> q = new LinkedList<>();
                List<int[]> group = new ArrayList<>();
                q.offer(new int[]{r,c});
                vis[r][c] = true;
                group.add(new int[]{r,c});

                while (!q.isEmpty()) {
                    int[] cur = q.poll();
                    int cr = cur[0], cc = cur[1];
                    for (int d=0; d<4; d++) {
                        int nr = cr + dr[d], nc = cc + dc[d];
                        if (nr<0||nr>=N||nc<0||nc>=N) continue;
                        if (vis[nr][nc]) continue;
                        if (!foods[nr][nc].equals(f)) continue; // 완전 동일한 음식만 그룹
                        vis[nr][nc] = true;
                        q.offer(new int[]{nr,nc});
                        group.add(new int[]{nr,nc});
                    }
                }

                // 대표 선정: truth 내림, r 오름, c 오름 (선형 스캔)
                int lr = group.get(0)[0], lc = group.get(0)[1];
                for (int k=1;k<group.size();k++) {
                    int gr = group.get(k)[0], gc = group.get(k)[1];
                    int a = truth[lr][lc], b = truth[gr][gc];
                    if (b > a || (b==a && (gr < lr || (gr==lr && gc < lc)))) { lr=gr; lc=gc; }
                }
                leaders.add(new Student(lr, lc, truth[lr][lc], f));

                // 대표 + (size-1), 나머지 -1
                truth[lr][lc] += group.size() - 1;
                for (int[] cell : group) {
                    int cr = cell[0], cc = cell[1];
                    if (cr==lr && cc==lc) continue;
                    truth[cr][cc] -= 1;
                }
            }

            // 저녁: 전파 — 정렬 (음식 길이 1→2→3, 같은 길이 내 truth 내림, r 오름, c 오름)
            leaders.sort((a,b)->{
                int la=a.food.length(), lb=b.food.length();
                if (la!=lb) return la-lb;
                int ta = truth[a.r][a.c], tb = truth[b.r][b.c]; // 현재 truth 기준
                if (ta!=tb) return tb-ta;
                if (a.r!=b.r) return a.r-b.r;
                return a.c-b.c;
            });

            boolean[][] done = new boolean[N][N]; // 전파함/전파당함 표식(당일 재전파 금지)

            for (Student s : leaders) {
                int r = s.r, c = s.c;
                if (done[r][c]) continue;

                int B0 = truth[r][c];
                int x  = B0 - 1;            // 간절함
                int d  = B0 % 4;            // 방향
                String f = s.food;

                truth[r][c] = 1;
                done[r][c] = true;

                while (x > 0) {
                    // *** 잘보이는 곳에서 모든 경우에 한번씩 더하고 검사
                    int nr = r + dr[d], nc = c + dc[d];
                    if (nr<0||nr>=N||nc<0||nc>=N) break;

                    if (!foods[nr][nc].equals(f)) {
                        if (x > truth[nr][nc]) {
                            // 강한 전파: 음식 교체, 대상 +1, x -= (대상+1)
                            foods[nr][nc] = f;
                            x -= (truth[nr][nc] + 1);
                            truth[nr][nc] += 1;
                            done[nr][nc] = true;
                        } else {
                            // *** 약한 전파: 무조건 합치기 (이미 관심 여부 검사 없음)
                            foods[nr][nc] = norm(foods[nr][nc] + f);
                            truth[nr][nc] += x;
                            x = 0;
                            done[nr][nc] = true;
                        }
                    }
                    // ***이동은 항상 루프 끝에서 1회
                    r = nr; c = nc;
                }
            }

            // 출력: 한 줄, **TCM TC TM CM M C T** 순
            long TCM=0, TC=0, TM=0, CM=0, Ms=0, Cs=0, Ts=0;
            for (int i=0;i<N;i++) for (int j=0;j<N;j++) {
                String s = foods[i][j]; // 항상 정규화 유지
                int v = truth[i][j];
                switch (s) {
                    case "TCM": TCM+=v; break;
                    case "TC":  TC +=v; break;
                    case "TM":  TM +=v; break;
                    case "CM":  CM +=v; break;
                    case "M":   Ms +=v; break;
                    case "C":   Cs +=v; break;
                    case "T":   Ts +=v; break;
                }
            }
            System.out.println(TCM+" "+TC+" "+TM+" "+CM+" "+Ms+" "+Cs+" "+Ts);
        }
    }
}
