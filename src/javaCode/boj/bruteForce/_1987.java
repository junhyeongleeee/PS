package javaCode.boj.bruteForce;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 문제 : 알파벳
 * 해설 : 백 트래킹
 */


public class _1987 {
    private static BufferedWriter bw;
    private static BufferedReader br;

    private static StringTokenizer st;

    private static int N, M, result = Integer.MAX_VALUE, R, C;

    private static int max = Integer.MIN_VALUE;
    private static int min = Integer.MAX_VALUE;

    private static char[][] alp;

    private static int[] dx = new int[]{0, 0, -1, 1};
    private static int[] dy = new int[]{-1, 1, 0, 0};

    private static boolean[][] visited;

    private static boolean[] check;

    public static void main(String[] args) throws IOException {

        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        R = Integer.valueOf(st.nextToken());
        C = Integer.valueOf(st.nextToken());

        alp = new char[R][C];

        check = new boolean[26];

        for(int i=0;i<R;i++){
            char[] input = br.readLine().toCharArray();
            for(int j=0;j<C;j++){
                alp[i][j] = input[j];
            }
        }

        go(0, 0, 0);

        bw.write(max + 1 + "");
        bw.flush();
        bw.close();
    }

    private static void go(int x, int y, int cnt) {

//        System.out.println(x + " " + y + " " + cnt);

        check[alp[y][x] - 65] = true;
        max = Math.max(max, cnt);

        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >=0 && ny >= 0 && nx < C && ny < R){
                if(!check[alp[ny][nx] - 65]) {
                    go(nx, ny, cnt + 1);
                    check[alp[ny][nx] - 65] = false;
                }
            }
        }
    }
}
