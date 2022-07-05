package boj.bfs_dfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class _9019 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            boolean[] check = new boolean[10000];
            char[] how = new char[10000];
            int[] from = new int[10000];
            check[n] = true;
            from[n] = -1;
            Queue<Integer> q = new LinkedList<Integer>();
            q.add(n);
            while (!q.isEmpty()) {
                int now = q.remove();
                int next = (now*2) % 10000;
                if (check[next] == false) {
                    q.add(next);
                    check[next] = true;
                    from[next] = now;
                    how[next] = 'D';
                }
                next = now-1;
                if (next == -1) next = 9999;
                if (check[next] == false) {
                    q.add(next);
                    check[next] = true;
                    from[next] = now;
                    how[next] = 'S';
                }
                next = (now%1000)*10 + now/1000;
                if (check[next] == false) {
                    q.add(next);
                    check[next] = true;
                    from[next] = now;
                    how[next] = 'L';
                }
                next = (now/10) + (now%10)*1000;
                if (check[next] == false) {
                    q.add(next);
                    check[next] = true;
                    from[next] = now;
                    how[next] = 'R';
                }
            }
            StringBuilder ans = new StringBuilder();
            while (m != n) {
                ans.append(how[m]);
                m = from[m];
            }
            System.out.println(ans.reverse());
        }
    }
}