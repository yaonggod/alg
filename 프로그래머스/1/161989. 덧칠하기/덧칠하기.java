class Solution {
    public int solution(int n, int m, int[] section) {
        int count = 1;
        int index = 1;
        int start = section[0];
        
        while (index < section.length) {
            if (section[index] - start >= m) {
                count++;
                start = section[index];
            }
            index++;
        }
        return count;
    }
}