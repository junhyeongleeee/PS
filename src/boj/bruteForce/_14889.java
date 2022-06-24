package boj.bruteForce;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;


/**
 * 문제 : 스타트와 링크
 * 해설 : 순열 (next_permutation) 을 이용한 브루트 포스 문제
 */

public class _14889 {

    private static BufferedWriter bw;
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int[][] arr;
    private static ArrayList<Integer> teem;
    private static int N;

    public static void main(String[] args) throws IOException {

        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.valueOf(br.readLine());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.valueOf(st.nextToken());
            }
        }

        teem = new ArrayList<>(N);

        for (int i = 0; i < N/2; i++) {
            teem.add(0);
        }
        for (int i = N/2; i < N; i++) {
            teem.add(1);
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        // TODO
        do {
            int a_sum = 0;
            int b_sum = 0;
            ArrayList<Integer> a_teem = new ArrayList<>(N/2);
            ArrayList<Integer> b_teem = new ArrayList<>(N/2);

            for(int i=0;i<N;i++){
                if(teem.get(i) == 0)
                    a_teem.add(i);
                else
                    b_teem.add(i);
            }

            for(int i=0;i<N/2;i++){
                for(int j=0;j<N/2;j++){
                    if(i==j) continue;
                    a_sum += arr[a_teem.get(i)][a_teem.get(j)];
                    b_sum += arr[b_teem.get(i)][b_teem.get(j)];
                }
            }

            int result = Math.abs(a_sum - b_sum);

            if(min > result){
                min = result;
            }

        } while (next_permutation());

        bw.write(min + "");

        bw.flush();
        bw.close();
    }

    public static boolean next_permutation() {
        int size = teem.size();
        int i = size - 1;

        // TODO 교환할 위치 (i-1) 찾기
        while (i > 0 && teem.get(i - 1) >= teem.get(i)) --i;

        if (i == 0) return false;

        // TODO 교환할 인덱스 (j) 찾기
        int j = size - 1;
        while (teem.get(i - 1) >= teem.get(j)) --j;

        // TODO 교환 (i-1, j)
        int temp = teem.get(i - 1);
        teem.set(i - 1, teem.get(j));
        teem.set(j, temp);

        int k = size - 1;

        // TODO (i 부터 k 까지 오름차순 교환)
        while (i < k) {
            temp = teem.get(i);
            teem.set(i, teem.get(k));
            teem.set(k, temp);
            ++i;
            --k;
        }
        return true;
    }
}
