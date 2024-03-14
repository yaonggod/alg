class Solution {
    public long solution(int n) {
        if (n == 1) return 1;
        
        // i번째 칸에 가는 방법
        long[] go = new long[n + 1];
        go[1] = 1;
        go[2] = 1;
        for (int i = 2; i <= n; i++) {
            go[i] = (go[i - 2] + go[i - 1] + go[i]) % 1234567;
        }
        return go[n];
    }
}