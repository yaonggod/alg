import java.util.*;

class Solution {
    public int[] solution(long begin, long end) {
        int[] answer = new int[(int) (end - begin + 1)];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = yaksu((int) (begin + i));
        }
        return answer;
    }
    
    // 가장 큰 약수를 찾는겨
    // 근데 최대 10000000
    static int yaksu(int n) {
        if (n == 1) return 0;
        int div = 2;
        double last = Math.sqrt(n);
        int answer = 1;
        while (div <= last) {
            if (n % div == 0) {
                if (n / div <= 10000000 && answer < n / div) {
                    answer = n / div;
                } 
                if (div <= 10000000 && answer < div) {
                    answer = div;
                }
            }
            div++;
        }
        return answer;
    }
}