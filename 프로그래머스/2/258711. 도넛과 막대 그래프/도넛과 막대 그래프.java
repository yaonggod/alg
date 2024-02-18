import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int maxNode = 0;
        for (int i = 0; i < edges.length; i++) {
            if (edges[i][0] > maxNode) maxNode = edges[i][0];
            if (edges[i][1] > maxNode) maxNode = edges[i][1];
        }
        List<Integer>[] edg = new ArrayList[maxNode + 1];
        int[][] inNout = new int[maxNode + 1][2];
        for (int i = 1; i <= maxNode; i++) {
            edg[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            // a -> b
            int a = edges[i][0];
            int b = edges[i][1];
            edg[a].add(b);
            inNout[b][0]++;
            inNout[a][1]++;
        }
        
        int[] answer = new int[4];
        int graphCount = 0;
        for (int i = 1; i <= maxNode; i++) {
            // in이 0개고 out이 2개 이상
            if (inNout[i][0] == 0 && inNout[i][1] >= 2) {
                answer[0] = i;
                graphCount = inNout[i][1];
                // 정점 edge 없애기
                inNout[answer[0]][1] = 0;
                for (int e : edg[answer[0]]) {
                    inNout[e][0]--;
                }
                break;
            }
        }
        
        int stick = 0;
        int eight = 0;
        for (int i = 1; i <= maxNode; i++) {
            // 들어오는게 없다 == 막대
            if (i != answer[0] && inNout[i][0] == 0) stick++;
            // 2 2 == 8
            else if (inNout[i][0] == 2 && inNout[i][1] == 2) eight++; 
        }
        answer[1] = graphCount - stick - eight;
        answer[2] = stick;
        answer[3] = eight;
        
        return answer;
        
    }
}