class Solution {
    public long solution(int w, int h) {
        int g = gcd(w, h);
        // 가장 작은 단위의 직사각형으로 나누기
        int sw = w / g;
        int sh = h / g;
        long count = (long) w * (long) h;
        long square = 0;
        for (int i = 1; i <= sh; i++) {
            double before = (i - 1) * 1.0 * sw / sh;
            double after = i * 1.0 * sw / sh;
            square += (long) Math.ceil(after) - (long) Math.floor(before);
        }
        return count - square * g;
    }
    
    public static int gcd(int a, int b) {
        if (a % b == 0) return b;
        if (b % a == 0) return a;
        if (a > b) return gcd(a % b, b);
        return gcd(a, b % a);
    }
}