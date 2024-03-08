import java.util.*;

class Solution {
    public int solution(int[] cookie) {
        int n = cookie.length;
        // 누적합
        int[] sumCookie = new int[n];
        sumCookie[0] = cookie[0];
        for (int i = 1; i < n; i++) {
            sumCookie[i] = sumCookie[i - 1] + cookie[i];
        }
        int result = 0;
        
        // mid를 기준으로 좌우로 나누기
        for (int mid = 0; mid < n - 1; mid++) {
            // 0 ~ mid
            int left = sumCookie[mid];
            // -> 저는 지금 여기에 있어요
            int leftIndex = 0;
            // mid + 1 ~ n - 1
            int right = sumCookie[n - 1] - sumCookie[mid];
            // <- 저는 지금 여기에 있어요
            int rightIndex = n - 1;
            // 이미 찾음 - 어차피 더 찾아도 이거보다 작음
            if (left == right && result < left) {
                result = left;
            // 좌우로 줄여보기
            } else {
                // result보다 작아지면 그만 찾기
                while (Math.max(left, right) >= result) {
                    // 찾음
                    if (left == right) {
                        if (result < left) result = left;
                        break;
                    // 오른쪽 줄이기
                    } else if (left < right) {
                        right += sumCookie[rightIndex - 1] - sumCookie[rightIndex];
                        rightIndex--;
                    // 왼쪽 줄이기
                    } else {
                        if (leftIndex == 0) {
                            left -= sumCookie[0];
                        } else {
                            left += sumCookie[leftIndex - 1] - sumCookie[leftIndex];
                        }
                        leftIndex++;
                    }
                }
            }
            
        }
        
        
        return result;
    }
}