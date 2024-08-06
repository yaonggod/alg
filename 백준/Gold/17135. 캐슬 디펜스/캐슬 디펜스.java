import java.io.*;
import java.util.*;

public class Main {
    static int n, m, len;
    static int[][] castle;
    static int[][] copyCastle;
    // 적
    static int enemy;
    static int maxDelete = 0;

    // 일단 한 칸 위로 간 다음에 왼, 위, 오
    static int[] dx = new int[] {0, -1, 0};
    static int[] dy = new int[] {-1, 0, 1};
    // 궁수 위치
    static int[] shoot;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        len = Integer.parseInt(st.nextToken());
        castle = new int[n][m];
        copyCastle = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                castle[i][j] = Integer.parseInt(st.nextToken());
                enemy += castle[i][j];
            }
        }

        for (int i = 0; i < m - 2; i++) {
            for (int j = i + 1; j < m - 1; j++) {
                for (int k = j + 1; k < m; k++) {
                    shoot = new int[] {i, j, k};
                    renewCastle();
                    attack();
                }
            }
        }
        System.out.println(maxDelete);
    }

    static void renewCastle() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copyCastle[i][j] = castle[i][j];
            }
        }
    }
    static void attack() {
        int remain = enemy;
        int attacked = 0;
        // 같은 적을 여러 명이 공격할 수 있음
        while (remain > 0) {
            // 지울 사람
            ArrayList<Integer[]> deleted = new ArrayList<>();
            for (int s : shoot) {
                Integer[] found = findAttack(s);
                if (!(found[0] == -1 && found[1] == -1)) {
                    deleted.add(found);
                }
            }
            // 쏴서 지우기
            for (Integer[] del : deleted) {
                if (copyCastle[del[0]][del[1]] == 1) {
                    copyCastle[del[0]][del[1]] = 0;
                    remain--;
                    attacked++;
                }
            }
            // 아래로 한 칸 내리기
            for (int i = 0; i < m; i++) {
                if (copyCastle[n - 1][i] == 1) remain--;
            }
            for (int i = n - 1; i >= 1; i--) {
                for (int j = 0; j < m; j++) {
                    copyCastle[i][j] = copyCastle[i - 1][j];
                }
            }
            for (int i = 0; i < m; i++) {
                copyCastle[0][i] = 0;
            }
        }
        maxDelete = Math.max(maxDelete, attacked);
    }

    static Integer[] findAttack(int s) {
        for (int d = 1; d <= len; d++) {
            for (int j = s - (d - 1); j <= s + (d + 1); j++) {
                int i = n - d + Math.abs(j - s);
                if (range(i, j) && copyCastle[i][j] == 1) {
                    return new Integer[] {i, j};
                }
            }
        }
        return new Integer[] {-1, -1};
    }


    static boolean range(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}