import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        // 다리
        // 0 0
        // 0 7
        // 7 0
        // 0 4
        // 4 5
        // 5 0
        // 0 6
        // 6 0
        // 0 0
        // 지금 트럭 무게 합
        int onBridge = truck_weights[0];
        int time = 1;
        // 넣을 트럭
        int truck = 1;
        Queue<Integer> bridge = new LinkedList<>();
        for (int i = 0; i < bridge_length - 1; i++) {
            bridge.offer(0);
        }
        bridge.offer(truck_weights[0]);
        while (true) {
            time++;
            // 앞에거 하나 빼고
            int byeTruck = bridge.poll();
            onBridge -= byeTruck;
            if (truck < truck_weights.length) {
                // 다음 트럭 더해도됨?
                if (onBridge + truck_weights[truck] <= weight) {
                    onBridge += truck_weights[truck];
                    bridge.offer(truck_weights[truck]);
                    truck++;
                } else {
                    bridge.offer(0);
                }
            } else {
                bridge.offer(0);
            }
            // 트럭 이제 없음
            if (onBridge == 0) break;
        }
        return time;
    }
}