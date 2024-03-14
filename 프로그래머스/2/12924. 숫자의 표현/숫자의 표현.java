class Solution {
    public int solution(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            // 홀수인데
            if (i % 2 == 1) {
                // 쪼개지면
                if (n % i == 0) {
                    int mid = n / i;
                    // 최소
                    int left = mid - i / 2;
                    // 자연수여야함
                    if (left > 0) count++;
                    // 최대
                    // int right = mid + i / 2;
                }
            // 짝수다
            } else {
                // 쪼개진다
                if ((n - i / 2) % i == 0) {
                    int mid = (n - i / 2) / i;
                    int left = mid - i / 2 + 1;
                    if (left > 0) count++;
                }
            }
        }
        return count;
        
        // 5 6 7 8 -> 26 -> 24 + 2
        // 5 6 7 8 9 10 -> 45 -> 42 + 3 
        // 5 6 7 8 9 10 11 12 -> 68 -> 64 + 4
    }
}