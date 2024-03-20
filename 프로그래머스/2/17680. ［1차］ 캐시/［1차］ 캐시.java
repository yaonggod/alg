import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) return cities.length * 5;
        
        Queue<String> queue = new LinkedList<>();
        
        int cacheHit = 0;
        int cacheMiss = 0;
        
        for (int i = 0; i < cities.length; i++) {
            String city = cities[i].toLowerCase();
            
            // 캐시에 있음
            if (queue.contains(city)) {
                // 제거하고 최신 페이지로 넣기
                queue.remove(city);
                queue.add(city);
                cacheHit++;
            // 캐시에 없음
            } else {
                // 캐시가 다 차서 하나 비워주기
                if (queue.size() == cacheSize) {
                    queue.remove();
                }
                queue.add(city);
                cacheMiss++;
            }
            
        }
        
        return cacheHit + cacheMiss * 5;
    }
}