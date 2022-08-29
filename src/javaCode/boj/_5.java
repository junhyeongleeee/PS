package javaCode.boj;

import java.io.*;
import java.util.*;

public class _5 {

    private static BufferedWriter bw;
    private static BufferedReader br;
    private static int N, Q;
    static List<String> sentences;
    static List<String> queries;

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
