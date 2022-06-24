import java.io.*;
import java.util.StringTokenizer;

import static java.lang.System.exit;

public class Main {

    private static BufferedWriter bw;
    private static BufferedReader br;

    private static StringTokenizer st;

    private static int N, M, result = Integer.MAX_VALUE;

    private static int[][] sudoku;

    private static boolean[][] c;
    private static boolean[][] c2;
    private static boolean[][] c3;

    public static void main(String[] args) throws IOException {

        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));

        N = 9;

        sudoku = new int[N][N];
        c = new boolean[10][10];
        c2 = new boolean[10][10];
        c3 = new boolean[10][10];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                sudoku[i][j] = Integer.valueOf(st.nextToken());

                if (sudoku[i][j] != 0) {
                    c[i][sudoku[i][j]] = true;
                    c2[j][sudoku[i][j]] = true;
                    c3[square(i, j)][sudoku[i][j]] = true;
                }
            }
        }

        go(0);

        bw.flush();
        bw.close();
    }

    private static int square(int i, int j) {
        return (i / 3) * 3 + (j / 3);
    }

    private static void go(int z) {
        if (z == 81) {
            // check
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(sudoku[i][j] + " ");
                }
                System.out.println();
            }

            exit(0);
        }

        // 좌표 구하기
        int x = z / N, y = z % N;

        if (sudoku[x][y] != 0) {
            go(z + 1);
        } else {
            // next
            for (int i = 1; i <= 9; i++) {
                if (!c[x][i] && !c2[y][i] && !c3[square(x, y)][i]) {
                    c[x][i] = c2[y][i] = c3[square(x, y)][i] = true;
                    sudoku[x][y] = i;
                    go(z + 1);
                    sudoku[x][y] = 0;
                    c[x][i] = c2[y][i] = c3[square(x, y)][i] = false;
                }
            }
        }
    }
}
