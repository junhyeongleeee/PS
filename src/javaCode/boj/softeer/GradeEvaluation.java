package javaCode.boj.softeer;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class GradeEvaluation
{

    private static BufferedWriter bw;
    private static BufferedReader br;
    private static StringTokenizer st;
    private static long[] competitions;

    public static void main(String args[]) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        competitions = new long[3];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int c1 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            int c3 = Integer.parseInt(st.nextToken());

            check(c1, c2, c3);
        }
        check(competitions[0], competitions[1], competitions[2]);

        bw.flush();
        bw.close();
    }

    static void check(long c1, long c2, long c3) throws IOException {
        int[] rank = new int[3];

        int count = 1;
        if(c1 < c2) count++;
        if(c1 < c3) count++;
        rank[0] = count;
        competitions[0] += c1;
        count = 1;

        if(c2 < c1) count++;
        if(c2 < c3) count++;
        rank[1] = count;
        competitions[1] += c2;
        count = 1;

        if(c3 < c1) count++;
        if(c3 < c2) count++;
        competitions[2] += c3;
        rank[2] = count;

        bw.write(rank[0] + " " + rank[1] + " " + rank[2] + "\n");
    }
}