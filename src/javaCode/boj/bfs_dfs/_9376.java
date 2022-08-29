package javaCode.boj.bfs_dfs;

import java.io.*;
import java.util.*;

public class _9376 {

    private static BufferedWriter bw;
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N, M, A, B, C, R, F, S, G, U, D, T, H, W, sum, answer = 0;
    private static char[][] area;
    private static int[][] dist;
    private static Queue<Point> q;
    private static int[] dx = new int[]{0, 0, -1, 1};
    private static int[] dy = new int[]{-1, 1, 0, 0};

    private static boolean[][] visited;
    private static boolean[][] check;


    private static class Point {
        int x;
        int y;

        int w;

        public Point(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }

    static ArrayList<Point> arr;

    public static void main(String[] args) throws IOException {

        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        T = Integer.valueOf(st.nextToken());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            H = Integer.valueOf(st.nextToken());
            W = Integer.valueOf(st.nextToken());

            area = new char[H + 2][W + 2];
            dist = new int[H + 2][W + 2];
            arr = new ArrayList<>();

            int result = Integer.MAX_VALUE;

            arr.add(new Point(0, 0, 0));

            for (int j = 1; j <= H; j++) {
                char[] chars = br.readLine().toCharArray();
                for (int k = 1; k <= W; k++) {
                    area[j][k] = chars[k - 1];
                    if (area[j][k] == '$') {
                        arr.add(new Point(k, j, 0));
                    }
                }
            }

            bfs(0);
            bfs(1);
            bfs(2);

            for(int j=0;j<H + 2;j++){
                for(int k=0;k<W + 2;k++){
                    int d = dist[j][k];
                    if(area[j][k] == '*') continue;
                    if(area[j][k] == '#') d -= 2;
                    result = Math.min(result, d);
                }
            }

            bw.write(result + "\n");
        }
        bw.flush();
        bw.close();
    }
    static void bfs(int idx) {
        q = new LinkedList<>();
        Point s_p = arr.get(idx);
        q.add(s_p);
        visited = new boolean[H + 2][W + 2];
        visited[s_p.y][s_p.x] = true;

        while(!q.isEmpty()){
            Point p = q.remove();

            int w = p.w;

            for(int i=0;i<4;i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(ny >=0 && nx >= 0 && ny < H + 2 && nx < W + 2){
                    if(area[ny][nx] == '*') continue;

                    if(!visited[ny][nx]){
                        int nw = w;
                        if(area[ny][nx] == '#') nw++;
                        dist[ny][nx] += nw;
                        q.add(new Point(nx, ny, nw));
                        visited[ny][nx] = true;
                    }
                }
            }
        }

        for(int i=0;i<H + 2;i++){
            for(int j=0;j<W + 2;j++){
                System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    /*public static int[][] bfs(String[] a, int x, int y) {
        int n = a.length;
        int m = a[0].length();
        int[][] d = new int[n][m];
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                d[i][j] = -1;
            }
        }
        ArrayDeque<Pair> deque = new ArrayDeque<Pair>();
        deque.add(new Pair(x, y));
        d[x][y] = 0;
        while (!deque.isEmpty()) {
            Pair p = deque.poll();
            x = p.x;
            y = p.y;
            for (int k=0; k<4; k++) {
                int nx = x+dx[k];
                int ny = y+dy[k];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (d[nx][ny] != -1) continue;
                char c = a[nx].charAt(ny);
                if (c == '*') continue;
                if (c == '#') {
                    d[nx][ny] = d[x][y] + 1;
                    deque.addLast(new Pair(nx, ny));
                } else {
                    d[nx][ny] = d[x][y];
                    deque.addFirst(new Pair(nx, ny));
                }
            }
        }
        return d;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            sc.nextLine();
            String[] a = new String[n+2];
            for (int i=1; i<=n; i++) {
                a[i] = sc.nextLine();
                a[i] = "." + a[i] + ".";
            }
            n += 2;
            m += 2;
            a[0] = a[n-1] = "";
            for (int j=0; j<m; j++) {
                a[0] += ".";
                a[n-1] += ".";
            }
            int[][] d0 = bfs(a, 0, 0);
            int x1, y1, x2, y2;
            x1 = y1 = x2 = y2 = -1;
            for (int i=0; i<n; i++) {
                for (int j=0; j<m; j++) {
                    if (a[i].charAt(j) == '$') {
                        if (x1 == -1) {
                            x1 = i;
                            y1 = j;
                        } else {
                            x2 = i;
                            y2 = j;
                        }
                    }
                }
            }
            int[][] d1 = bfs(a, x1, y1);
            int[][] d2 = bfs(a, x2, y2);
            int ans = n*m;
            for (int i=0; i<n; i++) {
                for (int j=0; j<m; j++) {
                    char c = a[i].charAt(j);
                    if (c == '*') continue;
                    if (d0[i][j] == -1 || d1[i][j] == -1 || d2[i][j] == -1) continue;
                    int cur = d0[i][j] + d1[i][j] + d2[i][j];
                    if (c == '#') cur -= 2;
                    if (ans > cur) ans = cur;
                }
            }
            System.out.println(ans);
        }
    }*/

}