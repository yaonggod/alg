class Solution {
    public int[] solution(int[] sequence, int k) {
        int len = sequence.length;
        int start = 0;
        int end = 0;
        int minLen = len + 1;
        int[] answer = new int[] {start, end};
        int sum = sequence[start];
        
        while (start < len && end < len) {
            if (sum == k) {
                if (minLen > end - start + 1) {
                    minLen = end - start + 1;
                    answer[0] = start;
                    answer[1] = end;
                }
                sum -= sequence[start++];
                end++;
                if (end < len) {
                    sum += sequence[end];
                }
            } else if (sum < k) {
                end++;
                if (end < len) {
                    sum += sequence[end];
                }
            } else {
                sum -= sequence[start++];
            }
        }
        
        return answer;
    }
}