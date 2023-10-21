import java.util.*;

class Solution {
    static int l, t;
    static int count = 0;
    static int[] n;
    public int solution(int[] numbers, int target) {
        l = numbers.length;
        t = target;
        n = numbers;
        addNum(0, 0);
        return count;
    }
    
    public void addNum(int index, int result) {
        if (index == l) {
            if (result == t) {
                count++;
            }
        } else {
            addNum(index + 1, result + n[index]);
            addNum(index + 1, result - n[index]);
        }
    }
}