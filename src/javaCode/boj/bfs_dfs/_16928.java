package javaCode.boj.bfs_dfs;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 문제 : 뱀과 사다리 게임
 * 해설 : DFS, 방문 배열을 boolean 대신 counting 개수로 만들어 해결
 */

public class _16928 {

    private static BufferedWriter bw;
    private static BufferedReader br;

    private static StringTokenizer st;

    private static int N, M,K,  result = Integer.MAX_VALUE;

    private static int[][] board;
    private static int[][] counting;

    private static int[] dx = new int[]{0, 0, -1, 1};
    private static int[] dy = new int[]{-1, 1, 0, 0};

    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[10][10];
        counting = new int[10][10];

        for(int i=0;i<N + M;i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.valueOf(st.nextToken());
            int go = Integer.valueOf(st.nextToken());
            num -= 1;
            int y = num / 10;
            int x = num % 10;

            board[y][x] = go;
        }
        go(1, 0);
        System.out.print(ans);

        bw.flush();
        bw.close();
    }

    static void go(int num, int cnt){

        int y = (num - 1) / 10;
        int x = (num - 1) % 10;

        if(num == 100) {
            ans = Math.min(ans, cnt);
            return;
        }
        else{
            counting[y][x] = cnt;

            for(int i=1;i<=6 && num + i <= 100;i++){
                int n_num = num + i;
                int ny = (n_num - 1) / 10;
                int nx = (n_num - 1) % 10;

                if(counting[ny][nx] == 0 || counting[ny][nx] > cnt + 1){
                    if(board[ny][nx] == 0) go(n_num, cnt + 1);
                    else {
                        counting[ny][nx] = cnt + 1;
                        go(board[ny][nx], cnt + 1);
                    }
                }
            }
        }
    }
}
