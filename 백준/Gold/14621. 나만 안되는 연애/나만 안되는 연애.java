import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static class Edge implements Comparable<Edge> {
		int start; int end; int length; boolean possible;

		public Edge(int start, int end, int length, boolean possible) {
			super();
			this.start = start;
			this.end = end;
			this.length = length;
			this.possible = possible;
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
		int m = sc.nextInt();
		boolean[] school = new boolean[n + 1];
		for (int i = 1; i <= n; i++) {
			String gender = sc.next();
			if (gender.equals("M")) school[i] = true;
		}
		p = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			p[i] = i;
		}
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 0; i < m; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			int d = sc.nextInt();
			if (school[u] != school[v]) pq.offer(new Edge(u, v, d, true));
			else pq.offer(new Edge(u, v, d, false));
		}
		int cnt = 0;
		int cost = 0;
		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			if (e.possible && findset(e.start) != findset(e.end)) {
				union(e.start, e.end);
				cnt++;
				cost += e.length;
			}
			if (cnt == n - 1) break;
		}
		if (cnt == n - 1) System.out.println(cost);
		else System.out.println(-1);
		
	}
	public static int findset(int x) {
		if (p[x] != x) p[x] = findset(p[x]);
		return p[x];
	}
	public static void union(int x, int y) {
		p[findset(y)] = findset(x);
	}
}