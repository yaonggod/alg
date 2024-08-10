import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        // 최대 전체 추의 합까지만 계산할 수 있음
        int maxCheck = 0;
        int[] ball = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            ball[i] = Integer.parseInt(st.nextToken());
            maxCheck += ball[i];
        }


        int m = Integer.parseInt(br.readLine());
        int[] check = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            check[i] = Integer.parseInt(st.nextToken());
        }

        boolean[] canCheck = new boolean[maxCheck + 1];
        canCheck[0] = true;

        // 추를 그대로 넣거나, 기존 무게에서 빼거나, 기존 무게에서 더하거나
        for (int b : ball) {
            Set<Integer> checkSet = new HashSet<>();
            for (int i = 0; i <= maxCheck; i++) {
                if (canCheck[i]) {
                    checkSet.add(i - b);
                    checkSet.add(i + b);
                    checkSet.add(b - i);
                }
            }
            for (Integer c : checkSet) {
                if (c >= 0 && c <= maxCheck) {
                    canCheck[c] = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int c : check) {
            if (c > maxCheck) {
                sb.append("N ");
            } else {
                sb.append(canCheck[c] ? "Y" : "N").append(" ");
            }
        }
        System.out.println(sb);
    }

}