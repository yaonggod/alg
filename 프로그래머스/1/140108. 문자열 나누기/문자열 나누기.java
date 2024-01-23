class Solution {
    public int solution(String s) {
        int x = 0;
        int equalsx = 0;
        int nonx = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            // 새 시작
            if (equalsx == 0 && nonx == 0) {
                count++;
                x = (int) s.charAt(i);
                equalsx++;
            } 
            else if (x == (int) s.charAt(i)) equalsx++;
            else if (x != (int) s.charAt(i)) nonx++;
            // 자른다
            if (equalsx == nonx) {
                equalsx = 0;
                nonx = 0;
            }
        }
        return count;
    }
}