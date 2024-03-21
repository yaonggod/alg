import java.util.*;

class Solution {
    public int solution(int[] order) {
        int turn = 0;
        int count = 0;
        
        // 메인
        Queue<Integer> main = new LinkedList<>();
        for (int i = 1; i <= order.length; i++) {
            main.offer(i);
        }
        // 서브
        Stack<Integer> sub = new Stack<>();
        
        while (turn < order.length) {
            // 보조 컨테이너의 맨 위가 이번 순서다
            if (!sub.isEmpty() && sub.peek() == order[turn]) {
                sub.pop();
                turn++;
                count++;
            // 없으면 메인에서 나올때까지 가져와서 보조에 넣기
            } else {
                boolean found = false;
                while (!main.isEmpty()) {
                    int box = main.poll();
                    if (box != order[turn]) {
                        sub.push(box);
                    // 찾았음
                    } else {
                        found = true;
                        turn++;
                        count++;
                        break;
                    }
                }
                if (!found) break;
            }
        }
        return count;
    }
}