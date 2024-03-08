class Solution {
    public int solution(int[] nums) {
        int max = 0;
        for (int num : nums) {
            if (num > max) max = num;
        }
        int n = nums.length;
        boolean[] sosu = new boolean[3 * max];
        // 체 만들기 - false면 소수
        sosu[0] = true;
        sosu[1] = true;
        for (int i = 2; i < 3 * max; i++) {
            if (!sosu[i]) {
                int m = 2;
                while (i * m < 3 * max) {
                    sosu[i * m] = true;
                    m++;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (!sosu[nums[i] + nums[j] + nums[k]]) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}