import java.io.*;
import java.util.*;


/**
 * 1초 일 때 입력의 최대 크기
 * <p>
 * O(N) : 약 1억
 * O(N^2) : 약 1만
 * O(N^3) : 약 500
 * O(2^N) : 약 20
 * O(N!) : 약 10
 */

public class Main {

    private static BufferedWriter bw;
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N, M, A, B, C, R, F, S, G, U, D, T, H, W, sum, answer = 0;
    private static char[][] area;
    private static int[][] dist;
    private static Queue<Point> q;
    private static int[] dx = new int[]{0, 0, -1, 1};
    private static int[] dy = new int[]{-1, 1, 0, 0};

    private static boolean[][] visited;
    private static boolean[][] check;


    private static class Point {
        int x;
        int y;
        
        int w;

        public Point(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }
    
    static ArrayList<Point> arr;

    public static void main(String[] args) throws IOException {

        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        T = Integer.valueOf(st.nextToken());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            H = Integer.valueOf(st.nextToken());
            W = Integer.valueOf(st.nextToken());

            area = new char[H + 2][W + 2];
            dist = new int[H + 2][W + 2];
            arr = new ArrayList<>();

            int result = Integer.MAX_VALUE;

            arr.add(new Point(0, 0, 0));

            for (int j = 1; j <= H; j++) {
                char[] chars = br.readLine().toCharArray();
                for (int k = 1; k <= W; k++) {
                    area[j][k] = chars[k - 1];
                    if (area[j][k] == '$') {
                        arr.add(new Point(k, j, 0));
                    }
                }
            }

            bfs(0);
            bfs(1);
            bfs(2);

            for(int j=0;j<H + 2;j++){
                for(int k=0;k<W + 2;k++){
                    int d = dist[j][k];
                    if(area[j][k] == '*') continue;
                    if(area[j][k] == '#') d -= 2;
                    result = Math.min(result, d);
                }
            }

            bw.write(result + "\n");
        }
        bw.flush();
        bw.close();
    }
    static void bfs(int idx) {
        q = new LinkedList<>();
        Point s_p = arr.get(idx);
        q.add(s_p);
        visited = new boolean[H + 2][W + 2];
        visited[s_p.y][s_p.x] = true;

        while(!q.isEmpty()){
            Point p = q.remove();
            
            int w = p.w;

            for(int i=0;i<4;i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(ny >=0 && nx >= 0 && ny < H + 2 && nx < W + 2){
                    if(area[ny][nx] == '*') continue;

                    if(!visited[ny][nx]){
                        int nw = w;
                        if(area[ny][nx] == '#') nw++;
                        dist[ny][nx] += nw;
                        q.add(new Point(nx, ny, nw));
                        visited[ny][nx] = true;
                    }
                }
            }
        }
    }
}
