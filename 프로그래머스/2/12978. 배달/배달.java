import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        List<Integer[]>[] village = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            village[i] = new ArrayList<>();
        }
        for (int[] r : road) {
            int a = r[0];
            int b = r[1];
            int c = r[2];
            village[a].add(new Integer[] {b, c});
            village[b].add(new Integer[] {a, c});
        }
        int[] route = new int[N + 1];
        Arrays.fill(route, Integer.MAX_VALUE);
        route[1] = 0;
        PriorityQueue<Integer[]> pq = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] a, Integer[] b) {
                return a[1] - b[1];
            }
        });
        pq.offer(new Integer[] {1, 0});
        while (!pq.isEmpty()) {
            Integer[] data = pq.poll();
            int from = data[0];
            int cFrom = data[1];
            for (Integer[] data2 : village[from]) {
                int to = data2[0];
                int fromTo = data2[1];
                if (cFrom + fromTo <= K && route[to] > cFrom + fromTo) {
                    route[to] = cFrom + fromTo;
                    pq.offer(new Integer[] {to, route[to]});
                }
            }
        }
        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (route[i] <= K) {
                count++;
            }
        }
        return count;
    }
}