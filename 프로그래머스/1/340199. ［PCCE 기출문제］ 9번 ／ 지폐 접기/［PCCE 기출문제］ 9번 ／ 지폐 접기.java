import java.util.*;

class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        
        int max = Math.max(wallet[0], wallet[1]);
        int min = Math.min(wallet[0], wallet[1]);
        
        int bmax = Math.max(bill[0], bill[1]);
        int bmin = Math.min(bill[0], bill[1]);
        
        while (true) {
            if (bmax <= max && bmin <= min) return answer;
            
            int nmax = bmax / 2;
            int nmin = bmin;
            bmax = Math.max(nmax, nmin);
            bmin = Math.min(nmax, nmin);
            answer++;
        }
    }
}