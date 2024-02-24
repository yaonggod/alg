class Solution {
    public int solution(int[] queue1, int[] queue2) {
        // queue1의 총합에서 시작하기 
        long sum = 0;
        long sum1 = 0;
        long sum2 = 0;
        int[] queue = new int[queue1.length + queue2.length];
        for (int i = 0; i < queue1.length; i++) {
            sum1 += queue1[i];
            sum += queue1[i];
            queue[i] = queue1[i];
        }
        for (int i = 0; i < queue2.length; i++) {
            sum2 += queue2[i];
            queue[i + queue1.length] = queue2[i];
        }
        long target = (sum1 + sum2) / 2;
        
        int from = 0;
        int to = queue1.length - 1;
        int start = from;
        int end = to;
        
        // 3 (2 7 2 4) 6 5 1 
        // i = 0 ~ 3 -> 1 ~ 4
        // 1 + 1 = 2
        
        // 1 2 1 2 1 (10) 1 2
        // i = 0 ~ 3 -> 5 ~ 5
        // 5 + 2 = 7
        
        while (true) {
            if (sum == target) break;
            // 작다
            if (sum < target) {
                if (end == queue.length - 1) break;
                // 뒤에거 하나를 더해보자
                if (end < queue.length - 1) {
                    end++;
                    sum += queue[end];
                }
            }
            // 크다
            else if (sum > target) {
                if (start == queue.length) break;
                // 앞에거 하나를 줄여보자
                if (start < queue.length) {
                    sum -= queue[start];
                    start++;
                }
            }
        }
        if (start == queue.length || end == queue.length - 1) return -1;
        return start + end - from - to;
    }
}