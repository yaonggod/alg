import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        char[] sk = skill.toCharArray();
        int n = sk.length;
        Map<Character, Integer> skillMap = new HashMap<>();
        for (int i = 0; i < sk.length; i++) {
            // 얘를 몇번째에 배워라
            skillMap.put(sk[i], i);
        }
        int possible = 0;
        for (String s : skill_trees) {
            // index번째 스킬을 배워라
            int index = 0;
            boolean success = true;
            char[] tree = s.toCharArray();
            for (char t : tree) {
                // 배울 거 나옴
                if (skillMap.containsKey(t)) {
                    // 순서 맞음
                    if (skillMap.get(t) == index) {
                        index++;
                    } else {
                        success = false;
                        break;
                    }
                } 
            }
            if (success) possible++;
        }
        return possible;
    }
}