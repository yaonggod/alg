import java.util.*;

class Solution {
    static String[] user;
    static String[] banned;
    static int banCount;
    static boolean[] visited;
    static ArrayList<Integer>[] possible;
    static Set<String> result;
    
    public int solution(String[] user_id, String[] banned_id) {
        user = user_id;
        banned = banned_id;
        banCount = banned.length;
        visited = new boolean[user.length];
        possible = new ArrayList[banCount];
        result = new HashSet<>();
        
        for (int i = 0; i < banCount; i++) {
            possible[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < banCount; i++) {
            for (int j = 0; j < user.length; j++) {
                // 가능한 user의 index를 넣어라 
                if (check(user[j], banned[i])) {
                    possible[i].add(j);
                }
            }
        }
        makeTurn(0, "");
        return result.size();
    }
    
    static boolean check(String userid, String banid) {
        // 길이가 다르면 안똑같음
        if (userid.length() != banid.length()) return false;
        char[] userArr = userid.toCharArray();
        char[] banArr = banid.toCharArray();
        int n = userArr.length;
        for (int i = 0; i < n; i++) {
            // 다른 부분 찾음
            if (banArr[i] != '*' && banArr[i] != userArr[i]) {
                return false;
            }
        }
        return true;
    }
    
    static void makeTurn(int index, String str) {
        if (index == banCount) {
            // 중복제거 
            char[] strArr = str.toCharArray();
            Arrays.sort(strArr);
            String st = "";
            for (char c : strArr) {
                st += c;
            }
            result.add(st);
            return;
        }
        // index번째 banid에 userid를 집어넣어볼거임
        for (int i = 0; i < possible[index].size(); i++) {
            int uidx = possible[index].get(i);
            if (!visited[uidx]) {
                visited[uidx] = true;
                makeTurn(index + 1, str + uidx);
                visited[uidx] = false;
            }
        }
    }
}