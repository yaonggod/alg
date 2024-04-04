import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    // 집, 치킨 좌표 현황 파악
    static int[][] house, chicken;
    // 도시 생김새
    static int[][] city;
    // 이 치킨집은 열었습니까?
    static boolean[] open;
    // 최대 m개의 치킨집을 열 수 있으나, m개 미만으로 여는 것보다 m개로 여는 것이 치킨 거리를 줄일 수 있다는 것이 확실함
    static int m, n, houseCount, chickenCount;
    // 치킨거리
    static int minDist = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        city = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        houseCount = 0;
        chickenCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (city[i][j] == 1) houseCount++;
                if (city[i][j] == 2) chickenCount++;
            }
        }

        house = new int[houseCount][2];
        chicken = new int[chickenCount][2];
        open = new boolean[chickenCount];
        int houseIdx = 0;
        int chickenIdx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (city[i][j] == 1) {
                    house[houseIdx][0] = i;
                    house[houseIdx][1] = j;
                    houseIdx++;
                }
                if (city[i][j] == 2) {
                    chicken[chickenIdx][0] = i;
                    chicken[chickenIdx][1] = j;
                    chickenIdx++;
                }
            }
        }
        travel(0, 0);
        System.out.println(minDist);
    }

    // 지금까지 count개를 열었으며, idx번째부터 열지 말지 다시 결정한다
    static void travel(int idx, int count) {
        if (count == m) {
            // 치킨 거리
            int chicDist = 0;
            // 모든 집에 대해
            for (int i = 0; i < houseCount; i++) {
                int dist = Integer.MAX_VALUE;
                // 오픈한 치킨집마다 대조
                for (int j = 0; j < chickenCount; j++) {
                    if (open[j]) {
                        int distj = Math.abs(house[i][0] - chicken[j][0]) + Math.abs(house[i][1] - chicken[j][1]);
                        if (distj < dist) dist = distj;
                    }
                }
                chicDist += dist;
            }
            if (chicDist < minDist) minDist = chicDist;
        }

        // 인덱스 범위 넘음
        if (idx == chickenCount) return;

        for (int i = idx; i < chickenCount; i++) {
            open[i] = true;
            travel(i + 1, count + 1);
            open[i] = false;
        }
    }
}