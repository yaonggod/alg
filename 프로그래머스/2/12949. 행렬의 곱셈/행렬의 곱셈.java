class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int a = arr1.length;
        // b == c
        int b = arr1[0].length;
        int c = arr2.length;
        int d = arr2[0].length;
        int[][] answer = new int[a][d];
        
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < d; j++) {
                // arr1[i][0] ~ arr1[i][b - 1]
                // arr2[0][j] ~ arr2[b - 1][j]
                int sum = 0;
                for (int x = 0; x < b; x++) {
                    sum += arr1[i][x] * arr2[x][j]; 
                }
                answer[i][j] = sum;
            }
        }
        return answer;
    }
}