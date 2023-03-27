import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static class Edge implements Comparable<Edge> {
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
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int v = sc.nextInt();
		int e = sc.nextInt();
		int k = sc.nextInt();
		// 그래프
		List<Edge>[] graph = new ArrayList[v + 1];
		for (int i = 1; i <= v; i++) {
			graph[i] = new ArrayList<>();
		}
		
		// 간선들
		for (int i = 0; i < e; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			Edge newe = new Edge(b, c);
			graph[a].add(newe);
		}
		
		// 루트
		int[] route = new int[v + 1];
		Arrays.fill(route, Integer.MAX_VALUE);
		route[k] = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[v + 1];
		pq.offer(new Edge(k, 0));
		while (!pq.isEmpty()) {
			Edge x = pq.poll();
			for (Edge y : graph[x.end]) {
				// k -> y > k -> x + x -> y
				if (route[y.end] > route[x.end] + y.cost) {
					route[y.end] = route[x.end] + y.cost;
					pq.offer(new Edge(y.end, route[y.end]));
				}				
			}	
		}
		for (int i = 1; i <= v; i++) {
			if (route[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			} else {
				System.out.println(route[i]);
			}
		}
	}
	
}