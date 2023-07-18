package javaCode.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class _14425 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<String, Boolean> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.put(br.readLine(), false);
        }

        int result = 0;
        for (int i = 0; i < m; i++) {
            if (map.containsKey(br.readLine())) {
                result++;
            }
        }

        System.out.println(result);
    }
}
