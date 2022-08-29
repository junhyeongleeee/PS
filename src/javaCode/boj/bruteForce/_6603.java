package javaCode.boj.bruteForce;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 문제 : 로또
 * 해설 : 아래의 방법을 이용한 Combination
 *      1. 재귀 (visited, stack)
 *      2. 백 트래킹
 */

public class _6603 {

    private static BufferedWriter bw;
    private static BufferedReader br;

    private static StringTokenizer st;

    private static ArrayList<Integer> lotto;

    public static void main(String[] args) throws IOException {

        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            st = new StringTokenizer(br.readLine());
            int n;
            if ((n = Integer.valueOf(st.nextToken())) == 0) {
                bw.flush();
                bw.close();
                return;
            } else {
                int[] nums = new int[n];
                boolean[] visited = new boolean[n];
                lotto = new ArrayList<>(n);

                for (int i = 0; i < n; i++) {
                    nums[i] = Integer.valueOf(st.nextToken());
                }
                combination3(nums, visited ,0, 6);
                bw.write("\n");
            }
        }
    }

    // TODO 재귀를 이용한 호출 1 [visited 배열 활용]
    private static void combination1(int[] nums, boolean[] visited, int depth, int r) throws IOException {
        if (r == 0) {
            for (int i = 0; i < nums.length; i++) {
                if (visited[i])
                    bw.write(nums[i] + " ");
            }
            bw.write("\n");
            return;
        }
        if (depth == nums.length) {
            return;
        } else {
            visited[depth] = true;
            combination1(nums, visited, depth + 1, r - 1);
            visited[depth] = false;
            combination1(nums, visited, depth + 1, r);
        }
    }

    // TODO 재귀를 이용한 호출 2 [스택 활용]
    private static void combination2(int[] nums, int index, int cnt) throws IOException {
        if (cnt == 6) {
            for (int num : lotto) {
                bw.write(num + " ");
            }
            bw.write("\n");
            return;
        }
        if (index == nums.length) {
            return;
        } else {
            lotto.add(nums[index]);
            combination2(nums, index + 1, cnt + 1);
            lotto.remove(lotto.size() - 1);
            combination2(nums, index + 1, cnt);
        }
    }

    // TODO 백 트래킹을 이용한 호출

    private static void combination3(int[] nums, boolean[] visited, int start, int r) throws IOException {
        if (r == 0) {
            for(int i=0;i<visited.length;i++){
                if(visited[i])
                    bw.write(nums[i] + " ");
            }
            bw.write("\n");
            return;
        } else {
            for (int i = start; i < nums.length; i++) {
                visited[i] = true;
                combination3(nums, visited, i + 1, r - 1);
                visited[i] = false;
            }
        }
    }
}
