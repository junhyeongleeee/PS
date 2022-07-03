import java.io.*;
import java.util.*;

public class Main {

    private static BufferedWriter bw;
    private static BufferedReader br;

    private static StringTokenizer st;

    private static int N, M, result = Integer.MAX_VALUE;

    private static int[][] sudoku;

    private static boolean[][] c;
    private static boolean[][] c2;
    private static boolean[][] c3;

    public static void main(String[] args) throws IOException {

        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));

        Solution s = new Solution();

//        System.out.println("abcabcdede -> start");
//        System.out.println(s.solution("abcabcdede"));

        String j = "()";
        int[] arr = new int[1];

        j = j.substring(1, j.length() - 1);
        System.out.println(j.length() + " " + j);

        bw.flush();
        bw.close();
    }
   static class Solution {
        public int solution(String s) {
            int answer = Integer.MAX_VALUE;
            // 최대로 나눌 수 있는 문자열의 길이
            int size = s.length() / 2;

            // i -> 나눌 길이
            for(int i=1;i<=size;i++){
                int idx = i;

                // 나눌 첫번째 문자
                String first = s.substring(0, i);

                // 나눠서 완성된 문자열
                String completed = "";

                // 반복 횟수
                int repeat = 1;

                while(true){
                    // 잘려진 뒤에 문자열

                    String remainder = s.substring(idx);

                    if(remainder.length() > i){
                        String next = s.substring(idx, idx + i);
                        if(first.equals(next)){
                            repeat++;
                        }
                        else{
                            if(repeat == 1)
                                completed += first;
                            else completed += String.valueOf(repeat) + first;
                            repeat = 1;
                            first = next;
                        }
                        idx += i;
                    }
                    else if(remainder.length() == i){
                        if(first.equals(remainder)){
                            completed += String.valueOf(++repeat) + first;
                        }
                        else {
                            if(repeat == 1)
                                completed += first + remainder;
                            else completed += String.valueOf(repeat) + first + remainder;
                        }
                        break;
                    }
                    else{
                        if(repeat == 1)
                            completed += first + remainder;
                        else completed += String.valueOf(repeat) + first + remainder;
                        break;
                    }
                }
                // 잘라서 완성된 문자열 길이
                answer = Math.min(answer, completed.length());
            }

            return answer;
        }


    }
}
