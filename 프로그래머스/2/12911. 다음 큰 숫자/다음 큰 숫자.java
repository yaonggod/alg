class Solution {
    public int solution(int n) {
        int target = Integer.bitCount(n);
        n++;
        while (true) {
            if (Integer.bitCount(n) == target) break;
            n++;
        }
        return n;
    }
    
}