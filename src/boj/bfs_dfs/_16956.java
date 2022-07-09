package boj.bfs_dfs;

import java.io.*;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 : 늑대와 양
 * 해설 : 백준 강의에서 BFS 에 있었으나 , 구현문제였음.
 */

public class _16956 {

    private static BufferedWriter bw;
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N, M, A, B, C,R,  sum, answer = Integer.MIN_VALUE;
    private static char[][] area;
    private static int[] dx = new int[]{0, 0, -1, 1};
    private static int[] dy = new int[]{-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {

        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        R = Integer.valueOf(st.nextToken());
        C = Integer.valueOf(st.nextToken());

        area = new char[R][C];

        for(int i=0;i<R;i++){
            char[] chars = br.readLine().toCharArray();
            for(int j=0;j<C;j++){
                area[i][j] = chars[j];
            }
        }

        int result = solution1();
        System.out.println(result);

        if(result == 1) {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    bw.write(area[i][j]);
                }
                bw.write("\n");
            }
        }

        bw.flush();
        bw.close();
    }

    static int solution1(){
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(area[i][j] == '.') area[i][j] = 'D';
                else if(area[i][j] == 'W') {
                    for(int k=0;k<4;k++){
                        int ny = i + dy[k];
                        int nx = j + dx[k];

                        if(ny >=0 && nx >= 0 && ny < R && nx < C){
                            if(area[ny][nx] == 'S') return 0;
                        }
                    }
                }
            }
        }

        return 1;
    }
}
