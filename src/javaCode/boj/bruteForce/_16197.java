package javaCode.boj.bruteForce;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 문제 : 두 동전
 * 해설 : 재귀
 */

public class _16197 {

    private static BufferedWriter bw;
    private static BufferedReader br;

    private static StringTokenizer st;

    private static int N, M, result = Integer.MAX_VALUE;
    private static int max = Integer.MIN_VALUE;
    private static int min = -1;

    private static int[][] arr;
    private static boolean[][] visited;

    private static int[] dx = new int[]{0, 0, -1, 1};
    private static int[] dy = new int[]{1, -1, 0, 0};

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void setPoint(Point point) {
            this.x = point.x;
            this.y = point.y;
        }

        public boolean comparePoint(Point p) {
            if (this.x == p.x && this.y == p.y) return true;
            else return false;
        }
    }

    private static ArrayList<Point> points;

    public static void main(String[] args) throws IOException {

        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.valueOf(st.nextToken());
        M = Integer.valueOf(st.nextToken());

        arr = new int[N][M];
        points = new ArrayList<>(2);

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            int j = 0;
            for (char ch : line.toCharArray()) {
                switch (ch) {
                    case 'o':       // 동전
                        arr[i][j] = 1;
                        points.add(new Point(j, i));
                        break;
                    case '.':       // 빈칸
                        arr[i][j] = 0;
                        break;
                    case '#':       // 벽
                        arr[i][j] = -1;
                        break;
                }
                j++;
            }
        }

        // 동전 2개, 움직임 횟수,
        go(0, points.get(0), points.get(1));

        bw.write(min + "");
        bw.flush();
        bw.close();
    }

    private static void go(int cnt, Point point1, Point point2){

        for (int i = 0; i < 4; i++) {
            int nx1 = point1.x + dx[i];
            int ny1 = point1.y + dy[i];
            int nx2 = point2.x + dx[i];
            int ny2 = point2.y + dy[i];
            Point n_point1 = new Point(nx1, ny1);
            Point n_point2 = new Point(nx2, ny2);

            boolean check1 = check(n_point1);
            boolean check2 = check(n_point2);

            // 11번 째부터는 -1
            if(cnt == 10) return;

            // 둘다 떨어졌을 때
            if(check1 && check2) continue;

            // 둘 중 하나만 떨어졌을 때
            if(check1 || check2) {
                if(min == -1) min = cnt + 1;
                else min = Math.min(min, cnt + 1);
                continue;
            }

            if(arr[ny1][nx1] == -1) n_point1.setPoint(point1);
            if(arr[ny2][nx2] == -1) n_point2.setPoint(point2);

            // 다음 위치가 서로 같을 때
            if(n_point1.comparePoint(n_point2)) continue;

            go(cnt + 1, n_point1, n_point2);
        }

    }

    private static boolean check(Point point) {
        if (point.x < 0 || point.x > M - 1 || point.y < 0 || point.y > N - 1)
            return true;
        else return false;
    }
}
