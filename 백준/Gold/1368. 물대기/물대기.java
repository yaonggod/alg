import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	static class Edge implements Comparable<Edge> {
		int start;
		int end;
		int length;
		
		public Edge(int start, int end, int length) {
			super();
			this.start = start;
			this.end = end;
			this.length = length;
		}

		@Override
		public int compareTo(Edge o) {
			return this.length - o.length;
		}
	}
	static int[] p;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		p = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			p[i] = i;
		}
		int[] price = new int[n + 1];
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 1; i <= n; i++) {
			int l = sc.nextInt();
			price[i] = l;
			pq.offer(new Edge(0, i, l));
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int l = sc.nextInt();
				if (i != j) {
					pq.offer(new Edge(i + 1, j + 1, l));
				}
			}
		}
		int cnt = 0;
		int cost = 0;
		while (true) {
			Edge e = pq.poll();
			if (findset(e.start) != findset(e.end)) {
				union(e.start, e.end);
				cnt++;
				cost += e.length;
			}
			if (cnt == n) break;
		}
		System.out.println(cost);
	}
	
	static int findset(int x) {
		if (p[x] != x) p[x] = findset(p[x]);
		return p[x];
	}
	// x에 y 흡수
	static void union(int x, int y) {
		p[findset(y)] = findset(x);
	}
}