import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int n, l, current, count;
    static boolean possible, road;
    static int[][] board;
    static boolean[] visited;

    static int maxSize = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;


        // 가로
        for (int i = 0; i < n; i++) {
            possible = true;
            // 지금 나오고 있는 것은 이거입니다
            current = board[i][0];
            // 몇 개 나왔습니다
            count = 1;
            // 도로를 깔고 있습니다
            road = false;
            for (int j = 1; j < n; j++) {
                // 같은거 나오는 중
                if (board[i][j] == current) {
                    count++;
                // 한 칸 낮아졌음
                } else if (board[i][j] - current == -1) {
                    // 도로를 깔고 있던 중임
                    if (road) {
                        possible = false;
                        break;
                    // 도로를 깔기 시작하자
                    } else {
                        road = true;
                        count = 1;
                        current = board[i][j];
                    }
                // 높아졌음
                } else if (board[i][j] - current == 1) {
                    // 이전에 나오던거를 활용해서 도로를 깔 수 있을까
                    if (!road && count >= l) {
                        count = 1;
                        current = board[i][j];
                    } else {
                        possible = false;
                        break;
                    }
                // 높이차가 심함
                } else {
                    possible = false;
                    break;
                }
                // 도로 다 깔았음
                if (road && count == l) {
                    road = false;
                    count = 0;
                }
            }
            // 깔고있는데 멈췄음
            if (road && count < l) {
                possible = false;
            }
            if (possible) answer++;
        }

        // 세로
        for (int i = 0; i < n; i++) {
            possible = true;
            // 지금 나오고 있는 것은 이거입니다
            current = board[0][i];
            // 몇 개 나왔습니다
            count = 1;
            // 도로를 깔고 있습니다
            road = false;
            for (int j = 1; j < n; j++) {
                // 같은거 나오는 중
                if (board[j][i] == current) {
                    count++;
                    // 한 칸 낮아졌음
                } else if (board[j][i] - current == -1) {
                    // 도로를 깔고 있던 중임
                    if (road) {
                        possible = false;
                        break;
                        // 도로를 깔기 시작하자
                    } else {
                        road = true;
                        count = 1;
                        current = board[j][i];
                    }
                    // 높아졌음
                } else if (board[j][i] - current == 1) {
                    // 이전에 나오던거를 활용해서 도로를 깔 수 있을까
                    if (!road && count >= l) {
                        count = 1;
                        current = board[j][i];
                    } else {
                        possible = false;
                        break;
                    }
                    // 높이차가 심함
                } else {
                    possible = false;
                    break;
                }
                // 도로 다 깔았음
                if (road && count == l) {
                    road = false;
                    count = 0;
                }
            }
            // 깔고있는데 멈췄음
            if (road && count < l) {
                possible = false;
            }
            if (possible) answer++;
        }
        System.out.println(answer);
    }


}