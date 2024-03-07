import java.util.*;

class Solution {
    public long[] solution(long[] numbers) {
        long[] result = new long[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            result[i] = f(numbers[i]);
        }
        return result;
    }
    
    static long f(long n) {
        // 짝수는 맨 끝의 비트를 1로 바꿔주기
        if (n % 2 == 0) return n + 1;
        
        // 홀수면 가장 먼저 나오는 0을 1로 바꾸고 직전 1을 0으로 바꾸기
        long n2 = n;
        long m = 0;
        while (n2 != 0) {
            if (n2 % 2 == 0) {
                break;
            } else {
                m++;
                n2 /= 2;
            }
        }
        return n + (long) Math.pow(2, m - 1);
    }
}