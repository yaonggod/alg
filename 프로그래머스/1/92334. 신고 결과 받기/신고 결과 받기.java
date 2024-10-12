import java.util.*;

class Solution {
    static Map<String, Integer> rp;
    static Map<String, Integer> idx;
    static Map<String, Integer> mail;
    static Map<String, Set<String>> result;
    public int[] solution(String[] id_list, String[] report, int k) {
        // 리폿 횟수
        rp = new HashMap<>();
        // 인덱스
        idx = new HashMap<>();
        // 메일 받은 횟수
        mail = new HashMap<>();
        // 얘는 누구한테 리폿받았어요
        result = new HashMap<>();
        
        for (int i = 0; i < id_list.length; i++) { 
            String id = id_list[i];
            rp.put(id, 0);
            idx.put(id, i);
            mail.put(id, 0);
            result.put(id, new HashSet<>());
        }
        
        for (String r : report) {
            String from = r.split(" ")[0];
            String to = r.split(" ")[1];
            
            if (!result.get(to).contains(from)) {
                // 신고 1번 추가
                rp.put(to, rp.get(to) + 1);
                // 정지 시 메일 보내기
                result.get(to).add(from);
            }
        }
        
        for (String id : rp.keySet()) {
            // 정지해~
            if (rp.get(id) >= k) {
                for (String sendMail : result.get(id)) {
                    mail.put(sendMail, mail.get(sendMail) + 1);
                }
            }
        }
        
        int[] ans = new int[id_list.length];
        for (String id : mail.keySet()) {
            int index = idx.get(id);
            ans[index] = mail.get(id);
        }
        
        return ans;
    }
}