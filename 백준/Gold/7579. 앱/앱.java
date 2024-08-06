import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] app = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            app[i] = Integer.parseInt(st.nextToken());
        }
        int[] cost = new int[n];
        int totalCost = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            totalCost += cost[i];
        }
        // cost만큼 들여서 이만큼의 메모리를 확보했다
        int[] memory = new int[totalCost + 1];
        for (int i = 0; i < n; i++) {
            int a = app[i];
            int c = cost[i];
            int[] memory2 = new int[memory.length];

            for (int j = 0; j < c; j++) {
                memory2[j] = memory[j];
            }
            // 앞에 기록들에서 c만큼 비용 추가해서 a만큼 더 확보하기
            memory2[c] = Math.max(memory[0] + a, memory[c]);
            for (int j = c + 1; j < memory2.length; j++) {
                memory2[j] = memory[j];
                if (memory[j - c] > 0) {
                    memory2[j] = Math.max(memory[j - c] + a, memory[j]);
                }
            }
            memory = memory2;
        }
        for (int i = 0; i <= totalCost; i++) {
            if (memory[i] >= m) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }

}