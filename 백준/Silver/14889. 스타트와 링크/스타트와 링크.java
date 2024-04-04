import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int n;
    static int[][] power;
    static boolean[] joined;
    static int[] teamA;
    static int[] teamB;
    // 차이의 최소
    static int minGap = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        power = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                power[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        joined = new boolean[n];
        teamA = new int[n / 2];
        teamB = new int[n / 2];

        team(0, 0);
        System.out.println(minGap);
    }

    // idx번째 사람을 넣을까말까, 지금까지 몇 명 모임
    static void team(int idx, int count) {
        // 다 모였음
        if (count == n / 2) {
            int aIdx = 0;
            int bIdx = 0;
            for (int i = 0; i < n; i++) {
                if (joined[i]) {
                    teamA[aIdx++] = i;
                } else {
                    teamB[bIdx++] = i;
                }
            }
            calc();
            return;
        }

        // 인덱스 범위 초과
        if (idx == n) return;

        // 안넣는다
        team(idx + 1, count);
        // 넣는다
        joined[idx] = true;
        team(idx + 1, count + 1);
        joined[idx] = false;
    }

    static void calc() {
        int aPower = 0;
        for (int i = 0; i < n / 2 - 1; i++) {
            for (int j = i + 1; j < n / 2; j++) {
                aPower += power[teamA[i]][teamA[j]];
                aPower += power[teamA[j]][teamA[i]];
            }
        }
        int bPower = 0;
        for (int i = 0; i < n / 2 - 1; i++) {
            for (int j = i + 1; j < n / 2; j++) {
                bPower += power[teamB[i]][teamB[j]];
                bPower += power[teamB[j]][teamB[i]];
            }
        }
        if (Math.abs(aPower - bPower) < minGap) minGap = Math.abs(aPower - bPower);
    }
}