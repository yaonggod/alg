import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        Map<Integer, Integer> process = new HashMap<>();
        for (int p : priorities) {
            if (!process.containsKey(p)) {
                process.put(p, 1);
            } else {
                process.put(p, process.get(p) + 1);
            }
        }
        Queue<Integer[]> queue = new LinkedList<>();
        for (int i = 0; i < priorities.length; i++) {
            queue.offer(new Integer[] {i, priorities[i]});
        }
        int turn = 1;
        while (!queue.isEmpty()) {
            Integer[] task = queue.poll();
            boolean pass = false;
            for (int i = task[1] + 1; i <= 9; i++) {
                if (process.containsKey(i)) {
                    pass = true;
                    break;
                }
            }
            // 미루자
            if (pass) {
                queue.offer(task);
            }
            // 하자
            else {
                // 만약 location이면
                if (task[0] == location) {
                    break;
                // 우선순위 태스크 한개 줄이기
                } else {
                    process.put(task[1], process.get(task[1]) - 1);
                    if (process.get(task[1]) == 0) {
                        process.remove(task[1]);
                    }
                    turn++;
                }
            }
        }
        return turn;
    }
}