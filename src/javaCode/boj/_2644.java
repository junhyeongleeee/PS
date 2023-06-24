package javaCode.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _2644 {

    static public BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static public StringTokenizer st;
    static public List<Integer>[] graph;
    static public Deque<Node> queue = new ArrayDeque();
    static public boolean[] visited;
    static class Node {
        int n;
        int d;

        public Node(int n, int d) {
            this.n = n;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i=1;i<=n;i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());       // y의 부모 번호
            int y = Integer.parseInt(st.nextToken());

            graph[x].add(y);
            graph[y].add(x);
        }

        System.out.println(bfs(a, b));
    }
    public static int bfs(int s, int e) {
        queue.add(new Node(s, 0));
        visited[s] = true;

        while(!queue.isEmpty()) {
            Node q = queue.poll();
            int n = q.n;
            int d = q.d;

            if (n == e) {
                return d;
            }

            for (int nn : graph[n]) {
                if (visited[nn]) continue;
                visited[nn] = true;
                queue.add(new Node(nn, d + 1));
            }
        }
        return -1;
    }
}