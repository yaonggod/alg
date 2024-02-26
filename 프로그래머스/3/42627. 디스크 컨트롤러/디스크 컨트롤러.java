import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        int n = jobs.length;
        // 시작 시간 작은거부터
        Arrays.sort(jobs, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        // 작업 시간 작은거부터
        PriorityQueue<Integer[]> pq = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] a, Integer[] b) {
                if (a[1] == b[1]) return a[0] - b[0];
                return a[1] - b[1];
            }                              
        });
        // 총 경과 시간
        int timeLapse = 0;
        // 지금 시간
        int currentTime = 0;
        
        int index = 0;
        
        while (true) {
            // 작업 큐에 아무것도 없으면 그냥 먼저 온거 하기
            if (pq.isEmpty()) {
                if (index == n) break;
                int[] task = jobs[index];
                timeLapse += task[1];
                currentTime = task[0] + task[1];
                index++;
                // System.out.println(timeLapse);
            // 있던거 꺼내서 하기
            } else {
                Integer[] task = pq.poll();
                timeLapse += currentTime - task[0] + task[1];
                currentTime += task[1];
                // System.out.println(timeLapse);
            }
            // 시간상 작업했어야 하는거 다 넣기
            while (index < n) {
                int[] task = jobs[index];
                if (task[0] < currentTime) {
                    System.out.println("put");
                    pq.offer(new Integer[] {task[0], task[1]});
                    index++;
                } else {
                    break;
                }
            }
        }
        
        return timeLapse / n;
    }
}