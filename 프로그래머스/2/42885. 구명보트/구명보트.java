import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int n = people.length;
        Arrays.sort(people);
        
        // 앞, 뒤 인덱스 조절해가면서 태워보기
        // 30, 40, 50, 50, 60, 70
        // 30 - 70 찾으면 70 앞의 인덱스만 고려하면 됨 뒤에꺼는 너무 커서 혼자 타야대 
        boolean[] visited = new boolean[n];
        int index = n - 1;
        
        int boat = 0;
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                int front = people[i];
                while (index > i) {
                    int back = people[index];
                    // 같이 태울 사람 찾음
                    if (front + back <= limit) {
                        visited[index--] = true;
                        break;
                    } 
                    index--;
                }
                boat++;
            }
        }
        
        return boat;
        
        
        
    }
}