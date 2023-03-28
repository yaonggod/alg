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
		int start = sc.nextInt();
		int end = sc.nextInt();
		dijkstra(start, end);
	}
	
	// start부터 end까지 가는 최단 경로
	public static void dijkstra(int start, int end) {
		int[] cost = new int[n + 1];
		// 가는 경로에 무슨 도시가 포함되었는지
		// size가 1인 경우 -> start에서 바로 갔음
		// size가 1보다 큰 경우 -> 마지막 값이 마지막으로 갱신된 경로
		List<Integer>[] contains = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			contains[i] = new ArrayList<>();
		}
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
						// y로 가는 길에 x가 포함됨
						contains[y.end].add(x.end);
						cost[y.end] = cost[x.end] + y.cost;
						pq.offer(new Edge(y.end, cost[y.end]));
					}
				}
			}
		}
		System.out.println(cost[end]);
		List<Integer> finalroute = new ArrayList<>();
		finalroute.add(end);
		// 지나온거
		int pass = end;
		while (true) {
			// 마지막 원소가 start면 그만 돌아
			if (contains[pass].get(contains[pass].size() - 1) == start) {
				break;
			} 
			finalroute.add(contains[pass].get(contains[pass].size() - 1));
			pass = contains[pass].get(contains[pass].size() - 1);
		}
		finalroute.add(start);
		System.out.println(finalroute.size());
		for (int i = finalroute.size() - 1; i >= 0; i--) {
			System.out.print(finalroute.get(i) + " ");
		}
	}
}