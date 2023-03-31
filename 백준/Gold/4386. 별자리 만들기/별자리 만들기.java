import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static class Edge implements Comparable<Edge> {
		int start; int end; double length;

		public Edge(int start, int end, double length) {
			super();
			this.start = start;
			this.end = end;
			this.length = length;
		}

		@Override
		public int compareTo(Edge o) {
			if (this.length > o.length) return 1;
			return -1;
		}
	}
	static int[] p;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		p = new int[n];
		for (int i = 0; i < n; i++) {
			p[i] = i;
		}
		double[][] star = new double[n][2];
		for (int i = 0; i < n; i++) {
			star[i][0] = sc.nextDouble();
			star[i][1] = sc.nextDouble();
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 0; i < n - 1; i++) { 
			for (int j = i + 1; j < n; j++) {
				double length = Math.pow(Math.pow(star[i][0] - star[j][0], 2) + Math.pow(star[i][1] - star[j][1], 2), 0.5);
				pq.offer(new Edge(i, j, length));
			}
		}
		int cnt = 0;
		double cost = 0;
		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			if (findset(e.start) != findset(e.end)) {
				union(e.start, e.end);
				cnt++;
				cost += e.length;
			}
			if (cnt == n - 1) break;
		}
		System.out.println(String.format("%.2f", (cost * 100) / 100.0));
		
	}
	public static int findset(int x) {
		if (p[x] != x) p[x] = findset(p[x]);
		return p[x];
	}
	public static void union(int x, int y) {
		p[findset(y)] = findset(x);
	}
}