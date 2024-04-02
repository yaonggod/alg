class Solution {
    public int solution(int n, int[] stations, int w) {
        int start = 1;
        int end = 1;
        int target = 2 * w + 1;
        int newst = 0;
        for (int s : stations) {
            end = s - w - 1;
            
            // start부터 end까지 구간 길이에 몇 개가 들어가는지
            if (start <= end) {
                newst += (end - start) / target + 1;
            }
            // 다음 구간
            start = s + w + 1;
        }
        end = n;
        if (start <= end) {
            newst += (end - start) / target + 1;
        }
        return newst;
    }
}