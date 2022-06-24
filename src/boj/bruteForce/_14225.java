package boj.bruteForce;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 문제 : 부분 수열의 합
 * 해설 : 재귀를 이용한 브루트 포스 문제
 * - 1182 응용 문제
 * - solution 1 :
 * - combination 을 사용하여 만들어진 조합의 합들을 구함.
 * - 1부터 배열 범위 까지 자연수가 차례대로 존재하는지 확인.
 * - solution 2 :
 * - 문제에서 주어진 자연수 범위 만큼의 boolean 배열을 정의.
 * - combination 을 사용하여 조합의 합들을 boolean 배열의 index 로 하여 true 값을 넣음.
 * - boolean 배열 처음부터 확인.
 */

public class _14225 {
    private static BufferedWriter bw;
    private static BufferedReader br;

    private static StringTokenizer st;

    private static int N, S, cnt = 0;

    private static ArrayList<Integer> arr;

    private static int[] num;

    private static boolean[] checkArr;

    public static void main(String[] args) throws IOException {

        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        N = Integer.valueOf(st.nextToken());
        num = new int[N];
        arr = new ArrayList<>();
        checkArr = new boolean[20 * 100000 + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.valueOf(st.nextToken());
        }

        int result = solution2();

        bw.write(result + "");
        bw.flush();
        bw.close();
    }

    private static int solution2() {
        int result = 1;

        combination2(0, 0);

        for (int i = 1; i < checkArr.length; i++) {
            if (!checkArr[i])
                return i;
        }

        return result;
    }

    private static void combination2(int index, int sum) {

        if (index == N) {
            checkArr[sum] = true;
            return;
        }

        combination2(index + 1, sum + num[index]);
        combination2(index + 1, sum);
    }

    private static int solution1() {
        Arrays.sort(num);
        combination(0, 0);
        Collections.sort(arr);

        int size = arr.size();
        int prev = 1;
        int result = 0;

        for (int i = 0; i < size; i++) {
            if (prev > arr.get(i)) continue;
            if (prev == arr.get(i)) prev++;
            else {
                result = prev;
                break;
            }
        }

        if (result == 0) result = arr.get(size - 1) + 1;

        return result;
    }

    private static void combination(int index, int sum) {

        if (index == N) {
            if (sum != 0) {
                arr.add(sum);
            }
            return;
        }
        combination(index + 1, sum + num[index]);
        combination(index + 1, sum);
    }
}
