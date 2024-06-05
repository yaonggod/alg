import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static int maxVal = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                maxVal = Math.max(board[i][j], maxVal);
            }
        }

        move(0, board);
        System.out.println(maxVal);
    }

    static void move(int count, int[][] board) {
        if (count == 5) {
            // 최대 수를 구하기
            return;
        }

        move(count + 1, up(board));
        move(count + 1, down(board));
        move(count + 1, left(board));
        move(count + 1, right(board));
    }

    static int[][] up(int[][] board) {
        List<Stack<Integer>> stacks = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            stacks.add(new Stack<>());
        }

        for (int i = 0; i < N; i++) {
            if (board[0][i] != 0) {
                stacks.get(i).push(board[0][i]);
            }

            // 합쳐졌으니 다음거는 무조건 push해야한다
            boolean push = false;

            for (int j = 1; j < N; j++) {
                int now = board[j][i];
                if (now != 0) {
                    if (!push && !stacks.get(i).isEmpty() && stacks.get(i).peek() == now) {
                        stacks.get(i).pop();
                        stacks.get(i).push(now * 2);
                        maxVal = Math.max(now * 2, maxVal);
                        push = true;
                    } else {
                        stacks.get(i).push(now);
                        push = false;
                    }
                }
            }
        }
        int[][] newBoard = new int[N][N];

        for (int i = 0; i < N; i++) {
            int idx = stacks.get(i).size() - 1;
            while (idx >= 0) {
                newBoard[idx][i] = stacks.get(i).pop();
                idx--;
            }
        }

        return newBoard;
    }
    static int[][] down(int[][] board) {
        List<Stack<Integer>> stacks = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            stacks.add(new Stack<>());
        }

        for (int i = 0; i < N; i++) {
            if (board[N - 1][i] != 0) {
                stacks.get(i).push(board[N - 1][i]);
            }

            // 합쳐졌으니 다음거는 무조건 push해야한다
            boolean push = false;

            for (int j = N - 2; j >= 0; j--) {
                int now = board[j][i];
                if (now != 0) {
                    if (!push && !stacks.get(i).isEmpty() && stacks.get(i).peek() == now) {
                        stacks.get(i).pop();
                        stacks.get(i).push(now * 2);
                        maxVal = Math.max(now * 2, maxVal);
                        push = true;
                    } else {
                        stacks.get(i).push(now);
                        push = false;
                    }
                }
            }
        }
        int[][] newBoard = new int[N][N];

        for (int i = 0; i < N; i++) {
            int idx = N - stacks.get(i).size();
            while (idx < N) {
                newBoard[idx][i] = stacks.get(i).pop();
                idx++;
            }
        }

        return newBoard;
    }
    static int[][] left(int[][] board) {
        List<Stack<Integer>> stacks = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            stacks.add(new Stack<>());
        }

        for (int i = 0; i < N; i++) {
            if (board[i][0] != 0) {
                stacks.get(i).push(board[i][0]);
            }

            // 합쳐졌으니 다음거는 무조건 push해야한다
            boolean push = false;

            for (int j = 1; j < N; j++) {
                int now = board[i][j];
                if (now != 0) {
                    if (!push && !stacks.get(i).isEmpty() && stacks.get(i).peek() == now) {
                        stacks.get(i).pop();
                        stacks.get(i).push(now * 2);
                        maxVal = Math.max(now * 2, maxVal);
                        push = true;
                    } else {
                        stacks.get(i).push(now);
                        push = false;
                    }
                }
            }
        }
        int[][] newBoard = new int[N][N];

        for (int i = 0; i < N; i++) {
            int idx = stacks.get(i).size() - 1;
            while (idx >= 0) {
                newBoard[i][idx] = stacks.get(i).pop();
                idx--;
            }
        }

        return newBoard;
    }
    static int[][] right(int[][] board) {
        List<Stack<Integer>> stacks = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            stacks.add(new Stack<>());
        }

        for (int i = 0; i < N; i++) {
            if (board[i][N - 1] != 0) {
                stacks.get(i).push(board[i][N - 1]);
            }

            // 합쳐졌으니 다음거는 무조건 push해야한다
            boolean push = false;

            for (int j = N - 2; j >= 0; j--) {
                int now = board[i][j];
                if (now != 0) {
                    if (!push && !stacks.get(i).isEmpty() && stacks.get(i).peek() == now) {
                        stacks.get(i).pop();
                        stacks.get(i).push(now * 2);
                        maxVal = Math.max(now * 2, maxVal);
                        push = true;
                    } else {
                        stacks.get(i).push(now);
                        push = false;
                    }
                }
            }
        }
        int[][] newBoard = new int[N][N];

        for (int i = 0; i < N; i++) {
            int idx = N - stacks.get(i).size();
            while (idx < N) {
                newBoard[i][idx] = stacks.get(i).pop();
                idx++;
            }
        }

        return newBoard;
    }
}