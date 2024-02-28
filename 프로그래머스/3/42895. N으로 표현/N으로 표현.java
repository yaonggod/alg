import java.util.*;

class Solution {
    public int solution(int N, int number) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer>[] turn = new HashSet[9];
        int k = N;
        // N, NN, NNN, ...
        for (int i = 1; i <= 8; i++) {
            turn[i] = new HashSet<>();
            turn[i].add(k);
            visited.add(k);
            k = k * 10 + N;
        }
        
        int result = -1;
        
        if (N == number) return 1;
        
        for (int i = 2; i <= 8; i++) {
            for (int j = 1; j <= i / 2; j++) {
                // i를 두개로 나누기
                // a개를 조합해서 나온거 + b개를 조합해서 나온거 -> c개를 조합해서 나온거 
                // b개를 조합해서 나온거 + a개를 조합해서 나온거 -> c개를 조합해서 나온거 
                int a = j;
                int b = i - j;
                for (int akey : turn[a]) {
                    for (int bkey : turn[b]) {
                        // 더하기
                        if (!visited.contains(akey + bkey)) {
                            visited.add(akey + bkey);
                            turn[i].add(akey + bkey);
                        }
                        // 빼기
                        if (!visited.contains(akey - bkey)) {
                            visited.add(akey - bkey);
                            turn[i].add(akey - bkey);
                        }
                        if (!visited.contains(bkey - akey)) {
                            visited.add(bkey - akey);
                            turn[i].add(bkey - akey);
                        }
                        // 곱하기
                        if (!visited.contains(akey * bkey)) {
                            visited.add(akey * bkey);
                            turn[i].add(akey * bkey);
                        }
                        // 나누기
                        if (akey != 0 && !visited.contains(bkey / akey)) {
                            visited.add(bkey / akey);
                            turn[i].add(bkey / akey);
                        }
                        if (bkey != 0 && !visited.contains(akey / bkey)) {
                            visited.add(akey / bkey);
                            turn[i].add(akey / bkey);
                        }
                        if (turn[i].contains(number)) return i;
                    }
                }
            }
            
        }
        return result;
    }
    
    
}