import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        Map<String, Integer> termMap = new HashMap<>();
        for (String t : terms) {
            String[] tArr = t.split(" ");
            termMap.put(tArr[0], Integer.parseInt(tArr[1]));
        }
        
        String[] todayArr = today.split("\\.");
        
        int year = Integer.parseInt(todayArr[0]);
        int month = Integer.parseInt(todayArr[1]);
        int day = Integer.parseInt(todayArr[2]);
        
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < privacies.length; i++) {
            String[] pArr = privacies[i].split(" ");
            String type = pArr[1];
            int addMon = termMap.get(type);
            
            String[] sArr = pArr[0].split("\\.");
            int ey = Integer.parseInt(sArr[0]);
            int em = Integer.parseInt(sArr[1]);
            int ed = Integer.parseInt(sArr[2]);
            
            em += addMon;
            while (em > 12) {
                em -= 12;
                ey += 1;
            }
            
            if (ey > year) continue;
            if (ey == year && em > month) continue;
            if (ey == year && em == month && ed > day) continue;
            
            answer.add(i + 1);
        }
        
        int[] ansArr = new int[answer.size()];
        for (int i = 0; i < ansArr.length; i++) {
            ansArr[i] = answer.get(i);
        }
        return ansArr;
    }
}