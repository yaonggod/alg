class Solution {
    public int solution(int[] arr) {
        int answer = arr[0];
        for (int i = 1; i < arr.length; i++) {
            answer = lcm(answer, arr[i]);
        }
        return answer;
    }
    
    public int gcd(int a, int b) {
        if (a == 0) return b;
        if (a > b) return gcd(a % b, b);
        return gcd(b % a, a);
    }
    
    public int lcm(int a, int b) {
        int g = gcd(a, b);
        return a * b / g;
    }
}