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
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 둘다 양수
        if (arr[0] >= 0 && arr[n - 1] >= 0) {
            System.out.println(arr[0] + " " + arr[1]);
            return;
        }

        // 둘다 음수
        if (arr[0] <= 0 && arr[n - 1] <= 0) {
            System.out.println(arr[n - 2] + " " + arr[n - 1]);
            return;
        }

        int min = Math.abs(arr[0] + arr[n - 1]);
        int start = 0;
        int end = n - 1;

        int a1 = 0;
        int a2 = n - 1;

        while (start < end) {
            int sum = arr[start] + arr[end];
            if (Math.abs(sum) < min) {
                min = Math.abs(sum);
                a1 = start;
                a2 = end;
            }

            if (sum == 0) break;

            else if (sum < 0) {
                start++;
            }

            else {
                end--;
            }
        }

        System.out.println(arr[a1] + " " + arr[a2]);
    }


}