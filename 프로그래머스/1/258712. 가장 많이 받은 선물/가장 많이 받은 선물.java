import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        // 친구 인덱스
        int n = friends.length;
        Map<String, Integer> frIdx = new HashMap<>();
        for (int i = 0; i < n; i++) {
            frIdx.put(friends[i], i);
        }
        
        // 선물 지수
        int[] give = new int[n];
        int[] receive = new int[n];
                      
        // i가 j에게 선물을 줬어요
        int[][] matrix = new int[n][n];
        
        for (int i = 0; i < gifts.length; i++) {
            String[] data = gifts[i].split(" ");
            String from = data[0];
            String to = data[1];
            
            int fromIdx = frIdx.get(from);
            int toIdx = frIdx.get(to);
            
            give[fromIdx]++;
            receive[toIdx]++;
            matrix[fromIdx][toIdx]++;
        }
        
        // 이번달에 받을거
        int[] thisMon = new int[n];
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                // i가 더 많이줌
                if (matrix[i][j] > matrix[j][i]) {
                    thisMon[i]++;
                // j가 더 많이줌
                } else if (matrix[i][j] < matrix[j][i]) {
                    thisMon[j]++;
                } else {
                    // 선물 지수
                    int presentA = give[i] - receive[i];
                    int presentB = give[j] - receive[j];
                    if (presentA > presentB) {
                        thisMon[i]++;
                    } else if (presentA < presentB) {
                        thisMon[j]++;
                    }
                }
            }
        }
        
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (thisMon[i] > result) result = thisMon[i];
        }
        return result;
    }
}