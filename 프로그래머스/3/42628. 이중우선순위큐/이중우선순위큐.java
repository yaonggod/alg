import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        // 최소값
        PriorityQueue<Integer[]> minpq = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] a, Integer[] b) {
                return a[0] - b[0];
            }
        });
        // 최대값 * -1
        PriorityQueue<Integer[]> maxpq = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] a, Integer[] b) {
                return a[0] - b[0];
            }
        });;
        
        // n번째 나온 명령은 이제 쓸모없음
        boolean[] delete = new boolean[operations.length];
        for (int i = 0; i < operations.length; i++) {
            String[] command = operations[i].split(" ");
            int num = Integer.parseInt(command[1]);
            if (command[0].equals("I")) {
                minpq.offer(new Integer[] {num, i});
                maxpq.offer(new Integer[] {-num, i});
            } else {
                if (num == -1) {
                    while (!minpq.isEmpty()) {
                        Integer[] target = minpq.poll();
                        // 진짜로 지울거 찾음
                        if (!delete[target[1]]) {
                            delete[target[1]] = true;
                            break;
                        } 
                    }
                } else {
                    while (!maxpq.isEmpty()) {
                        Integer[] target = maxpq.poll();
                        // 진짜로 지울거 찾음
                        if (!delete[target[1]]) {
                            delete[target[1]] = true;
                            break;
                        } 
                    }
                }
            }   
        }
        int minq = Integer.MAX_VALUE;
        int maxq = Integer.MIN_VALUE;
        int[] answer = new int[] {0, 0};
        while (!minpq.isEmpty()) {
            Integer[] target = minpq.poll();
            // 이미 없어진거 말고 남아있는거
            if (!delete[target[1]]) {
                if (target[0] > maxq) maxq = target[0];
                if (target[0] < minq) minq = target[0];
            } 
        }
        if (minq == Integer.MAX_VALUE && maxq == Integer.MIN_VALUE) return answer;
        answer[0] = maxq;
        answer[1] = minq;
        return answer;
    }
}