import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int wanhoa = scores[0][0];
        int wanhob = scores[0][1];
        // a 내림차순 b 오름차순
        // 무조건 뒤에 있는 놈이 a가 낮거나 같은데 b는 오름차순이라 앞에 나왔던 b만 비교
        // 5 4 / 4 1 / 4 2 / 4 3 앞에서 출현했던 4로 뒤에 셋을 모두 탈락시킴 
        Arrays.sort(scores, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) return a[1] - b[1];
                return b[0] - a[0];
            }
        });
        int n = scores.length;
        int maxB = 0;
        int rank = 1;
        for (int i = 0; i < n; i++) {
            // 밴
            if (scores[i][1] < maxB) {
                if (scores[i][0] == wanhoa && scores[i][1] == wanhob) return -1;                 
            } else {
                maxB = scores[i][1];
                if (scores[i][0] + scores[i][1] > wanhoa + wanhob) rank++;
            }
        }
        return rank;
    }
}