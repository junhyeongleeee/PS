package javaCode.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _2110 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] houses = new int[n];

        for (int i = 0; i < n; i++) {
            int r = Integer.parseInt(br.readLine());
            houses[i] = r;
        }

        Arrays.sort(houses);

        int left = 1;
        int right = houses[n - 1] - houses[0];
        int result = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            int start = houses[0];
            int count = 1;
            for (int i = 1; i < n; i++) {
                int d = houses[i] - start;
                if (d >= mid) {
                    count++;
                    start = houses[i];
                }
            }

            if (count >= c) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(result);
    }
}
