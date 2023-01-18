package javaCode.boj.softeer;

import java.io.*;
import java.util.StringTokenizer;

public class Virus {

    private static BufferedWriter bw;
    private static BufferedReader br;
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        long result = k;
        for (int i=0;i<n;i++) {
            result = (result * p) % 1000000007;
        }
        System.out.print(result);
    }
}
