package javaCode.boj.bfs_dfs;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class _1260 {

    private static BufferedWriter bw;
    private static BufferedReader br;
    private static StringTokenizer st;
    private static boolean[] visited;
    private static ArrayList<Node>[] arr;

    private static class Node {
        int n;
        int d;

        public Node(int n, int d) {
            this.n = n;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        visited = new boolean[N + 1];
        arr = new ArrayList[N + 1];

        for (int i = 0; i < N + 1; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            arr[n1].add(new Node(n2, d));
            arr[n2].add(new Node(n1, d));
        }

        for (int i = 1; i < N + 1; i++) {
            visited = new boolean[N + 1];
            bw.write(dfs(i, visited, 0) + "\n");
        }
        bw.flush();
        bw.close();
    }

    static int dfs(int s, boolean[] visited, int sum) {

        Iterator<Node> it = arr[s].iterator();
        visited[s] = true;
        int result = sum;

        while (it.hasNext()) {
            Node node = it.next();
            if (!visited[node.n]) {
                result += dfs(node.n, visited, sum + node.d);
            }
        }
        return result;
    }
}
