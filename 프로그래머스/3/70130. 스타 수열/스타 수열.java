import java.util.*;

class Solution {
    public int solution(int[] a) {
        if (a.length == 1) return 0;
        if (a.length == 2) return 2;
        
        // 숫자 출현 횟수
        int[] count = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            count[a[i]]++;
        }
        
        int starLen = 0;
        for (int i = 0; i < a.length; i++) {
            // 스타로 스타 수열 만들거임
            int star = i;
            int len = 0;
            
            if (count[star] == 0 || count[star] <= starLen) continue;
            
            int j = 0;
            boolean[] visited = new boolean[a.length];
            while (j < a.length) {
                
                // 스타 출현
                if (a[j] == star) {
                    int front = j - 1;
                    int back = j + 1;
                    // 앞에 숫자 가져다가 만들기
                    if (front >= 0 && a[front] != star && !visited[front]) {
                        len++;
                        j++;
                        visited[front] = true;
                    // 뒤에 숫자 가져다가 만들기
                    } else if (back < a.length && a[back] != star && !visited[back]) {
                        len++;
                        j += 2;
                        visited[back] = true;
                    } else {
                        j++;
                    }
                } else {
                    j++;
                }
            }
            starLen = Math.max(starLen, len);
        }
        return starLen * 2;
    }
}