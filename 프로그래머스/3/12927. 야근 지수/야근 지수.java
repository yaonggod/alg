import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        // 1. 평탄화하기
        // 2. a - b 차이를 최소화하기
        // [4, 8] 에서 2를 빼야하면 [5, 5]를 만들수는 없으므로 [4, 6]으로 만들기
        
        // 평탄화할 경우 일의 최소값 구하기
        int l = works.length;
        int total = -n;
        for (int i = 0; i < l; i++) {
            total += works[i];
        }
        
        // 일을 끝낼 수 있으면 더 볼 필요 없음
        if (total <= 0) return 0;
        
        // 평탄화
        // 그럼 한 칸당 minWork씩 하게 되고    
        int minWork = total / l;   
        
        // 그런데 이미 minWork보다 적게 일하고 있으면 패스하고
        int decrease = 0;
        Arrays.sort(works);
        int[] answer = new int[l];
        for (int i = 0; i < l; i++) {
            if (works[i] >= minWork) {
                answer[i] = minWork;
                decrease += works[i] - minWork;              
            } else {
                answer[i] = works[i];
            }
        }
        // 너무 많이 줄였음
        int increase = 0;
        if (decrease > n) {
            increase = decrease - n;
            // 다시 늘리기
            // 원래 work값 이내로 뒤에서부터 한개씩 넣기 
            int index = l - 1;
            while (increase > 0) {
                if (works[index] > answer[index]) {
                    answer[index]++;
                    index--;
                    increase--;
                } else {
                    index = l - 1;
                }
            }
        }
        long result = 0;
        for (int i = 0; i < l; i++) {
            result += answer[i] * answer[i];
        }
        return result;
    }
}