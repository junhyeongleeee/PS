package javaCode.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class _2636 {

    static public class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    static public BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static public StringTokenizer st;
    static public int[][] arr;      // <= 100
    static public boolean[][] visited;
    static public ArrayDeque<Node> queue;

    static public int[] dx = new int[]{-1, 1, 0, 0};
    static public int[] dy = new int[]{0, 0, -1, 1};

    static public int N;
    static public int M;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        int cheese = 0;
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 1) {
                    cheese++;
                }
            }
        }

        int answer2 = 0;
        int answer1 = 0;
        while(true) {
            visited = new boolean[N][M];

            answer1 = checked();
            if (answer1 != 0) {
                break;
            }
            dfs(new Node(0, 0));
            answer2++;
        }
        System.out.println(answer2);
        System.out.println(answer1);
    }

    private static int checked() {
        int count = 0;
        for (int i=0;i<N;i++) {
            for (int j=0;j<M;j++) {
                if (arr[i][j] == 2) {
                    arr[i][j] = 0;
                    count++;
                }
            }
        }

        boolean flag = false;
        for (int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if (arr[i][j] == 1) {
                    flag = true;
                    break;
                }
            }
        }

        if(flag) {
            return 0;
        } else {
            return count;
        }
    }

    public static void dfs(Node node) {
        for(int i=0;i<4;i++) {
            int nx = node.x + dx[i];
            int ny = node.y + dy[i];

            if (nx < 0 || ny < 0 || nx > M - 1 || ny > N - 1) continue;
            if (visited[ny][nx]) continue;
            visited[ny][nx] = true;

            if (arr[ny][nx] == 1) {
                arr[ny][nx] = 2;
            }
            else {
                dfs(new Node(nx, ny));
            }
        }
    }
}
