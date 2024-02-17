import java.util.*;

class Solution {
    static int[][] seq;
    static long maxSum;
    static int len;
    public long solution(int[] sequence) {
        long[][] sum = new long[2][sequence.length];
        // 1
        sum[0][0] = (long) sequence[0];
        // -1
        sum[1][0] = (long) -sequence[0];
        // 2 -3 -6 -1 3 1 2 -4
        // -2 3 6 1 -3 -1 -2 4
        for (int i = 1; i < sequence.length; i++) {
            long add0 = (long) sequence[i] * (i % 2 == 0 ? 1 : -1); 
            sum[0][i] = Math.max(sum[0][i - 1] + add0, add0);
            long add1 = -add0;
            sum[1][i] = Math.max(sum[1][i - 1] + add1, add1);
            // System.out.println(sum[0][i] + " " + sum[1][i]);
        }
        long max = Math.max(sum[0][0], sum[1][0]);
        for (int i = 1; i < sequence.length; i++) {
            max = Math.max(Math.max(sum[0][i], sum[1][i]), max);
        }
        return max;
    }
    
}