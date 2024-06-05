import java.io.*;
import java.util.*;


public class Main {
    static int answer = -1;
    static int r, c, k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());

        int[][] matrix = new int[3][3];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if (r < 3 && c < 3 && matrix[r][c] == k) {
            System.out.println(0);
            return;
        }

        move(0, matrix);
        System.out.println(answer);
    }

    static void move(int count, int[][] board) {
//        System.out.println(count);
//
//        for (int i = 0; i < board.length; i++) {
//            System.out.println(Arrays.toString(board[i]));
//        }
//        System.out.println();



        if (r < board.length && c < board[0].length && board[r][c] == k) {
            answer = count;
            return;
        }

        if (count == 100) return;

        if (board.length >= board[0].length) {
            move(count + 1, R(board));
        } else {
            move(count + 1, C(board));
        }
    }

    static int[][] R(int[][] board) {
        int rCount = board.length;
        int maxRLen = board[0].length;
        List<PriorityQueue<Integer[]>> pqs = new ArrayList<>();
        for (int i = 0; i < rCount; i++) {
            pqs.add(new PriorityQueue<>(new Comparator<Integer[]>() {
                @Override
                public int compare(Integer[] o1, Integer[] o2) {
                    if (o1[1] == o2[1]) return o1[0] - o2[0];
                    return o1[1] - o2[1];
                }
            }));
        }

        for (int i = 0; i < rCount; i++) {
            Map<Integer, Integer> numbers = new HashMap<>();
            for (int j = 0; j < board[0].length; j++) {
                int num = board[i][j];
                if (num != 0) {
                    if (numbers.containsKey(num)) {
                        numbers.put(num, numbers.get(num) + 1);
                    } else {
                        numbers.put(num, 1);
                    }
                }

            }
            for (int n : numbers.keySet()) {
                pqs.get(i).offer(new Integer[]{n, numbers.get(n)});
            }
            maxRLen = Math.max(pqs.get(i).size() * 2, maxRLen);

        }


        int[][] newBoard = new int[rCount][maxRLen];
        for (int i = 0; i < rCount; i++) {
            int idx = 0;
            while (!pqs.get(i).isEmpty()) {
                Integer[] data = pqs.get(i).poll();
                newBoard[i][idx * 2] = data[0];
                newBoard[i][idx * 2 + 1] = data[1];
                idx++;
            }
        }
        return newBoard;
    }

    static int[][] C(int[][] board) {
        int cCount = board[0].length;
        int maxCLen = board.length;
        List<PriorityQueue<Integer[]>> pqs = new ArrayList<>();
        for (int i = 0; i < cCount; i++) {
            pqs.add(new PriorityQueue<>(new Comparator<Integer[]>() {
                @Override
                public int compare(Integer[] o1, Integer[] o2) {
                    if (o1[1] == o2[1]) return o1[0] - o2[0];
                    return o1[1] - o2[1];
                }
            }));
        }

        for (int i = 0; i < cCount; i++) {
            Map<Integer, Integer> numbers = new HashMap<>();
            for (int j = 0; j < board.length; j++) {
                int num = board[j][i];
                if (num != 0) {
                    if (numbers.containsKey(num)) {
                        numbers.put(num, numbers.get(num) + 1);
                    } else {
                        numbers.put(num, 1);
                    }
                }
            }
            for (int n : numbers.keySet()) {
                pqs.get(i).offer(new Integer[]{n, numbers.get(n)});
            }
            maxCLen = Math.max(pqs.get(i).size() * 2, maxCLen);
        }

        int[][] newBoard = new int[maxCLen][cCount];
        for (int i = 0; i < cCount; i++) {
            int idx = 0;
            while (!pqs.get(i).isEmpty()) {
                Integer[] data = pqs.get(i).poll();
                newBoard[idx * 2][i] = data[0];
                newBoard[idx * 2 + 1][i] = data[1];
                idx++;
            }
        }
        return newBoard;
    }
}