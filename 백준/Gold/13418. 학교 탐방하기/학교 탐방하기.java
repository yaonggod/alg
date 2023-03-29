import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	static class Edge {
		int start;
		int end;
		int length;
		
		public Edge(int start, int end, int length) {
			super();
			this.start = start;
			this.end = end;
			this.length = length;
		}
	}
	static int[] p, p2;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		p = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			p[i] = i;
		}
		p2 = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			p2[i] = i;
		}
		// 0에서 1로 가는거
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		// 초기 비용 - 0 -> 1 무조건
		int cost;
		int cost2;
		if (c == 0) cost = cost2 = 1;
		else cost = cost2 = 0;
		// 최소힙 최대힙으로 관리
		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o2.length - o1.length);
		PriorityQueue<Edge> pq2 = new PriorityQueue<>((o1, o2) -> o1.length - o2.length);
		for (int i = 0; i < m; i++) {
			a = sc.nextInt();
			b = sc.nextInt();
			c = sc.nextInt();
			// 최소
			pq.offer(new Edge(a, b, c));
			// 최대
			pq2.offer(new Edge(a, b, c));
		}
		// 간선 갯수 : n - 1
		int cnt = 0;
		int cnt2 = 0;
		
		while (true) {
			Edge e = pq.poll();
			if (findset(e.start, p) != findset(e.end, p)) {
				union(e.start, e.end, p);
				cnt++;
				if (e.length == 0) cost++;
			}
			if (cnt == n - 1) break;
		}
		while (true) {
			Edge e = pq2.poll();
			if (findset(e.start, p2) != findset(e.end, p2)) {
				union(e.start, e.end, p2);
				cnt2++;
				if (e.length == 0) cost2++;
			}
			if (cnt2 == n - 1) break;
		}
		System.out.println(cost2 * cost2 - cost * cost);
	}
	
	static int findset(int x, int[] p) {
		if (p[x] != x) p[x] = findset(p[x], p);
		return p[x];
	}
	static void union(int x, int y, int[] p) {
		p[findset(y, p)] = findset(x, p);
	}
}