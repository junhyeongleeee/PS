package boj.bruteForce;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 문제 : 에너지 모으기
 * 해설 : 재귀
 */

public class _16198 {

    private static BufferedWriter bw;
    private static BufferedReader br;

    private static StringTokenizer st;

    private static int N, M, result = Integer.MAX_VALUE;

    private static int[] e_index;

    public static void main(String[] args) throws IOException {

        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.valueOf(br.readLine());


        ArrayList<Integer> energy;
        energy = new ArrayList<>(N);
        e_index = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            energy.add(Integer.valueOf(st.nextToken()));
            e_index[i] = i;
        }

        int result = getEnergy(energy);


        bw.write(result + "");
        bw.flush();
        bw.close();
    }

    private static int getEnergy(ArrayList<Integer> energy){
        int size = energy.size();
        if(size == 2) return 0;
        int result = 0;
        for(int i=1;i<size - 1;i++){
            int sum = energy.get(i - 1) * energy.get( i + 1);
            ArrayList<Integer> n_energy = new ArrayList<>(N);
            n_energy.addAll(energy);
            n_energy.remove(i);
            sum += getEnergy(n_energy);
            result = Math.max(sum, result);
        }

        return result;
    }
}
