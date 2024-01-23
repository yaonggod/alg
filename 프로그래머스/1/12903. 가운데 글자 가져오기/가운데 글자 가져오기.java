class Solution {
    public String solution(String s) {
        String answer = "";
        char[] sArr = s.toCharArray();
        if (s.length() % 2 == 0) {
            answer += sArr[s.length() / 2 - 1];
            answer += sArr[s.length() / 2];
        } else {
            answer += sArr[s.length() / 2];
        }
        
        return answer;
    }
}