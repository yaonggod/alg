import java.util.*;

class Solution {
    static int n;
    static char[] nos;
    static boolean[] visited;
    static Set<Integer> sosus = new HashSet<>();
    public int solution(String numbers) {
        nos = numbers.toCharArray();
        n = nos.length;
        visited = new boolean[n];
        choose(0);
        return sosus.size();
    }
    
    static void choose(int result) {
        if (sosu(result)) {
            sosus.add(result);
        }
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                choose(result * 10 + (nos[i] - '0'));
                visited[i] = false;
            }
        }
    }
    
    
    static boolean sosu(int num) {
        int count = 0;
        for (int i = 1; i <= num; i++) {
            if (num % i == 0) {
                count++;
            }
            if (count > 2) return false;
        }
        return count == 2 ? true : false;
    }
}