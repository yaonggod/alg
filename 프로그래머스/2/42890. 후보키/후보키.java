import java.util.*;

class Solution {
    static boolean[] isKey;
    static int column;
    static String[][] R;
    // 나온 키들
    static Set<Integer> keys;
    public int solution(String[][] relation) {
        R = relation;
        // 컬럼 개수
        column = relation[0].length;
        keys = new HashSet<>();
        for (int i = column; i >= 1; i--) {
            // 지금 선택된 키
            isKey = new boolean[column];
            makeComb(0, 0, i);
        }
        return keys.size();
    }
    
    public void makeComb(int idx, int count, int total) {
        // 다 선택했음
        if (count == total) {
            checkCanKey(total);
            return;
        }
        if (idx == column) return;
        
        // 지금거를 선택한다
        isKey[idx] = true;
        makeComb(idx + 1, count + 1, total);
        
        // 안한다
        isKey[idx] = false;
        makeComb(idx + 1, count, total);
    }
    
    public void checkCanKey(int total) {
        Set<String> PK = new HashSet<>();
        for (int i = 0; i < R.length; i++) {
            // 그냥 스트링빌더로 다 묶어버려서 키 취급하기
            StringBuilder sb = new StringBuilder();
            for (int k = 0; k < column; k++) {
                if (isKey[k]) {
                    sb.append(R[i][k]);
                }
            }
            if (PK.contains(sb.toString())) return;
            PK.add(sb.toString());
        }
        // 가능~
        int canKey = 0;
        for (int k = 0; k < column; k++) {
            if (isKey[k]) {
                canKey |= (1 << k);
            }
        }
        // keys에 key를 넣고 싶은데, 이미 이거로 이루어진 조합이 있으면 그걸 빼고 넣음
        Set<Integer> newSet = new HashSet<>();
        for (int key : keys) {
            int used = 0;
            for (int k = 0; k < column; k++) {
                // 사용했다 
                if ((canKey & (1 << k)) == (1 << k) && (canKey & (1 << k)) == (key & (1 << k))) {
                    used++;
                }
            }
            if (used != total) {
                newSet.add(key);
            }
        }
        System.out.println();
        newSet.add(canKey);
        keys = newSet;
    }
}