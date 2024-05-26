class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;
        
        while (n >= a) {
            int give = (n / a) * a;
            int take = (n / a) * b;
            answer += take;
            n += (take - give);
        }
        return answer;
    }
}