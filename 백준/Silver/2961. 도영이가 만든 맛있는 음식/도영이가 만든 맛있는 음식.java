import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static int[][] taste;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        taste = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            taste[i][0] = Integer.parseInt(st.nextToken());
            taste[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < (1 << N); i++) {
            int sour = 1;
            int bitter = 0;
            for (int bit = 0; bit < N; bit++) {
                if ((i & 1 << bit) != 0) {
                    sour *= taste[bit][0];
                    bitter += taste[bit][1];
                }
            }
            if (Math.abs(sour - bitter) < answer) answer = Math.abs(sour - bitter);
        }
        System.out.println(answer);
    }
}