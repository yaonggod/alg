import java.util.*;

class Solution {
    public int solution(int[] topping) {
        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();
        // 오른쪽에 몰아넣고
        for (int i = 0; i < topping.length; i++) {
            if (right.containsKey(topping[i])) {
                right.put(topping[i], right.get(topping[i]) + 1);
            } else {
                right.put(topping[i], 1);
            }
        }
        int result = 0;
        int leftSize = 0;
        int rightSize = right.size();
        // 하나씩 왼쪽으로 옮기기
        for (int i = 0; i < topping.length - 1; i++) {
            int move = topping[i];
            // 왼쪽에 넣고
            if (left.containsKey(move)) {
                left.put(move, left.get(move) + 1);
            } else {
                left.put(move, 1);
                leftSize++;
            }
            // 오른쪽에서 빼고
            if (right.get(move) == 1) {
                right.remove(move);
                rightSize--;
            } else {
                right.put(move, right.get(move) - 1);
            }
            // 크기 비교
            if (leftSize == rightSize) result++;
        }
        return result;
    }
}