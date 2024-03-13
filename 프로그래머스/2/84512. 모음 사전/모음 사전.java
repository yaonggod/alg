import java.util.*;

class Solution {
    static int[] w, a; 
    static int turn;
    static int found = -1;
    static Map<Character, Integer> alp;
    static String ans;
    public int solution(String word) {
        w = new int[5];
        w[0] = 1;
        turn = 0;
        
        alp = new HashMap<>();
        alp.put('A', 1);
        alp.put('E', 2);
        alp.put('I', 3);
        alp.put('O', 4);
        alp.put('U', 5);
        
        // 정답에다가 숫자 붙여넣고
        ans = "";
        char[] wtoc = word.toCharArray();
        for (char c : wtoc) {
            ans += alp.get(c);
        }
        
        dfs("");
        
        return found;
    }
    
    static void dfs(String str) {
        // 찾았으니 그만돌아
        if (found != -1) return;
        // 찾음
        if (str.equals(ans)) {
            found = turn;
            return;
        }
        // turn번째
        turn++;
        // str에다가 1부터 5까지 붙여보기
        for (int i = 1; i <= 5; i++) {
            if (str.length() == 5) return;
            dfs(str + i);
        }
    }
}