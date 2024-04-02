class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        
        int base = s / n;
        if (base == 0) return new int[] {-1};
        
        int left = s % n;
        for (int i = n - 1; i >= 0; i--) {
            if (left > 0) {
                answer[i] = base + 1;
                left--;
            } else {
                answer[i] = base;
            }
        }
        return answer;
        
    }
}