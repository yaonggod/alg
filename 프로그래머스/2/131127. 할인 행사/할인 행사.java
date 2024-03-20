import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        // 원하는 상품의 인덱스
        int w = want.length;
        Map<String, Integer> pIndex = new HashMap<>();
        for (int i = 0; i < w; i++) {
            pIndex.put(want[i], i);
        }
        // 첫째날에 장바구니에 있는거
        int[] bucket = new int[w];
        for (int i = 0; i < 10; i++) {
            if (pIndex.containsKey(discount[i])) {
                int pid = pIndex.get(discount[i]);
                bucket[pid]++;
            }
        }
        int enroll = 0;
        // 일치하는가
        boolean match = true;
        for (int i = 0; i < w; i++) {
            if (number[i] != bucket[i]) match = false;
        }
        if (match) enroll++;
        
        // 둘째날부터
        int days = discount.length - 10;
        for (int d = 0; d < days; d++) {
            // 장바구니에서 d를 빼고 d + 10을 넣음
            int put = -1;
            int delete = -2;
            if (pIndex.containsKey(discount[d])) {
                delete = pIndex.get(discount[d]);
            }
            if (pIndex.containsKey(discount[d + 10])) {
                put = pIndex.get(discount[d + 10]);
            }
            // 장바구니가 균형 상태이고, 나가고 들어오는 품목이 같음
            if (match && put == delete) {
                enroll++;
            // 불균형 상태
            } else {
                // 넣자
                if (put >= 0) {
                    bucket[put]++;
                }
                // 빼자
                if (delete >= 0) {
                    bucket[delete]--;
                }
                // 균형 검사하자
                match = true;
                for (int i = 0; i < w; i++) {
                    if (number[i] != bucket[i]) match = false;
                }
                if (match) enroll++;
            }
        }
        return enroll;
    }
}