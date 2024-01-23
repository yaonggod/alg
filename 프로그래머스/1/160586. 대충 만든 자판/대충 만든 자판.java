import java.util.*;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        // 65 90
        int[] minTab = new int[26];
        Arrays.fill(minTab, 101);
        for (int i = 0; i < keymap.length; i++) {
            for (int j = 0; j < keymap[i].length(); j++) {
                if (j + 1 < minTab[keymap[i].charAt(j) - 65]) {
                    minTab[keymap[i].charAt(j) - 65] = j + 1;
                }
            }
        }
        int[] answer = new int[targets.length];
        for (int i = 0; i < targets.length; i++) {
            for (int j = 0; j < targets[i].length(); j++) {
                if (minTab[targets[i].charAt(j) - 65] != 101) {
                    answer[i] += minTab[targets[i].charAt(j) - 65];
                } else {
                    answer[i] = -1;
                    break;
                }
            }
        }
        return answer;
    }
}