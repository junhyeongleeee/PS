package boj.bruteForce;

import java.io.*;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * 문제 : 단어 수학
 * 해설 : 순열 (next_permutation) 을 이용한 브루트 포스 문제
 */

public class _1339 {

    private static Vector<String> word;
    private static Vector<Character> letters;
    private static Vector<Character> chars;

    private static Vector<Integer> d;

    private static BufferedWriter bw;
    private static BufferedReader br;

    private static int[] alpha;

    private static void main() throws IOException {
        int N;
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.valueOf(br.readLine());

        word = new Vector(N);
        letters = new Vector<>();

        alpha = new int[256];

        for (int i = 0; i < N; i++) {
            word.add(br.readLine());
            for (char x : word.get(i).toCharArray()) {
                letters.add(x);
            }
        }

        chars = new Vector<>();
        d = new Vector<>();
        for (int i = 0; i < letters.size(); i++) {
            if (letters.indexOf(letters.get(i)) == i) chars.add(letters.get(i));
        }
        for (int i = 9; i > 9 - chars.size(); i--) {
            d.add(i);
        }

        // TODO 오름차순 정렬
        Collections.sort(chars);
        int ans = 0;

        // TODO
        do {
            int now = calc();
            if (ans < now) {
                ans = now;
            }
        } while (next_permutation());

        bw.write(ans + "");

        bw.flush();
        bw.close();
    }

    private static int calc() {
        int size = chars.size();
        int sum = 0;

        for (int i = 0; i < size; i++) {
            alpha[chars.get(i)] = d.get(i);
        }

        for (String s : word) {
            int now = 0;
            for (char c : s.toCharArray()) {
                now = now * 10 + alpha[c];
            }
            sum += now;
        }

        return sum;
    }

    public static boolean next_permutation() {
        int size = chars.size();
        int i = size - 1;

        // TODO 교환할 위치 (i) 찾기
        while (i > 0 && chars.get(i - 1) >= chars.get(i)) --i;

        if (i == 0) return false;

        // TODO 교환할 인덱스 (j) 찾기
        int j = size - 1;
        while (chars.get(i - 1) >= chars.get(j)) --j;

        // TODO 교환 (i-1, j)
        char temp = chars.get(i - 1);
        chars.set(i - 1, chars.get(j));
        chars.set(j, temp);

        int k = size - 1;

        // TODO (i 부터 k 까지 오름차순 교환)
        while (i < k) {
            temp = chars.get(i);
            chars.set(i, chars.get(k));
            chars.set(k, temp);
            ++i;
            --k;
        }
        return true;
    }
}
