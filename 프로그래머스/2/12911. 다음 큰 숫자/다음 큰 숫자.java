class Solution {
    public int solution(int n) {
        int target = countOne(n);
        n++;
        while (true) {
            if (countOne(n) == target) break;
            n++;
        }
        return n;
    }
    
    static int countOne(int n) {
        int count = 0;
        while (n != 0) {
            if (n % 2 == 1) count++;
            n /= 2;
        }
        return count;
    }
}