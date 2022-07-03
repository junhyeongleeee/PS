import java.io.*;
import java.util.*;

public class Main {

    private static BufferedWriter bw;
    private static BufferedReader br;

    private static StringTokenizer st;

    private static int N, M,K,  result = Integer.MAX_VALUE;

    private static int[][] space;

    static class Bead{
        int x;
        int y;
        public Bead(){};
        public Bead(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    private static int[] dx = new int[]{0, 0, -1, 1};
    private static int[] dy = new int[]{-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {

        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.valueOf(st.nextToken());
        M = Integer.valueOf(st.nextToken());

        space = new int[N][M];

        Bead red = new Bead();
        Bead blue = new Bead();

        for(int i=0;i<N;i++){
            char[] input = br.readLine().toCharArray();
            for(int j=0;j<input.length;j++){
                switch (input[j]){
                    case '#':
                        space[i][j] = -1;
                        break;
                    case '.':
                        space[i][j] = 0;
                        break;
                    case 'O':
                        space[i][j] = 1;
                        break;
                    case 'B':
                        blue = new Bead(j, i);
                        break;
                    case 'R':
                        red = new Bead(j, i);
                        break;
                }
            }
        }

        System.out.print(go(red, blue, -1, 0));

        bw.flush();
        bw.close();
    }

    static int go(Bead r, Bead b, int prev_r, int cnt){

        int ans = -1;
        if(cnt == 11) return -1;

        for(int i=0;i<4;i++){
            int r_nx = r.x, r_ny = r.y, r_bx = b.x, r_by = b.y;
            int c = 0;
            int count = 0;
            if(prev_r != i) {
                if(space[r_ny + dy[i]][r_nx + dx[i]] == -1 && space[r_by + dy[i]][r_bx + dx[i]] == -1) continue;
                while (c < 2) {
                    if(space[r_ny][r_nx] == -1) c++;
                    else{
                        r_nx += dx[i];
                        r_ny += dy[i];
                    }
                    if(space[r_by][r_bx] == -1) c++;
                    else{
                        r_bx += dx[i];
                        r_by += dy[i];
                    }

                    if(space[r_by][r_bx] == 1) break;
                    if(space[r_ny][r_nx] == 1) return cnt + 1;
                }
                if(space[r_by][r_bx] == 1) continue;
                ans = go(new Bead(r_nx, r_ny), new Bead(r_bx, r_by), i, cnt + 1);
            }
        }
        return ans;
    }

}
