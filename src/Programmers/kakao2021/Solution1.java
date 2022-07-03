package Programmers.kakao2021;

import java.util.*;

public class Solution1 {

    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        Map<String, HashSet<String>> map = new HashMap<>();
        Map<String, Integer> idx_map = new HashMap<>();

        for(int i=0;i<id_list.length;i++){
            String name = id_list[i];
            idx_map.put(name, i);
            map.put(name, new HashSet<>());
        }

        for(String data: report){
            String from = data.split(" ")[0];
            String to = data.split(" ")[1];

            map.get(to).add(from);
        }

        for(int i=0;i<id_list.length;i++){
            HashSet<String> set = map.get(id_list[i]);
            int count = set.size();

            if(count >= k){
                for(String data: set){
                    answer[idx_map.get(data)]++;
                }
            }
        }


        return answer;
    }
}
