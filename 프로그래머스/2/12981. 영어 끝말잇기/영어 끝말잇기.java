import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[] {0, 0};
        char last = words[0].toCharArray()[words[0].length() - 1];
        Set<String> before = new HashSet<>();
        before.add(words[0]);
        for (int i = 1; i < words.length; i++) {
            int person = i % n + 1;
            int turn = i / n + 1;
            boolean end = false;
            // 한 글자
            if (words[i].length() == 1) {
                end = true;
            }
            // 안이어짐
            char first = words[i].toCharArray()[0];
            if (first != last) {
                end = true;
            }
            // 이전에 나왔음
            if (before.contains(words[i])) {
                end = true;
            }
            if (end) {
                answer[0] = person;
                answer[1] = turn;
                break;
            } else {
                before.add(words[i]);
                last = words[i].toCharArray()[words[i].length() - 1];
            }
        }
        return answer;
    }
}