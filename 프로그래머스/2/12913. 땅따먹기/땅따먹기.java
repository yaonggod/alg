class Solution {
    int solution(int[][] land) {
        for (int i = 1; i < land.length; i++) {
            // 그 행에서 가장 큰 숫자 인덱스와 두번째로 큰 숫자 인덱스
            int[] idx = maxArr(land[i - 1]);
            for (int j = 0; j < 4; j++) {
                // 바로 위에 숫자가 제일 크면 두 번째 큰거 가져오기
                if (j == idx[0]) {
                    land[i][j] += land[i - 1][idx[1]];
                } else {
                    land[i][j] += land[i - 1][idx[0]];
                }
            }
        }
        int result = 0;
        for (int i = 0; i < 4; i++) {
            if (land[land.length - 1][i] > result) result = land[land.length - 1][i];
        }
        return result;
    }
    
    public int[] maxArr(int[] row) {
        int max1 = row[0];
        int first = 0;
        int max2 = 0;
        int second = 0;
        for (int i = 1; i < 4; i++) {
            if (row[i] > max1) {
                max2 = max1;
                max1 = row[i];
                second = first;
                first = i;
            } else if (row[i] > max2) {
                max2 = row[i];
                second = i;
            }
        }
        return new int[] {first, second};
    }
}