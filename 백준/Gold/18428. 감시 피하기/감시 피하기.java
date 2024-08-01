import java.io.*;
import java.util.*;

public class Main {
    static int n, student;
    static ArrayList<Integer[]> teacher = new ArrayList<>();
    static char[][] map;
    static boolean possible = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        for (int i = 0; i < n; i++) {
            char[] row = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                map[i][j] = row[j * 2];
                if (map[i][j] == 'S') {
                    student++;
                } else if (map[i][j] == 'T') {
                    teacher.add(new Integer[] {i, j});
                }
            }
        }

        putO(0, 0, -1);
        System.out.println(possible ? "YES" : "NO");

    }

     static void putO(int count, int i, int j) {
         // 가능하니까 그만돌아
         if (possible) return;

         // 장애물 다 설치함
         if (count == 3) {
            if (!checkStudent()) {
                possible = true;
            }
            return;
         }

         // 장애물 놓을 수 있음
         for (int b = j + 1; b < n; b++) {
             if (map[i][b] == 'X') {
                 map[i][b] = 'O';
                 putO(count + 1, i, b);
                 map[i][b] = 'X';
             }
         }
         for (int a = i + 1; a < n; a++) {
             for (int b = 0; b < n; b++) {
                 if (map[a][b] == 'X') {
                     map[a][b] = 'O';
                     putO(count + 1, i, b);
                     map[a][b] = 'X';
                 }
             }
         }
     }

     static boolean checkStudent() {
        int[] dx = new int[] {0, -1, 0, 1};
        int[] dy = new int[] {1, 0, -1, 0};

        for (Integer[] t : teacher) {
            for (int d = 0; d < 4; d++) {
                int nx = t[0] + dx[d];
                int ny = t[1] + dy[d];
                while (true) {
                    // 범위 밖이다
                    if (nx >= n || nx < 0 || ny >= n || ny < 0) break;

                    // 장애물이다
                    if (map[nx][ny] == 'O') break;

                    // 학생이다
                    if (map[nx][ny] == 'S') return true;

                    nx += dx[d];
                    ny += dy[d];
                }
            }
        }
        return false;
     }
}