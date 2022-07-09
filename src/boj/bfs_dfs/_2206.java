package boj.bfs_dfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


/**
 * 문제 : 벽 부수고 이동하기
 * 해설 : BFS, 평범한 최단거리 구하기 + 막힌 벽을 한번 뚫을 수 있는 조건 추가
 */

public class _2206 {

    static class Pair {
        int x, y, z;

        Pair(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    public static int[] dx = {1, -1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        int[][] a = new int[n][m];
        int[][][] d = new int[n][m][2];
        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < m; j++) {
                a[i][j] = s.charAt(j) - '0';
            }
        }
        d[0][0][0] = 1;
        Queue<Pair> q = new LinkedList<Pair>();
        q.offer(new Pair(0, 0, 0));
        while (!q.isEmpty()) {
            Pair p = q.remove();
            int x = p.x;
            int y = p.y;
            int z = p.z;
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (a[nx][ny] == 0 && d[nx][ny][z] == 0) {
                    d[nx][ny][z] = d[x][y][z] + 1;
                    q.offer(new Pair(nx, ny, z));
                }
                if (z == 0 && a[nx][ny] == 1 && d[nx][ny][z + 1] == 0) {
                    d[nx][ny][z + 1] = d[x][y][z] + 1;
                    q.offer(new Pair(nx, ny, z + 1));
                }
            }
        }
        if (d[n - 1][m - 1][0] != 0 && d[n - 1][m - 1][1] != 0) {
            System.out.println(Math.min(d[n - 1][m - 1][0], d[n - 1][m - 1][1]));
        } else if (d[n - 1][m - 1][0] != 0) {
            System.out.println(d[n - 1][m - 1][0]);
        } else if (d[n - 1][m - 1][1] != 0) {
            System.out.println(d[n - 1][m - 1][1]);
        } else {
            System.out.println(-1);
        }
    }

    /*static int bfs() {
        q = new LinkedList<>();
        q.add(new Main.Point(0, 0, 0));
        dist[0][0][0] = 1;

        while (!q.isEmpty()) {
            Main.Point p = q.remove();

            int crash = p.crash;

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < M && ny < N) {
                    if (area[ny][nx] == 0 && dist[ny][nx][crash] == 0) {
                        dist[ny][nx][crash] = dist[p.y][p.x][crash] + 1;
                        q.add(new Main.Point(nx, ny, crash));
                    }
                    if (crash == 0 && area[ny][nx] == 1 && dist[ny][nx][1] == 0) {
                        dist[ny][nx][1] = dist[p.y][p.x][0] + 1;
                        q.add(new Main.Point(nx, ny, 1));
                    }
                }
            }
        }
        if (dist[N - 1][M - 1][0] != 0 && dist[N-1][M - 1][1] != 0)
            return Math.min(dist[N - 1][M - 1][0], dist[N - 1][M - 1][1]);
        else if(dist[N - 1][M - 1][0] != 0)
            return dist[N - 1][M - 1][0];
        else if(dist[N - 1][M - 1][1] != 1)
            return dist[N - 1][M - 1][1];
        else return -1;
    }*/
}
