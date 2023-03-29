import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	static class Edge implements Comparable<Edge> {
		int start;
		int end;
		double length;
		
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
		int m = sc.nextInt();
		p = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			p[i] = i;
		}
		// 우주신 좌표
		int[] ux = new int[n + 1];
		int[] uy = new int[n + 1];
		for (int i = 0; i < n; i++) {
			ux[i + 1] = sc.nextInt();
			uy[i + 1] = sc.nextInt();
		}
		// 이미 연결됨
		for (int i = 0; i < m; i++) {
			int c1 = sc.nextInt();
			int c2 = sc.nextInt();
			union(c1, c2);
		}
		// 엣지 넣자
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				pq.offer(new Edge(i, j, length(ux[i], ux[j], uy[i], uy[j])));
			}
		}
		
		double result = 0;
		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			if (findset(e.start) != findset(e.end)) {
				union(e.start, e.end);
				result += e.length;
			}
		}
		System.out.println(String.format("%.2f", result));
	}
	
	static int findset(int x) {
		if (p[x] != x) p[x] = findset(p[x]);
		return p[x];
	}
	static void union(int x, int y) {
		p[findset(y)] = findset(x);
	}
	// 피타고라스
	static double length(int x1, int x2, int y1, int y2) {
		return Math.pow(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2), 0.5);
	}
}