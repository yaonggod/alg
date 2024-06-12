import java.io.*;
import java.util.*;


public class Main {
    static int n;

    static int[] dx = new int[] {0, 1, 0, -1};
    static int[] dy = new int[] {1, 0, -1, 0};

    static int[][] table;
    static int[][] student;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        table = new int[n][n];
        student = new int[n * n][5];

        Map<Integer, Integer> stIdx = new HashMap<>();

        for (int i = 0; i < n * n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                student[i][j] = Integer.parseInt(st.nextToken());
                if (j == 0) {
                    stIdx.put(student[i][j], i);
                }
            }
        }

        int satisfy = 0;
        for (int s = 0; s < n * n; s++) {
            int x = -1;
            int y = -1;
            int like = -1;
            int empty = -1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (table[i][j] != 0) continue;
                    // 비었음
                    int e = 0;
                    // 좋아함
                    int l = 0;
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                            // 비었냐
                            if (table[nx][ny] == 0) e++;
                            // 좋아하냐
                            else {
                                for (int k = 1; k <= 4; k++) {
                                    if (table[nx][ny] == student[s][k]) l++;
                                }
                            }
                        }
                    }
                    // 1번
                    if (l > like) {
                        x = i;
                        y = j;
                        like = l;
                        empty = e;
                    // 2번
                    } else if (l == like && e > empty) {
                        x = i;
                        y = j;
                        empty = e;
                    }
                }
            }
            table[x][y] = student[s][0];
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int stno = stIdx.get(table[i][j]);
                int stsati = 0;
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                        for (int e = 1; e < 5; e++) {
                            if (student[stno][e] == table[nx][ny]) stsati++;
                        }
                    }
                }
                if (stsati > 0) satisfy += (int) Math.pow(10, stsati - 1);
            }
        }

        System.out.println(satisfy);
    }




}