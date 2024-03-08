import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        // 조직원들 위치
        Map<String, Integer> index = new HashMap<>();
        for (int i = 0; i < enroll.length; i++) {
            index.put(enroll[i], i);
        }
        
        int[] result = new int[enroll.length];
        for (int i = 0; i < amount.length; i++) {
            String person = seller[i];
            int profit = amount[i] * 100;
            while (true) {
                int ten = profit / 10;
                int ninety = profit - ten;
                // 나는 90%를 가지고
                result[index.get(person)] += ninety;
                // 부모 준다
                String parent = referral[index.get(person)];
                // 부모가 없거나 부모가 받은 돈이 없을 때
                if (parent.equals("-") || ten == 0) break;
                // 위로 타고 올라가기
                person = parent;
                profit = ten;
            }
        }
        return result;
    }
}