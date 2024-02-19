class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        
        // 무조건 뒤에서부터 하나씩 깐다
        long moved = 0;
        
        // 배달할거 총량
        int totald = 0;
        int totalp = 0;
        for (int i = 0; i < n; i++) {
            totald += deliveries[i];
            totalp += pickups[i];
        }
        
        int c = cap;
        int start = n - 1;
        for (int i = start; i >= 0; i--) {
            // 뒤에서부터 처음으로 나타나는 곳이 있다면
            if (deliveries[i] > 0) {
                start = i;
                break;
            }
            if (pickups[i] > 0) {
                start = i;
                break;
            }
        }
        while (true) {
            if (totald == 0 && totalp == 0) break;
            
            int dIndex = start;
            if (totald > 0) {
                // 배달
                int cd = cap;
                while (dIndex >= 0) {
                    if (deliveries[dIndex] > 0) {
                        // 더 배달할 수 있다
                        if (cd > deliveries[dIndex]) {
                            totald -= deliveries[dIndex];
                            cd -= deliveries[dIndex];
                            deliveries[dIndex] = 0;
                        // 한계다
                        } else {
                            totald -= cd;
                            deliveries[dIndex] -= cd;
                            cd = 0;
                            break;
                        }
                    } 
                    dIndex--;
                }

                // 배달할 게 처음 나타나는 지점
                while (dIndex >= 0) {
                    if (deliveries[dIndex] > 0) break;
                    dIndex--;
                }
            }
            
            int pIndex = start;
            if (totalp > 0) {
                // 픽업
                int cp = cap;
                while (pIndex >= 0) {
                    if (pickups[pIndex] > 0) {
                        // 더 픽업할 수 있다
                        if (cp > pickups[pIndex]) {
                            totalp -= pickups[pIndex];
                            cp -= pickups[pIndex];
                            pickups[pIndex] = 0;
                        // 한계다
                        } else {
                            totalp -= cp;
                            pickups[pIndex] -= cp;
                            cp = 0;
                            break;
                        }
                    } 
                    pIndex--;
                }

                // 픽업할 게 처음 나타나는 지점
                while (pIndex >= 0) {
                    if (pickups[pIndex] > 0) break;
                    pIndex--;
                }
            }
            
            // 이동거리
            moved += (start + 1) * 2;
            
            // 배달해야하는 지점과 픽업해야하는 지점 중 더 뒤에서 시작하기
            if (totald == 0) start = pIndex;
            else if (totalp == 0) start = dIndex;
            else start = Math.max(dIndex, pIndex);
        }
        return moved;
    }
}