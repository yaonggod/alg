import java.util.*;
class Solution {
    public String solution(String s, String skip, int index) {
        // 97
        // 안 셀 알파벳들
        boolean[] noCount = new boolean[26];
        int count = skip.length();
        for (int i = 0; i < count; i++) {
            char a = skip.charAt(i);
            noCount[(int) a - 97] = true;
        }
        // 인덱스 알파벳 양방향 저장
        Map<Character, Integer> alpIdx = new HashMap<>();
        Map<Integer, Character> idxAlp = new HashMap<>();
        int idx = 0;
        for (int i = 0; i < 26; i++) {
            if (!noCount[i]) {
                alpIdx.put((char) (i + 97), idx);
                idxAlp.put(idx, (char) (i + 97));
                idx++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int newIdx = (alpIdx.get(s.charAt(i)) + index) % (26 - count);
            sb.append(idxAlp.get(newIdx));
        }
        return sb.toString();
    }
}