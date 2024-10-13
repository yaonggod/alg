import java.util.*;

class Solution {
    public String solution(int[] numbers, String hand) {
        // 키패드
        int[][] keypad = new int[][] {{3, 1}, {0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 1}, {1, 2}, {2, 0}, {2, 1}, {2, 2}};
        // 지금 손 위치
        int[] left = new int[] {3, 0};
        int[] right = new int[] {3, 2};
        
        StringBuilder sb = new StringBuilder();
        
        for (int n : numbers) {
            int[] to = keypad[n];
            
            // 왼쪽
            if (to[1] == 0) {
                left[0] = to[0];
                left[1] = to[1];
                sb.append("L");
            }
            
            // 오른쪽
            else if (to[1] == 2) {
                right[0] = to[0];
                right[1] = to[1];
                sb.append("R");
            }
            
            // 가운데
            else {
                int ll = Math.abs(to[0] - left[0]) + Math.abs(to[1] - left[1]);
                int rl = Math.abs(to[0] - right[0]) + Math.abs(to[1] - right[1]);
                
                // 왼쪽이 가까움
                if (ll < rl) {
                    left[0] = to[0];
                    left[1] = to[1];
                    sb.append("L");
                }
                
                // 오른쪽이 가까움
                else if (ll > rl) {
                    right[0] = to[0];
                    right[1] = to[1];
                    sb.append("R");
                }
                
                // 같음
                else {
                    if (hand.equals("left")) {
                        left[0] = to[0];
                        left[1] = to[1];
                        sb.append("L");
                    } else {
                        right[0] = to[0];
                        right[1] = to[1];
                        sb.append("R");
                    }
                }
            }
        }
        return sb.toString();
    }
}