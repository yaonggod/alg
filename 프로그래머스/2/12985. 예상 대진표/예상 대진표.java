class Solution {
    public int solution(int n, int a, int b) {
        // 3 -> 1
        // 1 -> 0
        // 0 -> 0
        // 6 -> 3
        // 3 -> 1
        // 1 -> 0
        int round = 0;
        int A = a - 1;
        int B = b - 1;
        while (A != B) {
            round++;
            A /= 2;
            B /= 2;
        }
        return round;
    }
}