package boj;

import java.io.*;
import java.util.*;

public class _3 {
    private static BufferedWriter bw;
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N, M, A, B, C, R, F, S, G, U, D, T, H, W,k, sum, answer = 0;
    static List<Integer> numbers;

    public static void main(String[] args) throws IOException {

        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.valueOf(st.nextToken());

        numbers = new ArrayList<>();
        for(int i=0;i<N;i++){
            int num = Integer.valueOf(br.readLine());
            numbers.add(num);
        }
        k = Integer.valueOf(br.readLine());

        System.out.print(countPairs(numbers, k));

        bw.flush();
        bw.close();
    }

    public static int countPairs(List<Integer> numbers, int k) {
        // Write your code here

        Map<Integer, Boolean> map = new HashMap<>();

        for(int i=0;i<numbers.size();i++)
            map.put(numbers.get(i), true);

        int count = 0;
        for(int n: map.keySet()){
            int find = n + k;

            if(map.get(find) != null)
                count++;
        }

        return count;
    }
}
