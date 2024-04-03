import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] pattern1 = new int[] {1, 2, 3, 4, 5};
        int[] pattern2 = new int[] {2, 1, 2, 3, 2, 4, 2, 5};
        int[] pattern3 = new int[] {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        int maxScore = 0;
        int score1 = 0;
        int l1 = pattern1.length;
        int score2 = 0;
        int l2 = pattern2.length;
        int score3 = 0;
        int l3 = pattern3.length;
        
        for (int i = 0; i < answers.length; i++) {
            if (pattern1[i % l1] == answers[i]) score1++;
            if (pattern2[i % l2] == answers[i]) score2++;
            if (pattern3[i % l3] == answers[i]) score3++;
        }
        
        if (score1 > maxScore) maxScore = score1;
        if (score2 > maxScore) maxScore = score2;
        if (score3 > maxScore) maxScore = score3;
        
        ArrayList<Integer> result = new ArrayList<>();
        if (score1 == maxScore) result.add(1);
        if (score2 == maxScore) result.add(2);
        if (score3 == maxScore) result.add(3);
        
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}