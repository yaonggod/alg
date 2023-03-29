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
		
		// 행성 0부터
		int n = sc.nextInt();
		p = new int[n];
		for (int i = 1; i < n; i++) {
			p[i] = i;
		}
		// 관리비용 : 행렬이라 중복으로 들어옴, i < j인 경우에만 받기로
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int l = sc.nextInt();
				pq.offer(new Edge(i, j, l));
			}
		}
		// 간선 갯수 : n - 1
		int cnt = 0;
		// 비용 총합
		long cost = 0;
		
		while (true) {
			Edge e = pq.poll();
			if (findset(e.start) != findset(e.end)) {
				union(e.start, e.end);
				cnt++;
				cost += e.length;
			}
			if (cnt == n - 1) break;
		}
		System.out.println(cost);
	}
	
	static int findset(int x) {
		if (p[x] != x) p[x] = findset(p[x]);
		return p[x];
	}
	static void union(int x, int y) {
		p[findset(y)] = findset(x);
	}
}