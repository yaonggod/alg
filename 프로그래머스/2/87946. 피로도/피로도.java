class Solution {
    static int answer = 0;
    static int[][] d;
    static int n, tired;
    static int[] turn;
    static boolean[] visited;
    public int solution(int k, int[][] dungeons) {
        d = dungeons;
        n = dungeons.length;
        tired = k;
        turn = new int[n];
        visited = new boolean[n];
        makeTurn(0);
        return answer;
    }
    
    static void makeTurn(int index) {
        if (index == n) {
            travel();
        } else {
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    turn[index] = i;
                    makeTurn(index + 1);
                    visited[i] = false;
                }
            }
        }
    }
    
    static void travel() {
        int count = 0;
        int t = tired;
        for (int i = 0; i < n; i++) {
            // 탐험할 수 있는가
            if (t >= d[turn[i]][0]) {
                t -= d[turn[i]][1];
                count++;
            } else {
                break;
            }
        }
        if (count > answer) answer = count;
    }
}