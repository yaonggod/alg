class Solution {
    public long solution(int k, int d) {
        // 0 2 4
        // 0 1 2 3 4 5
        // 0 3 
        long answer = (d / k + 1) * 2 - 1;
        
        for (int i = k; i <= d; i += k) {
            int length = (int) Math.floor(Math.pow((long) d * (long) d - (long) i * (long) i, 0.5)) / k;
            if (length == 0) break;
            answer += length;
        }
        return answer;
    }
}