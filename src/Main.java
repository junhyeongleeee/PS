import java.io.*;
import java.util.*;


/**
 *  1초 일 때 입력의 최대 크기
 *
 *  O(N) : 약 1억
 *  O(N^2) : 약 1만
 *  O(N^3) : 약 500
 *  O(2^N) : 약 20
 *  O(N!) : 약 10
 */


public class Main {

    private static BufferedWriter bw;
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N, M, A, B, C,sum, answer = Integer.MIN_VALUE;
    private static int[][] area;
    private static Queue<Point> q;
    private static int[] dx = new int[]{0, 0, -1, 1};
    private static int[] dy = new int[]{-1, 1, 0, 0};

    private static boolean[][] visited;

    private static class Point{
        int x;
        int y;
        int crash;
        public Point(int x, int y, int crash){
            this.x = x;
            this.y = y;
            this.crash = crash;
        }
    }
    public static void main(String[] args) throws IOException {

        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.valueOf(st.nextToken());
        M = Integer.valueOf(st.nextToken());

        area = new int[N][M];

        for(int i=0;i<N;i++){
            char[] chars = br.readLine().toCharArray();
            for(int j=0;j<M;j++){
                area[i][j] = chars[j] - '0';
            }
        }

        bw.flush();
        bw.close();
    }

    static int bfs(int x, int y){
        q = new LinkedList<>();
        q.add(new Point(0, 0, 0));

        while(!q.isEmpty()){
            Point p = q.remove();

            int crash = p.crash;

            for(int i=0;i<4;i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx >= 0 && ny >= 0 && nx < M && ny < N){
                    if(area[ny][nx] == 0){

                    }
                    if(crash == 0 && area[ny][nx] == 1){
                        q.add(new Point(nx, ny, 1));
                    }
                }
            }
        }

        return -1;
    }
}
