import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        // a * b = brown + yellow
        // a + b = brown / 2 + 2
        int b = 1;
        int a = brown + yellow;
        while (a >= b) {
            if ((brown + yellow) % b == 0) {
                a = (brown + yellow) / b;
                if (a + b == brown / 2 + 2) {
                    return new int[] {a, b};
                }
            }
            b++;
        }       
        return new int[] {0, 0};
    }
}