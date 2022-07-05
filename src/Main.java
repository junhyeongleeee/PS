import java.io.*;
import java.util.*;

public class Main {

    private static BufferedWriter bw;
    private static BufferedReader br;

    private static StringTokenizer st;

    private static int T, N, M, K, result = Integer.MAX_VALUE;
    private static char[] how;
    private static boolean[] visited;
    private static int[] from;

    private static char[] dslr = new char[]{'D', 'S', 'L', 'R'};
    private static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {

        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        T = Integer.valueOf(st.nextToken());

        if(T == 0) return;

        for(int i=0;i<T;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.valueOf(st.nextToken());
            int b = Integer.valueOf(st.nextToken());

            bw.write(bfs(s, b) + "\n");
        }

        bw.flush();
        bw.close();
    }

    static String bfs(int s, int b){
        queue = new LinkedList<>();
        how = new char[10000];
        from = new int[10000];
        visited = new boolean[10000];
        queue.add(s);
        visited[s] = true;

        while(!queue.isEmpty()){
            int num = queue.remove();
            int n_num = num*2 % 10000;
            if(!visited[n_num]){
                visited[n_num] = true;
                how[n_num] = 'D';
                from[n_num] = num;
                queue.add(n_num);
            }
            n_num = num == 0 ? 9999 : num - 1;
            if(!visited[n_num]){
                visited[n_num] = true;
                how[n_num] = 'S';
                from[n_num] = num;
                queue.add(n_num);
            }
            n_num = num % 1000 * 10 + num / 1000;
            if(!visited[n_num]){
                visited[n_num] = true;
                how[n_num] = 'L';
                from[n_num] = num;
                queue.add(n_num);
            }
            n_num = num%10 * 1000 + num/10;
            if(!visited[n_num]){
                visited[n_num] = true;
                how[n_num] = 'R';
                from[n_num] = num;
                queue.add(n_num);
            }
        }

        StringBuilder sb = new StringBuilder();
        while( s != b){
            sb.append(how[b]);
            b = from[b];
        }
        return sb.toString();
    }
}
