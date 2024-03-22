import java.util.*;

class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        boolean[] used = new boolean[n + 1];
        long a = 1;      
        for (int i = 2; i <= n; i++) {
            a *= i;
        }
        // 첫 번째 숫자에 (n - 1)!개의 순열이 나옴
        // turn을 (n - 1)!로 나눠서 앞에서부터 몇 번째 숫자인지 알아내기
        long turn = k - 1;
        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n + 1];
            a /= n - i;
            int largest = (int) (turn / a);
            int now = -1;
            for (int l = 1; l <= n; l++) {
                // largest번째 큰 수 찾기 
                if (!visited[l] && !used[l]) {
                    visited[l] = true;
                    now++;
                }
                if (now == largest) {
                    // 썼다 
                    used[l] = true;
                    answer[i] = l;
                    break;
                }
            }
            turn = turn % a;
        }
        
        return answer;
    }
}