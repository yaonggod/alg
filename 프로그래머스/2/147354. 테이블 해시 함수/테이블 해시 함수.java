import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        Arrays.sort(data, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[col - 1] == b[col - 1]) return b[0] - a[0];
                return a[col - 1] - b[col - 1];
            }
        });
        int answer = -1;
        for (int i = row_begin - 1; i < row_end; i++) {
            int after = 0;
            for (int j = 0; j < data[0].length; j++) {
                after += data[i][j] % (i + 1);
            }
            if (answer != -1) {
                answer = answer ^ after;
            } else {
                answer = after;
            }
        }
        return answer;
    }
}