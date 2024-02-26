class Solution {
    static int size;
    static int[] answer;
    public int[] solution(int n, long left, long right) {
        size = n;
        answer = new int[(int) (right - left + 1)];
        
        for (long i = left; i <= right; i++) {
            answer[(int) (i - left)] = num(i);
        }
        return answer;        
    }
    
    static int num(long index) {
        int x = (int) (index / (long) size);
        int y = (int) (index % (long) size);
        // x번째줄이면 x + 1이 x + 1개 나오고 하나씩 증가
        if (y <= x) return x + 1;
        return y + 1;
    }
}