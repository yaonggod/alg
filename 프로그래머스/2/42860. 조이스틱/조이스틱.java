import java.util.*;

class Solution {
    public int solution(String name) {
        int n = name.length();
        char[] nameArr = name.toCharArray();
        int horizonLen = Integer.MAX_VALUE;
        int ans = 0;
        
        // 가봐야 할 인덱스 왼쪽과 오른쪽을 정해서
        List<Integer> visitIdx = new ArrayList<>();
       
        int[] updown = new int[26];
        // 다음 알파벳
        for (int i = 0; i < 26; i++) {
            updown[i] = i;
        }
        // 이전 알파벳
        for (int i = 1; i < 26; i++) {
            updown[26 - i] = Math.min(i, updown[26 - i]);
        }
        
        for (int i = 0; i < n; i++) {
            if (nameArr[i] != 'A') {
                // 알파벳 바꾸는데 쓰는 조작 
                ans += updown[nameArr[i] - 'A'];
                if (i != 0) {
                    visitIdx.add(i);
                }
            }
        }
        
        // 첫번째 말고 바꿀 알파벳이 없다
        if (visitIdx.size() == 0) return ans;
        
        // 한개만 더
        if (visitIdx.size() == 1) return ans + Math.min(visitIdx.get(0), n - visitIdx.get(0));
        
        // 왼쪽으로 쭉 가거나
        // A 아닌 알파벳이 나오는 곳 까지만 가면 됨
        horizonLen = visitIdx.get(visitIdx.size() - 1);
        
        // 오른쪽으로 쭉 가거나
        // 뒤로 가기 + 0번째 말고 A 아닌게 나오면
        horizonLen = Math.min(horizonLen, n - visitIdx.get(0));
        
        // 왼 + 오 합하거나 
        for (int i = 0; i < visitIdx.size() - 1; i++) {
            int left = visitIdx.get(i);
            int right = visitIdx.get(i + 1);
            
            // 뒤에 먼저 왔다간다
            int back = 1 + (n - right - 1) * 2 + 1 + left;
            
            // 앞에 먼저 왔다간다
            int front = left * 2 + 1 + (n - right - 1);
            
            horizonLen = Math.min(horizonLen, Math.min(back, front));
        }
        
        return ans + horizonLen;
    }
}