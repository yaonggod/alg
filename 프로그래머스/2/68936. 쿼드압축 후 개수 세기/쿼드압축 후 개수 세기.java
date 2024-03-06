import java.util.*;

class Solution {
    static int[][] A;
    static int[] answer;
    public int[] solution(int[][] arr) {
        A = arr;
        answer = new int[] {0, 0};
        quadTree(A.length, 0, 0);
        return answer;
    }
    
    static int quadTree(int n, int x, int y) {
        if (n == 1) {
            if (A[x][y] == 0) {
                answer[0]++;
                return 0;
            }
            else if (A[x][y] == 1) {
                answer[1]++;
                return 1;
            }
        } else {
            int one = quadTree(n / 2, x, y);
            int two = quadTree(n / 2, x, y + n / 2);
            int three = quadTree(n / 2, x + n / 2, y);
            int four = quadTree(n / 2, x + n / 2, y + n / 2);
            if (one + two + three + four == 4) {
                answer[1] -= 3;
                return 1;
            } else if (one + two + three + four == 0) {
                answer[0] -= 3;
                return 0;
            }
        }
        return -1000;
    }
}