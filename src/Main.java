import java.io.*;
import java.util.*;

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

        bw.flush();
        bw.close();
    }
}
