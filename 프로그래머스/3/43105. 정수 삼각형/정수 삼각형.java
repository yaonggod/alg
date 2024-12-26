class Solution {
    public int solution(int[][] triangle) {
        // 제일 마지막 행의 길이 1 2 3 4 ... n
        int n = triangle.length;
        
        // 두 번째 행부터 위의 행 값 참고해서 값 갱신
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                // 제일 왼쪽거
                if (j == 0) {
                    triangle[i][j] += triangle[i - 1][j];
                // 제일 오른쪽거
                } else if (j == i) {
                    triangle[i][j] += triangle[i - 1][j - 1];
                // 내 바로 위에거, 그 왼쪽거랑 비교
                } else {
                    if (triangle[i - 1][j - 1] > triangle[i - 1][j]) {
                        triangle[i][j] += triangle[i - 1][j - 1];
                    } else {
                        triangle[i][j] += triangle[i - 1][j];
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (triangle[n - 1][i] > ans) {
                ans = triangle[n - 1][i];
            }
        }
        return ans;
    }
}