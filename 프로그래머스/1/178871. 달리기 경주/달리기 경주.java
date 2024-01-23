import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        Map<String, Integer> name = new HashMap<>();
        Map<Integer, String> rank = new HashMap<>();
        
        for (int i = 0; i < players.length; i++) {
            name.put(players[i], i + 1);
            rank.put(i + 1, players[i]);
        }
        
        for (int i = 0; i < callings.length; i++) {
            // A의 이름과 순위
            String an = callings[i];
            int ar = name.get(callings[i]);
            // B의 이름과 순위 - A보다 한 순위 위
            int br = ar - 1;
            String bn = rank.get(br);
            // 둘의 순위를 바꿔준다
            name.put(an, br);
            name.put(bn, ar);
            rank.put(ar, bn);
            rank.put(br, an);
        }
        
        String[] result = new String[players.length];
        for (int i = 0; i < players.length; i++) {
            result[i] = rank.get(i + 1);
        }
        
        return result;
    }
}