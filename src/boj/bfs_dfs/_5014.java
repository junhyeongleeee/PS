package boj.bfs_dfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _5014 {
    private static BufferedWriter bw;
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N, M, A, B, C, R, F, S, G, U, D, sum, answer = Integer.MIN_VALUE;
    private static char[][] area;
    private static int[][][] dist;
    private static Queue<Integer> q;
    private static int[] dx = new int[]{0, 0, -1, 1};
    private static int[] dy = new int[]{-1, 1, 0, 0};

    private static int[] visited;

    private static class Point {
        int x;
        int y;
        int crash;

        public Point(int x, int y, int crash) {
            this.x = x;
            this.y = y;
            this.crash = crash;
        }
    }

    public static void main(String[] args) throws IOException {

        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        F = Integer.valueOf(st.nextToken());
        S = Integer.valueOf(st.nextToken());
        G = Integer.valueOf(st.nextToken());
        U = Integer.valueOf(st.nextToken());
        D = Integer.valueOf(st.nextToken());

        visited = new int[F + 1];

        bfs();

        bw.flush();
        bw.close();
    }

    static void bfs() {
        q = new LinkedList<>();
        q.add(S);
        visited[S] = 1;

        if(S == G) {
            System.out.print(0);
            return;
        }

        while (!q.isEmpty()) {
            int s = q.remove();
            int u_s = s + U;
            int d_s = s - D;

            if(u_s == G || d_s == G){
                System.out.print(visited[s]);
                return;
            }

            if (u_s <= F && visited[u_s] == 0) {
                q.add(u_s);
                visited[u_s] = visited[s] + 1;
            }

            if (d_s > 0 && visited[d_s] == 0) {
                q.add(d_s);
                visited[d_s] = visited[s] + 1;
            }
        }

        System.out.print("use the stairs");
    }
}
