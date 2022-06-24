package boj.bruteForce;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 문제 : 부분 수열의 합
 * 해설 : 재귀 (combination)
 */

public class _1182 {
    private static BufferedWriter bw;
    private static BufferedReader br;

    private static StringTokenizer st;

    private static int N, S, cnt = 0;

    private static int[] num;

    public static void main(String[] args) throws IOException {

        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        N = Integer.valueOf(st.nextToken());
        S = Integer.valueOf(st.nextToken());

        num = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            num[i] = Integer.valueOf(st.nextToken());
        }

        combination2(0, 0);

        if(S == 0) cnt -= 1;

        bw.write(cnt + "");
        bw.flush();
        bw.close();
    }
    private static void combination2(int index, int sum) {

        if (index == N) {
            if(sum == S)
                cnt++;
            return;
        }

        combination2(index + 1, sum + num[index]);
        combination2(index + 1, sum);
    }
}
