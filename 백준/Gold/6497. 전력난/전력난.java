import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static class Edge implements Comparable<Edge> {
		int start; int end; int length;

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
		while (true) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			if (n == 0 && m == 0) break;
			p = new int[n];
			for (int i = 0; i < n; i++) {
				p[i] = i;
			}
			int total = 0;
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			for (int i = 0; i < m; i++) { 
				int x = sc.nextInt();
				int y = sc.nextInt();
				int z = sc.nextInt();
				pq.offer(new Edge(x, y, z));
				total += z;
			}
			int cnt = 0;
			int cost = 0;
			while (!pq.isEmpty()) {
				Edge e = pq.poll();
				if (findset(e.start) != findset(e.end)) {
					union(e.start, e.end);
					cnt++;
					cost += e.length;
				}
				if (cnt == n - 1) break;
			}
			System.out.println(total - cost);
		}
		
	}
	public static int findset(int x) {
		if (p[x] != x) p[x] = findset(p[x]);
		return p[x];
	}
	public static void union(int x, int y) {
		p[findset(y)] = findset(x);
	}
}