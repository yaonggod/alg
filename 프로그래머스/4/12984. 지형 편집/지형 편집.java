import java.util.*;
public class Solution {
    public long solution(int[][] land, int P, int Q) {
        int n = land.length;
        
        int[] height = new int[n * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                height[i * n + j] = land[i][j];
            }
        }
        
        // 높이 정렬하기
        Arrays.sort(height);
        
        // V자 모양이므로, 내려가다가 올라가는 시점에 그만두기
        long currCost = 0;
        // 처음 cost - 일단 다 빼고 시작
        int h = height[0];
        for (int i = 0; i < height.length; i++) {
            if (height[i] != h) {
                currCost += (long) (height[i] - h) * (long) Q;
            }
        }
        
        for (int i = 1; i < height.length; i++) {
            
            if (height[i - 1] == height[i]) continue;
            
            // 층 변화하면
            long gap = (long) height[i] - height[i - 1];
            // 앞에 애들 증축하기
            long add = gap * (long) P * (long) i;
            // 뒤에 애들 층 삭제 취소하기
            long delete = gap * (long) Q * (long) (height.length - i);
            
            if (add < delete) currCost += (add - delete);
            if (add > delete) break;
        }
        return currCost;
    }
    
}