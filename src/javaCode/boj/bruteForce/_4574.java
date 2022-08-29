package javaCode.boj.bruteForce;

import java.io.*;
import java.util.StringTokenizer;

public class _4574 {
    private static BufferedWriter bw;
    private static BufferedReader br;

    private static StringTokenizer st;

    private static int N, M, result = Integer.MAX_VALUE;

    private static int[][] sudoku;

    private static boolean[][] c;
    private static boolean[][] c2;
    private static boolean[][] c3;
    private static boolean[][] c4;

    public static void main(String[] args) throws IOException {

        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));

        c = new boolean[10][10];
        c2 = new boolean[10][10];
        c3 = new boolean[10][10];
        c4 = new boolean[10][10];

        String input = "";

        while((input = br.readLine()) != null){
            if(input.equals("0")) return;

            N = Integer.valueOf(input);

            sudoku = new int[9][9];

            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());

                int num1 = Integer.valueOf(st.nextToken());
                char[] point1 = st.nextToken().toCharArray();
                int row1 = point1[0] - 65;
                int col1 = point1[1] - 49;

                int num2 = Integer.valueOf(st.nextToken());
                char[] point2 = st.nextToken().toCharArray();
                int row2 = point2[0] - 65;
                int col2 = point2[1] - 49;

                sudoku[row1][col1] = num1;
                sudoku[row2][col2] = num2;

                c[row1][num1] = true;
                c[row2][num2] = true;
                c2[col1][num1] = true;
                c2[col2][num2] = true;
                c3[square(row1,col1)][num1] = true;
                c3[square(row2,col2)][num2] = true;

                c4[num1][num2] = true;
                c4[num2][num1] = true;
            }

            st = new StringTokenizer(br.readLine());

            for(int i=1;i<=9;i++){
                char[] point = st.nextToken().toCharArray();
                int row = point[0] - 65;
                int col = point[1] - 49;
                sudoku[row][col] = i;
            }

            /*for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(sudoku[i][j] + " ");
                }
                System.out.println();
            }*/
        }

        bw.flush();
        bw.close();
    }

    private static int square(int i, int j) {
        return (i / 3) * 3 + (j / 3);
    }
}
