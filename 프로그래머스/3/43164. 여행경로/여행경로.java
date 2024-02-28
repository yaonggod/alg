import java.util.*;

class Solution {
    static String[][] t;
    static String[] answer, route;
    static boolean[] visited;
    static int n;
    public String[] solution(String[][] tickets) {
        t = tickets;
        n = t.length;
        visited = new boolean[n];
        Arrays.sort(t, new Comparator<String[]>() {
            @Override
            public int compare(String[] a, String[] b) {
                return b[1].compareTo(a[1]);
            }
        });
        route = new String[n + 1];
        answer = new String[n + 1];
        dfs(0, "ICN");
        return answer;
    }

    static void dfs(int count, String from) {
        route[count] = from;
        if (count == n) {
            for (int i = 0; i <= n; i++) {
                answer[i] = route[i];
            }
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i] && t[i][0].equals(from)) {
                visited[i] = true;
                // 다음 공항으로 ㄱㄱ
                dfs(count + 1, t[i][1]);
                visited[i] = false;
            }
        }
    }
}