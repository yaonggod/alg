import java.util.*;
class Solution {
    // 지금 위치
    static int[] current;
    // 오프닝 정보
    static int[] ops;
    static int[] ope;
    // 비디오 정보
    static int[] video;
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        current = timeChange(pos);
        ops = timeChange(op_start);
        ope = timeChange(op_end);
        video = timeChange(video_len);
        
        skipOpening();
        
        for (String c : commands) {
            if (c.equals("next")) {
                doNext();
            } else if (c.equals("prev")) {
                doPrev();
            }
            skipOpening();
        }
        
        StringBuilder ans = new StringBuilder();
        if (current[0] < 10) {
            ans.append(0);
        }
        ans.append(current[0]);
        ans.append(":");
        if (current[1] < 10) {
            ans.append(0);
        }
        ans.append(current[1]);
        return ans.toString();
    }
    
    static int[] timeChange(String data) {
        int[] ans = new int[2];
        ans[0] = Integer.parseInt(data.split(":")[0]);
        ans[1] = Integer.parseInt(data.split(":")[1]);
        return ans;
    }
    
    static void skipOpening() {
        boolean op = false;
        if (current[0] > ops[0] && current[0] < ope[0]) {
            op = true;
        } else if (current[0] == ops[0] && current[0] == ope[0]) {
            if (current[1] >= ops[1] && current[1] <= ope[1]) {
                op = true;
            }
        } else if (current[0] == ops[0] && current[1] >= ops[1]) {
            op = true;
        } else if (current[0] == ope[0] && current[1] <= ope[1]) {
            op = true;
        }
        if (op) {
            current[0] = ope[0];
            current[1] = ope[1];
        }
    }
    
    static void doPrev() {
        current[1] -= 10;
        if (current[1] < 0) {
            current[1] += 60;
            current[0] -= 1;
        }
        if (current[0] < 0) {
            current[0] = 0;
            current[1] = 0;
        }
    }
    
    static void doNext() {
        current[1] += 10;
        if (current[1] >= 60) {
            current[1] -= 60;
            current[0] += 1;
        }
        if (current[0] > video[0] || (current[0] == video[0] && current[1] > video[1])) {
            current[0] = video[0];
            current[1] = video[1];
        }
    }
}