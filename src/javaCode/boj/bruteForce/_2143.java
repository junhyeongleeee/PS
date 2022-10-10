package javaCode.boj.bruteForce;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class _2143 {

    private static BufferedWriter bw;
    private static BufferedReader br;

    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));

        long t = Long.parseLong(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        int[] b = new int[m];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Long> aList = new ArrayList<>();
        ArrayList<Long> bList = new ArrayList<>();

        calculateSumOfSequence(aList, a);
        calculateSumOfSequence(bList, b);

        Collections.sort(bList);

        long count = 0;
        for (long num : aList) {
            count += calculateRangeOfValidValue(t - num, bList);
        }

        System.out.println(count);
    }

    private static long calculateRangeOfValidValue(long target, ArrayList<Long> bList) {
        int left = 0;
        int right = 0;

        int start = 0;
        int end = bList.size() - 1;
        while (start <= end) {
            int mid = (start + end) / 2;

            if (bList.get(mid) >= target) {
                end = mid -1;
            }
            else {
                start = mid + 1;
            }
        }

        left = end;
        start = 0;
        end = bList.size() - 1;
        while (start <= end) {
            int mid = (start + end) / 2;

            if (bList.get(mid) <= target) {
                start = mid + 1;
            }
            else {
                end = mid -1;
            }
        }
        right = end;
        return right - left;
    }

    static void calculateSumOfSequence(ArrayList<Long> list, int[] numList) {
        for (int i = 0; i < numList.length; i++) {
            long sum = 0;
            for (int j = i; j < numList.length; j++) {
                sum += numList[j];
                list.add(sum);
            }
        }
    }
}
