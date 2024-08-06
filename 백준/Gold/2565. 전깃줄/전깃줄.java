import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] coil = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            coil[i][0] = Integer.parseInt(st.nextToken());
            coil[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(coil, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        // 번호 순서대로 이어지게
        int[] arr = new int[n];
        arr[0] = 1;
        for (int i = 1; i < n; i++) {
            arr[i] = 1;
            for (int j = 0; j < i; j++) {
                if (coil[j][1] < coil[i][1]) {
                    arr[i] = Math.max(arr[i], arr[j] + 1);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, arr[i]);
        }
        System.out.println(n - ans);
    }

}