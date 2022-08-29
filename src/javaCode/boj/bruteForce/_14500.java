package javaCode.boj.bruteForce;

import java.io.*;
import java.util.StringTokenizer;

public class _14500 {

    private static BufferedWriter bw;
    private static BufferedReader br;

    private static StringTokenizer st;

    private static int N, M, result = Integer.MIN_VALUE;
    private static int max = Integer.MIN_VALUE;
    private static int min = Integer.MAX_VALUE;

    private static int[][] arr;
    private static boolean[][] visited;

    private static int[] dx = new int[]{0, 0, -1, 1};
    private static int[] dy = new int[]{1, -1, 0, 0};

    public static void main(String[] args) throws IOException {

        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.valueOf(st.nextToken());
        M = Integer.valueOf(st.nextToken());

        arr = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.valueOf(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                nextPoint(j, i, arr[i][j], 0);
                visited[i][j] = false;
                check(i, j);
            }
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
    }

    private static void check(int y, int x) {

        if (x > 0 && x < M - 1) {
            int temp = arr[y][x] + arr[y][x - 1] + arr[y][x + 1];
            if (y > 0) {
                result = Math.max(result, temp + arr[y - 1][x]);
            }
            if (y < N - 1) {
                result = Math.max(result, temp + arr[y + 1][x]);
            }
        }
        if (y > 0 && y < N - 1) {
            int temp = arr[y][x] + arr[y - 1][x] + arr[y + 1][x];
            if (x - 1 >= 0) {
                result = Math.max(result, temp + arr[y][x - 1]);
            }
            if (x + 1 <= M - 1) {
                result = Math.max(result, temp + arr[y][x + 1]);
            }
        }
    }

    private static void nextPoint(int x, int y, int sum, int cnt) {

        if (cnt == 3) {
            if (result < sum) {
                result = sum;
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < M && ny < N) {
                if (!visited[ny][nx]) {
                    visited[ny][nx] = true;
                    nextPoint(nx, ny, sum + arr[ny][nx], cnt + 1);
                    visited[ny][nx] = false;
                }
            }
        }
    }

}
