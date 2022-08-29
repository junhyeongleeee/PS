package javaCode.boj.bruteForce;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 문제 : N_QUEEN
 * 해설 : 재귀
 */

public class _9663 {
    private static BufferedWriter bw;
    private static BufferedReader br;

    private static StringTokenizer st;

    private static int N, M, result = Integer.MAX_VALUE;
    private static boolean[] check_col;
    private static boolean[] check_dig_r;
    private static boolean[] check_dig_l;
    public static void main(String[] args) throws IOException {

        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.valueOf(br.readLine());

        check_col = new boolean[15];
        check_dig_l = new boolean[40];
        check_dig_r = new boolean[40];

        bw.write(calc(0) + "");
        bw.flush();
        bw.close();
    }

    private static boolean check(int row, int col){

        if(check_col[col]) return false;
        if(check_dig_l[row + col]) return false;
        if(check_dig_r[row - col + N]) return false;

        return true;
    }

    private static int calc(int row){

        int result = 0;
        if(row == N) return 1;

        for(int col=0;col<N;col++){
            if(check(row, col)){
                check_col[col] = true;
                check_dig_l[row + col] = true;
                check_dig_r[row - col + N] = true;
                result += calc(row + 1);
                check_col[col] = false;
                check_dig_l[row + col] = false;
                check_dig_r[row - col + N] = false;
            }
        }

        return result;
    }
}
