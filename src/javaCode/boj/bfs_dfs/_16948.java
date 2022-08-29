package javaCode.boj.bfs_dfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 : 데스 나이트
 * 해설 : DFS, 방문 배열을 boolean 대신 counting 개수로 만들어 해결
 */

public class _16948 {

    private static BufferedWriter bw;
    private static BufferedReader br;

    private static StringTokenizer st;

    private static int N, M, K, result = Integer.MAX_VALUE;

    private static int[][] dist;

    private static int[] d1 = new int[]{-2, -2, 0, 0, 2, 2};
    private static int[] d2 = new int[]{-1, 1, 2, -2, -1, 1};
    static int c1, c2;

    static Queue<Point> queue;

    static class Point {
        int p1;
        int p2;

        public Point(int p1, int p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
    }

    public static void main(String[] args) throws IOException {

        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.valueOf(st.nextToken());

        dist = new int[N][N];

        st = new StringTokenizer(br.readLine());

        int r1 = Integer.valueOf(st.nextToken());
        int r2 = Integer.valueOf(st.nextToken());
        c1 = Integer.valueOf(st.nextToken());
        c2 = Integer.valueOf(st.nextToken());

        System.out.print(go(r1, r2));

        bw.flush();
        bw.close();
    }

    public static int go(int p1, int p2) {

        queue = new LinkedList<>();
        queue.add(new Point(p1, p2));

        while (!queue.isEmpty()) {
            Point p = queue.peek();

            for(int i=0;i<6;i++){
                int np1 = p.p1 + d1[i];
                int np2 = p.p2 + d2[i];

                if(np1 >= 0 && np2 >= 0 && np1 < N && np2 < N){
                    if(np1 == c1 && np2 == c2) return dist[p.p1][p.p2] + 1;

                    if(dist[np1][np2] == 0){
                        queue.add(new Point(np1, np2));
                        dist[np1][np2] += dist[p.p1][p.p2] + 1;
                    }
                }
            }
            queue.remove();
        }

        return -1;
    }
}
