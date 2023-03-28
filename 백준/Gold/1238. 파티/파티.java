import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static class Edge implements Comparable<Edge> {
		// 특정 점에서 end까지 가는 데 cost만큼 걸림
		int end;
		int cost;
		
		public Edge(int end, int cost) {
			super();
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}
	static int n;
	static List<Edge>[] route;
	public static void main(String[] args) {
		// N -> X -> N
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int m = sc.nextInt();
		int x = sc.nextInt();
		route = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			route[i] = new ArrayList<>();
		}
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			route[a].add(new Edge(b, c));
		}
		int result = 0;
		for (int i = 1; i <= n; i++) {
			if (dijkstra(i, x) + dijkstra(x, i) > result) {
				result = dijkstra(i, x) + dijkstra(x, i);
			}
		}
		System.out.println(result);
	}
	
	// start부터 end까지 가는 최단 경로
	public static int dijkstra(int start, int end) {
		int[] cost = new int[n + 1];
		Arrays.fill(cost, Integer.MAX_VALUE);
		cost[start] = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(start, 0));
		while (!pq.isEmpty()) {
			// start -> x로 가는 cost
			Edge x = pq.poll();
			// x에서 갈 수 있는 엣지들을 다 꺼내서
			// start -> y > start -> x + x -> y면 갱신
			// start -> x가 기존 배열에 저장된 값보다 작거나 같을 때에만 한다
			if (x.cost <= cost[x.end]) {
				// y.cost : x -> y로 가는 cost
				for (Edge y : route[x.end]) {
					if (cost[y.end] > cost[x.end] + y.cost) {
						cost[y.end] = cost[x.end] + y.cost;
						pq.offer(new Edge(y.end, cost[y.end]));
					}
				}
			}
		}
		return cost[end];
	}
}