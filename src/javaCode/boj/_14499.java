package javaCode.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _14499 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int[] dx = new int[]{1, -1, 0, 0};
    private static int[] dy = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        int[] command = new int[k];

        for (int i = 0; i < k; i++) {
            command[i] = Integer.parseInt(st.nextToken());
        }

        // 동 1, 서 2, 북 3, 남 4

        // 이동한 칸의 수가 0이면, 주사위 바닥면에 쓰여 있는 수가 칸에 복사
        // `` 아니면, 칸의 수가 주사위 바닥면으로 복사

        // 주사위 윗면을 이동할 때마다 출력
        // 가장 처음 주사위에는 모든 면이 0임.

        int[] horizon = new int[]{0, 0, 0, 0};
        int[] vertical = new int[]{0, 0, 0, 0};

        // 주사위 윗면
        int hp = 1;
        int hb = 3;
        int vp = 1;
        int vb = 3;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < k; i++) {
            int c = command[i] - 1;

            int nx = x + dx[c];
            int ny = y + dy[c];

            if (nx < 0 || ny < 0 || nx > m - 1 || ny > n - 1) continue;

            x = nx;
            y = ny;

            int num = arr[ny][nx];

            // 주사위 set
            if (c == 0) {
                hp = plus(hp);
                hb = plus(hb);
                vertical[vp] = horizon[hp];
                vertical[vb] = horizon[hb];
            }else if (c == 1) {
                hp = minus(hp);
                hb = minus(hb);
                vertical[vp] = horizon[hp];
                vertical[vb] = horizon[hb];
            }else if(c == 2) {
                vp = plus(vp);
                vb = plus(vb);
                horizon[hp] = vertical[vp];
                horizon[hb] = vertical[vb];
            }else if(c == 3) {
                vp = minus(vp);
                vb = minus(vb);
                horizon[hp] = vertical[vp];
                horizon[hb] = vertical[vb];
            }

            if (num == 0) {
                arr[ny][nx] = horizon[hb];
            }else {
                horizon[hb] = arr[ny][nx];
                vertical[vb] = arr[ny][nx];
                arr[ny][nx] = 0;
            }

            sb.append(horizon[hp]).append("\n");
        }

        System.out.println(sb);
    }

    public static int minus(int p) {
        return p - 1 < 0 ? 3 : p - 1;
    }

    public static int plus(int p) {
        return (p + 1) % 4;
    }
}


