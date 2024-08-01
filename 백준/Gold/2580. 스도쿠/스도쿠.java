import java.io.*;
import java.util.*;

public class Main {
    static int[][] sudoku = new int[9][9];
    static int[][] answer = new int[9][9];

    // 빈칸
    static int empty;
    static ArrayList<Integer[]> checklist = new ArrayList<>();
    static boolean done = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = Integer.parseInt(st.nextToken());
                if (sudoku[i][j] == 0) {
                    empty++;
                    checklist.add(new Integer[] {i, j});
                }
            }
        }

        play(0);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }

    }

    // 0번째 좌표를 체크해
    static void play(int idx) {
        if (done) return;

        // 다했음
        if (idx == empty) {
            done = true;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    answer[i][j] = sudoku[i][j];
                }
            }
            return;
        }

        int i = checklist.get(idx)[0];
        int j = checklist.get(idx)[1];
        boolean[] visited = new boolean[10];

        // 나온 숫자들 체크하기
        // 가로
        for (int k = 0; k < 9; k++) {
            if (k != j && sudoku[i][k] != 0) {
                visited[sudoku[i][k]] = true;
            }
        }
        // 세로
        for (int k = 0; k < 9; k++) {
            if (k != i && sudoku[k][j] != 0) {
                visited[sudoku[k][j]] = true;
            }
        }
        // 정사각형
        int a = (i / 3) * 3;
        int b = (j / 3) * 3;
        for (int x = a; x < a + 3; x++) {
            for (int y = b; y < b + 3; y++) {
                if (!(x == i && y == j) && sudoku[x][y] != 0) {
                    visited[sudoku[x][y]] = true;
                }
            }
        }

        // 내가 갈 수 있는 점들 찾아서 가보기
        for (int dot = 1; dot <= 9; dot++) {
            if (!visited[dot]) {
                // dot으로 채우고 play
                visited[dot] = true;
                sudoku[i][j] = dot;
                play(idx + 1);

                // 원복
                sudoku[i][j] = 0;
                visited[dot] = false;
            }
        }
    }


}