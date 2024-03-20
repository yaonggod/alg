import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        // 닉네임 현황
        Map<String, String> nickname = new HashMap<>();
        int resultLen = 0;
        for (String r : record) {
            String[] rArr = r.split(" ");
            // 이름 정보
            if (rArr[0].equals("Enter") || rArr[0].equals("Change")) {
                String uuid = rArr[1];
                nickname.put(uuid, rArr[2]);
            }
            // 기록
            if (rArr[0].equals("Enter") || rArr[0].equals("Leave")) {
                resultLen++;
            }
        }
        String[] result = new String[resultLen];
        int index = 0;
        for (int i = 0; i < record.length; i++) {
            String[] rArr = record[i].split(" ");
            if (rArr[0].equals("Enter")) {
                result[index++] = nickname.get(rArr[1]) + "님이 들어왔습니다.";
            } else if (rArr[0].equals("Leave")) {
                result[index++] = nickname.get(rArr[1]) + "님이 나갔습니다.";
            } 
        }
        return result;
    }
}