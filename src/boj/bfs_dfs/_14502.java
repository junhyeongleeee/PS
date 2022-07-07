package boj.bfs_dfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 : 연구소
 * 해설 : DFS , BFS 동시에 사용.
 */


public class _14502 {


    private static BufferedWriter bw;
    private static BufferedReader br;

    private static StringTokenizer st;

    private static int N, M, answer = Integer.MIN_VALUE;
    private static int[][] area;

    private static int[][] tmp_area;

    private static Queue<Point> q;

    private static int[] dx = new int[]{0, 0, -1, 1};
    private static int[] dy = new int[]{-1, 1, 0, 0};

    private static boolean[][] visited;
    private static class Point{
        int x;
        int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {

        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.valueOf(st.nextToken());
        M = Integer.valueOf(st.nextToken());
        area = new int[N][M];
        tmp_area = new int[N][M];
        visited = new boolean[N][M];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                area[i][j] = Integer.valueOf(st.nextToken());
            }
        }
        dfs(0);

        System.out.print(answer);

        bw.flush();
        bw.close();
    }

    private static void dfs(int cnt){
        if(cnt == 3){
            bfs();
            return;
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(area[i][j] == 0){
                    area[i][j] = 1;
                    dfs(cnt + 1);
                    area[i][j] = 0;
                }
            }
        }
    }

    private static void bfs(){
        q = new LinkedList<>();

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                tmp_area[i][j] = area[i][j];
                if(tmp_area[i][j] == 2)
                    q.add(new Point(j, i));
            }
        }

        while(!q.isEmpty()){
            Point p = q.remove();

            for(int i=0;i<4;i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx >= 0 && ny >= 0 && nx < M && ny < N){
                    if(tmp_area[ny][nx] == 0){
                        tmp_area[ny][nx] = 2;
                        q.add(new Point(nx, ny));
                    }
                }
            }
        }

        int count = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(tmp_area[i][j] == 0)
                    count++;
            }
        }

        answer = Math.max(answer, count);
    }
}
