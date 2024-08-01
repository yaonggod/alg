import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int answer = 0;
    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        visited = new boolean[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            visited[i] = true;
            gap(0, 0, i);
            visited[i] = false;
        }

        System.out.println(answer);

    }

    static void gap(int count, int sum, int idx) {
        if (count == n - 1){
            answer = Math.max(sum, answer);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                gap(count + 1, sum + Math.abs(arr[idx] - arr[i]), i);
                visited[i] = false;
            }
        }
    }




}