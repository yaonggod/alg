class Solution {
    public int solution(int n) {
        String three = "";
        while (n != 0) {
            three += n % 3;
            n /= 3;
        }
        int answer = 0;
        int multiple = 1;
        for (int i = three.length() - 1; i >= 0; i--) {
            int digit = Integer.parseInt(three.substring(i, i + 1));
            answer += multiple * digit;
            multiple *= 3;
        }
        return answer;
    }
}