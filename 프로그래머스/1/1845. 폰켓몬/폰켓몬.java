import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int count = nums.length / 2;
        Set<Integer> pkm = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            pkm.add(nums[i]);
            if (pkm.size() == count) {
                break;
            }
        }
        return pkm.size();
    }
}