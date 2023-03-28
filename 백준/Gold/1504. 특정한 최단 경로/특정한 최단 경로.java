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
		// 1 -> N인데 v1, v2 지나감
		// 1 -> v1 -> v2 -> N
		// 1 -> v2 -> v1 -> N
		// 양방향이므로 v1 -> v2 == v2 -> v1 => 이게 INF뜨면 일단 못감
		// 1에서 출발하는 최단 경로 -> v2, v1
		// v1에서 출발하는 최단 경로 -> v2, N
		// v2에서 출발하는 최단 경로 -> (v1), N
		
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		// i번 정점에서 나오는 모든 간선
		route = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			route[i] = new ArrayList<>();
		}
		int e = sc.nextInt();
		for (int i = 0; i < e; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			route[a].add(new Edge(b, c));
			route[b].add(new Edge(a, c));
		}
		int v1 = sc.nextInt();
		int v2 = sc.nextInt();
		int[] result = new int[5];
		// 1 -> v1
		result[0] = dijkstra(1, v1);
		// 1 -> v2
		result[1] = dijkstra(1, v2);
		// v1 -> v2
		result[2] = dijkstra(v1, v2);
		// v1 -> n
		result[3] = dijkstra(v1, n);
		// v2 -> n
		result[4] = dijkstra(v2, n);
		
		// 0, 2, 4
		// 1, 2, 3
		// 둘다안됨
		if (result[2] == -1 || ((result[0] == -1 || result[4] == -1) && (result[1] == -1 || result[3] == -1))) {
			System.out.println(-1);
		// 123
		} else if (result[0] == -1 || result[4] == -1) {
			System.out.println(result[1] + result[2] + result[3]);
		// 024
		} else if (result[1] == -1 || result[3] == -1) {
			System.out.println(result[0] + result[2] + result[4]);
		// 둘다됨
		} else {
			System.out.println(Math.min(result[1] + result[2] + result[3], result[0] + result[2] + result[4]));
		}
		
	}
	
	// start부터 end까지 가는 최단 경로
	// INF면 -1 return함
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
		if (cost[end] == Integer.MAX_VALUE) return -1;
		return cost[end];
	}
}