class Solution {
    public long solution(int[] weights) {
        int[] weight = new int[1001];
        weight[weights[0]] = 1;
        
        long count = 0;
        int target = 0;
        for (int i = 1; i < weights.length; i++) {
            int w = weights[i];
            
            // 나랑 똑같은 사람
            count += weight[w];

            target = w * 2;
            if (target >= 100 && target <= 1000) count += weight[target];
            
            if (w % 2 == 0) {
                target = w / 2 * 3;
                if (target >= 100 && target <= 1000) count += weight[target];
                target = w / 2;
                if (target >= 100 && target <= 1000) count += weight[target];
            }
            
            if (w % 3 == 0) {
                target = w / 3 * 2;
                if (target >= 100 && target <= 1000) count += weight[target];
                target = w / 3 * 4;
                if (target >= 100 && target <= 1000) count += weight[target];
            }
            
            if (w % 4 == 0) {
                target = w / 4 * 3;
                if (target >= 100 && target <= 1000) count += weight[target];
            }
            
            weight[w]++;
        }
        return count;
    }
}