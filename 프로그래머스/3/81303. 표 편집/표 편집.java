import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        int[] before = new int[n];
        int[] after = new int[n];
        Stack<Integer> deleted = new Stack<>();
        
        // -1 0 1 2 3 4 5 6
        //  1 2 3 4 5 6 7 8
        // 2에서 2번 아래로 감 -> 3 -> 4
        // 4를 삭제함
        // 4를 -1, -1로 바꾸고 deleted에 넣고
        // 4의 before의 after를 4의 after로 바꾸고
        // 4의 after의 before를 4의 before로 바꿈
        // 복구할때는 좌우로 -1, -1이 나타나지 않을 때까지
        
        for (int i = 0; i < n; i++) {
            before[i] = i - 1;
            after[i] = i + 1;
        }
        
        // k부터 시작해서 
        for (String c : cmd) {
            if (c.startsWith("U")) {
                int count = Integer.parseInt(c.split(" ")[1]);
                for (int i = 0; i < count; i++) {
                    k = before[k];
                }
            } else if (c.startsWith("D")) {
                int count = Integer.parseInt(c.split(" ")[1]);
                for (int i = 0; i < count; i++) {
                    k = after[k];
                }
            } else if (c.equals("C")) {
                // k가 맨 뒤의 행이다 
                if (after[k] == n) {
                    after[before[k]] = n;
                    deleted.push(k);
                    k = before[k];
                }
                // k가 맨 앞의 행이다
                else if (before[k] == -1) {
                    before[after[k]] = -1;
                    deleted.push(k);
                    k = after[k];
                }
                // k의 before의 after를 k의 after로 바꾸고
                // k의 after의 before를 k의 before로 바꿈
                else {
                    deleted.push(k);
                    after[before[k]] = after[k];
                    before[after[k]] = before[k];
                    k = after[k];
                }
            } else if (c.equals("Z")) {
                int restore = deleted.pop();
                if (after[restore] != n) {
                    before[after[restore]] = restore;
                }
                if (before[restore] != -1) {
                    after[before[restore]] = restore;
                }
            }
        }
        boolean[] answer = new boolean[n];
        while (!deleted.isEmpty()) {
            answer[deleted.pop()] = true;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(answer[i] ? "X" : "O");
        }
        return sb.toString();
    }
}