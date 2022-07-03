package boj.bruteForce;

import java.io.*;
import java.util.StringTokenizer;


/**
 * 문제 : 가르침
 * 해설 : 비트 마스크 활용
 */

public class _1062 {

    private static BufferedWriter bw;
    private static BufferedReader br;

    private static StringTokenizer st;

    private static int N, M,K,  result = Integer.MAX_VALUE;

    private static boolean[] checkArr;
    private static int[] words;

    public static void main(String[] args) throws IOException {

        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.valueOf(st.nextToken());
        K = Integer.valueOf(st.nextToken());

        checkArr = new boolean[26];
        words = new int[N];

        for(int i=0;i<N;i++) {
            String s = br.readLine();
            for(char c: s.toCharArray())
                words[i] |= (1 << c - 'a');
        }

        System.out.print(go(0, K, 0));

        bw.flush();
        bw.close();
    }

    static int count(int mask, int[] words) {
        int cnt = 0;
        for (int word : words) {
            if ((word & ((1<<26)-1-mask)) == 0) {
                cnt += 1;
            }
        }
        return cnt;
    }
    static int go(int index, int k, int mask) {
        if (k < 0) return 0;
        if (index == 26) {
            return count(mask, words);
        }
        int ans = 0;

        // 무조건 배움
        int t1 = go(index+1, k-1, mask | (1 << index));
        if (ans < t1) ans = t1;

        // 'a', 'n', 't', 'i', 'c' 를 안배우는 경우는 없다는 뜻, -> 다른 알파벳일 경우는 안배움.
        if (index != 'a'-'a' && index != 'n'-'a' && index != 't'-'a' && index != 'i'-'a' && index != 'c'-'a') {
            t1 = go(index+1, k, mask);
            if (ans < t1) ans = t1;
        }
        return ans;
    }
}
