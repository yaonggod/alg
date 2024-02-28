import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        List<Integer[]>[] route = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            route[i] = new ArrayList<>();
        }
        
        // 정상
        Set<Integer> summ = new HashSet<>();
        for (int s : summits) {
            summ.add(s);
        }
        
        // 문
        Set<Integer> gat = new HashSet<>();
        for (int g : gates) {
            gat.add(g);
        }
        for (int[] p : paths) {
            int i = p[0];
            int j = p[1];
            int w = p[2];
            // 단방향
            if (gat.contains(i) || summ.contains(j)) {
                route[i].add(new Integer[] {j, w});
            } else if (gat.contains(j) || summ.contains(i)) {
                route[j].add(new Integer[] {i, w});
            } else {
                route[i].add(new Integer[] {j, w});
                route[j].add(new Integer[] {i, w});
            }
            
        }
        
        int[] result = new int[] {n, 10000001};
        
        
        
        // a까지 가는 데 피로도 b 걸렸음
        PriorityQueue<Integer[]> pq = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] a, Integer[] b) {
                return a[0] - b[0];
            }
        });
        
        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, 10000001);
        for (int g : gates) {
            // gate로 가는 intensity는 없음
            intensity[g] = 0;
            pq.offer(new Integer[] {0, g});
        }
        
        // 갱신
        while (!pq.isEmpty()) {
            Integer[] data = pq.poll();
            int w = data[0];
            int from = data[1];
            if (w > intensity[from]) continue;
            if (summ.contains(from)) {
                if (intensity[from] < result[1]) {
                    result[0] = from;
                    result[1] = intensity[from];
                } else if (intensity[from] == result[1] && result[0] > from) {
                    result[0] = from;
                }
            } else {
                for (Integer[] to : route[from]) {
                    // 기존 intensity 갱신
                    if (intensity[to[0]] > Math.max(w, to[1])) {
                        intensity[to[0]] = Math.max(w, to[1]);
                        pq.offer(new Integer[] {intensity[to[0]], to[0]});
                    }
                }
            }
        }          
    
        return result;
        
        
    }
}