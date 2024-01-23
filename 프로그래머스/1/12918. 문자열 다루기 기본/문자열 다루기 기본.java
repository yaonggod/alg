class Solution {
    public boolean solution(String s) {
        if (s.length() != 4 && s.length() != 6) return false;
        char[] str = s.toCharArray();
        // 48 57
        for (int i = 0; i < str.length; i++) {
            if ((int) str[i] > 57 || (int) str[i] < 48) {
                return false;
            }
        }
        return true;
    }
}