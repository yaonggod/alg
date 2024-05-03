import java.util.*;

class Solution {
    static int m, n, fill;
    static int[][] K, L;
    public boolean solution(int[][] key, int[][] lock) {
        m = key.length;
        n = lock.length;
        K = key;
        
        // lock 주변에 m - 1길이만큼 확장해서 1칸 ~ m^2칸 겹칠 때 모두 고려
        int ll = 2 * m + n - 2;
        L = new int[ll][ll];
        
        // 비교 안해도 되는 칸은 -1
        for (int i = 0; i < ll; i++) {
            Arrays.fill(L[i], -1);
        }
        
        // 중간에 lock넣기 
        for (int i = m - 1; i < m + n - 1; i++) {
            for (int j = m - 1; j < m + n - 1; j++) {
                L[i][j] = lock[i - m + 1][j - m + 1];
            }
        }
        
        // 채워넣어야 할 네모
        fill = 0; 
        
        for (int i = 0; i < ll; i++) {
            for (int j = 0; j < ll; j++) {
                if (L[i][j] == 0) fill++;
            }
        }
        
        // lock의 (x, y)과 key의 (0, 0)을 비교하기 
        for (int i = 0; i < 4; i++) {
            for (int x = 0; x <= ll - m; x++) {
                for (int y = 0; y <= ll - m; y++) {
                    if (match(x, y)) return true;
                }
            }
            K = spin(K);
        }
        
        return false;
    }
    
    static int[][] spin(int[][] key) {
        int[][] newKey = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                newKey[j][m - i - 1] = key[i][j]; 
            }
        }
        return newKey;
    }
    
    static boolean match(int x, int y) {
        int count = 0;
        for (int i = x; i < x + m; i++) {
            for (int j = y; j < y + m; j++) {
                int keyx = i - x;
                int keyy = j - y;
                
                if (L[i][j] == -1) continue;
                if (L[i][j] == 0 && K[keyx][keyy] == 1) count++;
                if (L[i][j] == 1 && K[keyx][keyy] == 1) return false;
            }
        }
        if (count == fill) return true;
        return false;
    }
}