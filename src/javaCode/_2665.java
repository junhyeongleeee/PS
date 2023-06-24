package javaCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class _2665 {
    static public BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static public int[][] arr;
    static public int[][] counting;
    static public StringBuilder sb = new StringBuilder();

    static public PriorityQueue<Node> pq = new PriorityQueue<>((p1, p2) -> p1.crush - p2.crush);

    static public int[] dx = {-1, 1, 0, 0};
    static public int[] dy = {0, 0, -1, 1};

    static public class Node {
        int x;
        int y;
        int crush;

        public Node(int x, int y, int crush) {
            this.x = x;
            this.y = y;
            this.crush = crush;
        }
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        counting = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                counting[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < n; i++) {
            char[] data = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                arr[i][j] = data[j] - '0';
            }
        }

        System.out.println(bfs(n));
    }

    static public int bfs(int n) {
        pq.add(new Node(0, 0, 0));
        counting[0][0] = 0;
        int result = Integer.MAX_VALUE;

        while (!pq.isEmpty()) {
            Node p = pq.poll();
            int x = p.x;
            int y = p.y;
            int crush = p.crush;

            if (x == n - 1 && y == n - 1) {
                result = Math.min(result, crush);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];


                if (nx < 0 || ny < 0 || nx > n - 1 || ny > n - 1) continue;
                if (counting[ny][nx] <= counting[y][x]) continue;

                if (arr[ny][nx] == 0) {
                    counting[ny][nx] = counting[y][x] + 1;
                }
                else {
                    counting[ny][nx] = counting[y][x];
                }
                pq.add(new Node(nx, ny, counting[ny][nx]));
            }
        }

        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
