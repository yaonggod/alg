import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> runner = new HashMap<>();
        for (String p : participant) {
            if (!runner.containsKey(p)) {
                runner.put(p, 1);
            } else {
                runner.put(p, runner.get(p) + 1);
            }
        }
        
        for (String c : completion) {
            runner.put(c, runner.get(c) - 1);
            if (runner.get(c) == 0) {
                runner.remove(c);
            }
        }
        
        String answer = "";
        for (String r : runner.keySet()) {
            answer = r;
        }
        return answer;
    }
}