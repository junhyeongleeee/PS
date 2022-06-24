package boj.bruteForce;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * 문제 : 연산자 끼워 넣기
 * 해설 : 순열 (next_permutation) 을 이용한 브루트 포스 문제
 */

public class _14888 {

    private static Vector<Integer> nums;
    private static Vector<Character> letters;

    private static Vector<Integer> d;

    private static BufferedWriter bw;
    private static BufferedReader br;

    private static StringTokenizer st;
    private static ArrayList<Integer> operators;

    private static int[] alpha;

    private static int max = Integer.MIN_VALUE;
    private static int min = Integer.MAX_VALUE;

    private static int N;

    public static void main(String[] args) throws IOException {

        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.valueOf(br.readLine());

        nums = new Vector(N);
        letters = new Vector<>();
        operators = new ArrayList<>();

        alpha = new int[256];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            nums.add(Integer.valueOf(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<4;i++){
            int input = Integer.valueOf(st.nextToken());
            for(int j=0;j<input; j++){
                operators.add(i);
            }
        }

        calc2(0, nums.get(0), operators.get(0), operators.get(1), operators.get(2), operators.get(3));

        bw.write(max+"\n"+min);

        bw.flush();
        bw.close();
    }

    private static void calc2(int index, int sum, int plus, int minus, int mul, int div){
        if(index == N - 1){
            if(max < sum){
                max = sum;
            }
            if(min > sum){
                min = sum;
            }
            return;
        }

        if(plus > 0)
            calc2(index + 1, sum + nums.get(index + 1), plus - 1, minus, mul, div);
        if(minus > 0)
            calc2(index + 1, sum - nums.get(index + 1), plus, minus - 1, mul, div);
        if(mul > 0)
            calc2(index + 1, sum * nums.get(index + 1), plus, minus, mul - 1, div);
        if(div > 0)
            calc2(index + 1, sum / nums.get(index + 1), plus, minus, mul, div - 1);
    }
}
