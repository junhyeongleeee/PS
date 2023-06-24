package javaCode.ps;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class PS {

    // IO
    static public BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static public StringTokenizer st;
    static public StringBuilder sb = new StringBuilder();

    static public class Test implements Comparable<Test>{
        int a;
        int b;

        public Test(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Test t) {
            return this.a - t.a;
        }
    }

    public static void main(String[] args) {

        //////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////
        // Array
        //////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////

        int[] arr1 = new int[10];
        int[] arr2 = {1, 2, 3};
        Integer[] arr3 = {1, 2, 3};

        String[] s_arr1 = new String[1];

        int a = arr2[1];
        int size1 = arr1.length;

        //////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////
        // Collections
        //////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////
        List<Integer> list1 = new ArrayList<>();
        List<Test> list2 = new ArrayList<>();

        list1.add(1);
        int size2 = list1.size();

        for(Integer num : list1) {
            System.out.println("num: " + num);
        }

        Map<Integer, Integer> map1 = new LinkedHashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();

        map1.put(0, 2);
        map1.get(1);
        map2.get(2);
        map1.remove(0);

        int size3 = map1.size();

        for(Integer key : map1.keySet()) {
            System.out.println("[Key]:" + key + " [Value]:" + map1.get(key));
        }

        //////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////
        // Deque
        //////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////
        Deque<Integer> deque = new ArrayDeque<>();

        deque.add(1);                                       // queue add와 동일
        deque.poll();
        deque.push(1);                                   // stack 의 push 와 동일
        deque.pop();
        deque.addFirst(1);
        deque.addLast(2);
        deque.clear();

        int size4 = deque.size();

        //////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////
        // Sort
        //////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////
        Arrays.sort(arr1);
        Arrays.sort(arr3, Collections.reverseOrder());      // Integer 타입일 경우에만 가능

        Collections.sort(list1);
        Collections.sort(list2, new Comparator<Test>() {
            @Override
            public int compare(Test t1, Test t2) {
                return t1.a - t2.a;
            }
        });
        Collections.sort(list2, (t1, t2) -> t1.a - t2.a);

        //////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////
        // PriorityQueue
        //////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////

        PriorityQueue<Integer> pq1 = new PriorityQueue<>((i1, i2) -> i1 - i2);
        PriorityQueue<Test> pq2 = new PriorityQueue<>((t1, t2) -> t1.a - t2.a);

        for(Test t : pq2) {
            System.out.println("a : " + t.a);
        }

        //////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////
        // dimension 2
        //////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////

        int[][] d2_arr1 = {{1, 1},{2, 2},{3, 3}};
        ArrayList<Integer>[] graph = new ArrayList[19];

        for(int i=0;i<graph.length;i++) {
            graph[i] = new ArrayList<>();
        }

        int row_size = d2_arr1.length;
        int col_size = d2_arr1[0].length;

        System.out.println("row_size : " + row_size + " col_size: " + col_size);


    }
}
