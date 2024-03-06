import java.util.*;

class Solution {
    public int[] solution(int[][] arr) {
        int[] answer = new int[] {0, 0};
        int m = (int) (Math.log10(arr.length) / Math.log10(2));
        for (int k = 1; k <= m; k++) {
            // 체크할 수 == 그 칸은 1이 몇 개가 모여서 압축되었다
            // 칸은 2^k씩 점핑하는데 그럼 거기에는 2^(2 * (k - 1))개씩 모일거임
            int checkOne = (int) Math.pow(2, 2 * (k - 1));
            // 0은 마이너스로 모아서 기록하는데 첫 번째면 0으로 확인하기
            int checkZero = -checkOne;
            if (checkOne == 1) checkZero = 0;
            // 정사각형 내에서 이만큼 뛰어서 검사할거다
            int leap = (int) Math.pow(2, k - 1);
            // 이거를 기준으로 정사각형을 나눌거다
            int divide = (int) Math.pow(2, k);
            // 정사각형 나누기
            for (int i = 0; i < arr.length; i += divide) {
                for (int j = 0; j < arr.length; j += divide) {
                    // 그 안에서 순회하기 - 4개를 돌게 됨
                    int zero = 0;
                    int one = 0;
                    for (int a = i; a < i + divide; a += leap) {
                        for (int b = j; b < j + divide; b += leap) {
                            // 만약에 k == 1이면 처음 도는거라서 카운트하기
                            if (k == 1) {
                                if (arr[a][b] == 1) answer[1]++;
                                else if (arr[a][b] == 0) answer[0]++;
                            }
                            if (arr[a][b] == checkZero) {
                                zero++;
                            } else if (arr[a][b] == checkOne) {
                                one++;
                            } 
                        }
                    }
                    // 0이나 1이 일관되게 나왔다
                    if (one == 4) {
                        // 1만 나왔으면 기존 1을 4개를 모아서 1이 되었으므로
                        // 그러면 checkOne * 4해서 첫 칸에 넣음
                        answer[1] -= 3;
                        arr[i][j] = checkOne * 4;
                    } else if (zero == 4) {
                        answer[0] -= 3;
                        if (checkZero == 0) {
                            arr[i][j] = -4;
                        } else {
                            arr[i][j] = checkZero * 4;                            
                        }
                    }
                }
            }
        }
        return answer;
    }
}