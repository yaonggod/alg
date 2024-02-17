class Solution {
    static int m, minTired;
    static int[] pick;
    static String[] mineral;
    static int[][] tMatrix = new int[][] {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
    
    public int solution(int[] picks, String[] minerals) {
        m = minerals.length;
        minTired = m * 25;
        pick = picks;
        mineral = minerals;
        
        for (int i = 0; i < 3; i++) {
            if (pick[i] > 0) {
                pick[i]--;
                dig(0, i, 0, 0);
                pick[i]++;
            }
        }
        return minTired;
    }
    
    public static void dig(int index, int currPick, int count, int totalTired) {
        // 모든 광물을 캤다
        if (index == m) {
            if (totalTired < minTired) minTired = totalTired;
            return;
        // 아직 캘게 남았다
        } else {
            // 만약 장비 카운트를 다 썼다
            if (count == 5) {
                boolean noMorePick = true;
                for (int i = 0; i < 3; i++) {
                    // 장비가 아직 남아있으면 이거 써보자
                    if (pick[i] > 0) {
                        noMorePick = false;
                        pick[i]--;
                        dig(index, i, 0, totalTired);
                        pick[i]++;
                    }
                }
                // 남은 장비가 없다
                if (noMorePick) {
                    if (totalTired < minTired) minTired = totalTired;
                    return;
                }
            } else {
                // 이번 턴에 캐야할거
                int thisMineral = minIndex(mineral[index]);
                // 지금 가지고 있는 장비로 캐면 소모되는 피로도
                int thisTired = tMatrix[currPick][thisMineral];
                dig(index + 1, currPick, count + 1, totalTired + thisTired);
            }
        }
    }
    
    public static int minIndex(String mineral) {
        if (mineral.equals("diamond")) return 0;
        if (mineral.equals("iron")) return 1;
        return 2;
    }
}