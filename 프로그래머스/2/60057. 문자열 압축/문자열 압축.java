class Solution {
    public int solution(String s) {
        int n = s.length();
        
        int divide = n / 2;
        
        int len = n;
        
        // d개 단위로 나눠보자 
        for (int d = divide; d >= 1; d--) {
            StringBuilder sb = new StringBuilder();
            int start = d;
            // 몇개 쌓였는지
            int count = 1;
            // 비교 대상 d글자짜리 문자
            String target = s.substring(0, d);
            while (true) {
                // 범위 넘어서 검사할 수 없음 남은 거 다 붙이기 
                if (start + d > n) {
                    if (count > 1) sb.append(count);
                    sb.append(target);
                    
                    sb.append(s.substring(start, n));
                    break;
                }
                // 지금 위치 d글자짜리 문자
                String next = s.substring(start, start + d);
                if (target.equals(next)) {
                    count++;
                // 다른게 나오면 이전까지 나왔던 문자 붙이기
                } else {
                    if (count > 1) sb.append(count);
                    sb.append(target);
                    // next로 다시 시작 
                    target = next;
                    count = 1;
                }
                start += d;
            }
            if (sb.length() < len) {
                
                len = sb.length();
            }
        }
        return len;
    }
}