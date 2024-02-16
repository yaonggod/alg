import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int targetIdx = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(target)) {
                targetIdx = i;
                break;
            }
        }
        if (targetIdx == -1) return 0;
        
        Set<Integer>[] canGo = new HashSet[words.length];
        // 첫 번째는 begin에서 갈 수 있는 단어들
        Set<Integer> startGo = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            canGo[i] = new HashSet<>();
            if (similar(begin, words[i])) {
                startGo.add(i);
            }
        }
        for (int i = 0; i < words.length; i++) {
            if (i != targetIdx) {
                for (int j = 0; j < words.length; j++) {
                    if (similar(words[i], words[j])) {
                        canGo[i].add(j);
                        canGo[j].add(i);
                    }
                }
            }
        }
        boolean[] visited = new boolean[words.length];
        Queue<Integer[]> queue = new LinkedList<>();
        for (Integer go : startGo) {
            visited[go] = true;
            queue.offer(new Integer[] {go, 1});
        }
        while (!queue.isEmpty()) {
            Integer[] from = queue.poll();
            if (words[from[0]].equals(target)) return from[1];
            for (int to : canGo[from[0]]) {
                if (!visited[to]) {
                    visited[to] = true;
                    queue.offer(new Integer[] {to, from[1] + 1});
                }
            }
        }
        return 0;
    }
    
    static boolean similar(String from, String to) {
        int count = 0;
        for (int i = 0; i < from.length(); i++) {
            if (from.charAt(i) == to.charAt(i)) count++;
        }
        if (count == from.length() - 1) return true;
        return false;
    }
}