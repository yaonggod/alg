class Solution {
    // 이모티콘 개수
    static int e;
    // 할인율 
    static int[] dis, emo; 
    // 가입자, 매출(순수 이모티콘)
    static int[] answer = new int[] {0, 0};
    // 사람
    static int[][] user;
    
    public int[] solution(int[][] users, int[] emoticons) {
        e = emoticons.length;
        emo = emoticons;
        dis = new int[e];
        user = users;
        rate(0);
        return answer;
    }
    
    public static void rate(int index) {
        if (index == e) {
            discount();
            return;
        }
        for (int i = 10; i <= 40; i += 10) {
            dis[index] = i;
            rate(index + 1);
        }
    }
    
    // 만들어진 dis 배열로 
    public static void discount() {
        // 가입자 수
        int u = 0;
        // 수익
        int p = 0;
        for (int i = 0; i < user.length; i++) {
            // 최소 할인율
            int uRate = user[i][0];
            // 최소 비용
            int cost = user[i][1];
            // 구매 비용 purchase >= cost이면 가입자++;
            int purchase = 0;
            for (int ei = 0; ei < e; ei++) {
                if (dis[ei] >= uRate) {
                    purchase += emo[ei] * (100 - dis[ei]) / 100;
                }
            }
            if (purchase >= cost) u++;
            else p += purchase;
        }
        if (u > answer[0]) {
            answer[0] = u;
            answer[1] = p;
        } else if (u == answer[0] && p > answer[1]) {
            answer[1] = p;
        }
    }
}