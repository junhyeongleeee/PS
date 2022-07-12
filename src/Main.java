import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.*;
import java.util.*;


/**
 * 1초 일 때 입력의 최대 크기
 * <p>
 * O(N) : 약 1억
 * O(N^2) : 약 1만
 * O(N^3) : 약 500
 * O(2^N) : 약 20
 * O(N!) : 약 10
 */

public class Main {

    private static BufferedWriter bw;
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N, M, A, B, C, R, F, S, G, U, D, T, H, W, k, Q, sum, answer = 0;

    private static int[] dx = new int[]{0, 0, -1, 1};
    private static int[] dy = new int[]{-1, 1, 0, 0};

    static List<Integer> numbers;
    static List<String> sentences;
    static List<String> queries;

    private static class Point {
        int x;
        int y;

        int w;

        public Point(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }

    static ArrayList<Point> arr;

    public static void main(String[] args) throws IOException {

        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.valueOf(br.readLine());

        sentences = new ArrayList<>();

        for (int i = 0; i < N; i++)
            sentences.add(br.readLine());

        Q = Integer.valueOf(br.readLine());
        queries = new ArrayList<>();

        for (int i = 0; i < Q; i++)
            queries.add(br.readLine());

        for (int i = 0; i < textQueries(sentences, queries).size(); i++) {
            List<Integer> result = textQueries(sentences, queries).get(i);
            for (int j = 0; j < result.size(); j++)
                System.out.print(result.get(j) + " ");
            System.out.println();
        }

        bw.flush();
        bw.close();
    }

    public static List<List<Integer>> textQueries(List<String> sentences, List<String> queries) {
        // Write your code here

        List<List<Integer>> list = new ArrayList<>();

        for (int i = 0; i < queries.size(); i++)
            list.add(new ArrayList<>());

        List<List<String>> list_query = new ArrayList<>();

        for (int i = 0; i < queries.size(); i++) {
            list_query.add(new ArrayList<>());
            String[] arr = queries.get(i).split(" ");
            for (int j = 0; j < arr.length; j++) {
                list_query.get(i).add(arr[j]);
            }
        }

        for (int i = 0; i < sentences.size(); i++) {
            String[] arr = sentences.get(i).split(" ");
            Map<String, Boolean> map = new HashMap<>();

            for (int j = 0; j < arr.length; j++)
                map.put(arr[j], true);

            for (int j = 0; j < list_query.size(); j++) {
                List<String> query = list_query.get(j);
                boolean check = true;
                for (int k = 0; k < query.size(); k++)
                    if (map.get(query.get(k)) == null) {
                        check = false;
                        break;
                    }

                if (check) list.get(j).add(i);
            }
        }

        for (int i = 0; i < list.size(); i++)
            if (list.get(i).size() == 0)
                list.get(i).add(-1);

        return list;
    }

}
