import java.util.*;
class Solution {
    public int solution(String[] strs, String t) {
        char[] tArr = t.toCharArray();
        int[] dp = new int[t.length() + 1];
        // 시작하기 전 ~ 아무데나 갈 수 있음
        Arrays.fill(dp, -1);
        // 여기까지 몇 개를 써서 왔다
        dp[0] = 0;
        for (int i = 0; i < dp.length; i++) {
            // 여기까지 와본 경험이 있다
            if (dp[i] >= 0) {
                // 남은 길이: 대조할 수 있는지 보게
                int length = dp.length - (i + 1);
                for (String st : strs) {
                    char[] piece = st.toCharArray();
                    // 대조 가능
                    if (piece.length <= length) {
                        boolean match = true;
                        for (int j = 0; j < piece.length; j++) {
                            if (tArr[i + j] != piece[j]) {
                                match = false;
                                break;
                            }
                        }
                        if (match) {
                            if (dp[i + piece.length] == -1) {
                                dp[i + piece.length] = dp[i] + 1;
                            } else if (dp[i + piece.length] > dp[i] + 1) {
                                dp[i + piece.length] = dp[i] + 1;
                            }
                        }
                    }
                }
            }
        }
        return dp[dp.length - 1];
    }
}