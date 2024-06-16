import java.io.*;
import java.util.*;

public class Main {

    static int n, m, x, y, k;
    static int[][] map;

    // 위 아래 앞 뒤 우 좌
    static int[] dice = new int[] {0, 0, 0, 0, 0, 0};

    // 1 - 6 5 - 2 3 - 4
    // 왼쪽
    // 3 - 4 5 - 2 6 - 1
    // 오른쪽
    // 4 - 3 5 - 2 1 - 6
    // 위쪽
    // 5 - 2 6 - 1 3 - 4
    // 아래쪽
    // 2 - 5 1 - 6 3 - 4

    static int[] dx = new int[] {0, 0, -1, 1};
    static int[] dy = new int[] {1, -1, 0, 0};

    static int[] command;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dice[1] = map[x][y];

        command = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            command[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < k; i++) {
            doCommand(command[i]);
        }
        System.out.println(answer);
    }

    static void doCommand(int c) {
        int nx = x + dx[c - 1];
        int ny = y + dy[c - 1];

        // 이동하지 않는다
        if (nx < 0 || nx >= n || ny < 0 || ny >= m) return;

        x = nx;
        y = ny;
        int[] newDice = new int[6];

        // 1 - 6 5 - 2 3 - 4
        // 왼쪽
        // 3 - 4 5 - 2 6 - 1
        // 오른쪽
        // 4 - 3 5 - 2 1 - 6
        // 위쪽
        // 5 - 2 6 - 1 3 - 4
        // 아래쪽
        // 2 - 5 1 - 6 3 - 4
        if (c == 2) {
            newDice[0] = dice[4];
            newDice[1] = dice[5];
            newDice[2] = dice[2];
            newDice[3] = dice[3];
            newDice[4] = dice[1];
            newDice[5] = dice[0];
        } else if (c == 1) {
            newDice[0] = dice[5];
            newDice[1] = dice[4];
            newDice[2] = dice[2];
            newDice[3] = dice[3];
            newDice[4] = dice[0];
            newDice[5] = dice[1];
        } else if (c == 3) {
            newDice[0] = dice[2];
            newDice[1] = dice[3];
            newDice[2] = dice[1];
            newDice[3] = dice[0];
            newDice[4] = dice[4];
            newDice[5] = dice[5];
        } else if (c == 4) {
            newDice[0] = dice[3];
            newDice[1] = dice[2];
            newDice[2] = dice[0];
            newDice[3] = dice[1];
            newDice[4] = dice[4];
            newDice[5] = dice[5];
        }

        if (map[x][y] == 0) {
            map[x][y] = newDice[1];
        } else {
            newDice[1] = map[x][y];
            map[x][y] = 0;
        }
        dice = newDice;
        answer.append(dice[0]).append("\n");
    }
}