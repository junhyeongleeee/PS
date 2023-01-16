package javaCode.boj.softeer;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SafeBreaking {

    private static BufferedWriter bw;
    private static BufferedReader br;
    private static StringTokenizer st;

    private static int N, W;

    private static class Bag implements Comparable<Bag> {
        int m;
        int p;

        public Bag(int m, int p) {
            this.m = m;
            this.p = p;
        }

        @Override
        public int compareTo(@NotNull Bag o) {
            return o.p - this.p;
        }
    }

    public static void main(String args[]) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        PriorityQueue<Bag> bags = new PriorityQueue<Bag>(N);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            bags.offer(new Bag(m, p));
        }

        long result = 0;
        int max = W;
        while (!bags.isEmpty()) {
            Bag bag = bags.poll();
            if (bag.m > max) {
                result += (long) max * bag.p;
                break;
            }
            result += (long) bag.m * bag.p;
            max -= bag.m;
        }
        System.out.print(result);
    }
}