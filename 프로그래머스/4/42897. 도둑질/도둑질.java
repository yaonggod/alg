class Solution {
    public int solution(int[] money) {
        int n = money.length;
        
        // 첫 번째 집 훔침
        int[] dp1 = new int[n];
        // 훔침
        dp1[0] = money[0];
        // 안훔침
        dp1[1] = money[0];
        for (int i = 2; i < n - 1; i++) {
            // 건너와서 훔치냐 안훔치냐
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + money[i]); 
        }
        
        // 두 번째 집부터 훔침
        int[] dp2 = new int[n];
        dp2[1] = money[1];
        for (int i = 2; i < n; i++) {
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + money[i]);
        }
        
        return Math.max(dp1[n - 2], dp2[n - 1]);
    }
}