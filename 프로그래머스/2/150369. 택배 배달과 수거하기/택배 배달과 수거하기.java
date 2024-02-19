class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        
        // 무조건 뒤에서부터 하나씩 깐다
        long moved = 0;
        
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
            // if (start == -1) break;
            
            // 배달
            int dIndex = start;
            int cd = cap;
            while (dIndex >= 0) {
                if (deliveries[dIndex] > 0) {
                    // 더 배달할 수 있다
                    if (cd > deliveries[dIndex]) {
                        cd -= deliveries[dIndex];
                        deliveries[dIndex] = 0;
                    // 한계다
                    } else {
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

            // 픽업
            int pIndex = start;
            int cp = cap;
            while (pIndex >= 0) {
                if (pickups[pIndex] > 0) {
                    // 더 픽업할 수 있다
                    if (cp > pickups[pIndex]) {
                        cp -= pickups[pIndex];
                        pickups[pIndex] = 0;
                    // 한계다
                    } else {
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
            
            // 배달과 픽업이 한 번도 일어나지 않았다면
            if (cd == cap && cp == cap) break;
            
            // 이동거리
            moved += (start + 1) * 2;
            
            // 배달해야하는 지점과 픽업해야하는 지점 중 더 뒤에서 시작하기
            start = Math.max(dIndex, pIndex);
        }
        return moved;
    }
}