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
		int v = sc.nextInt();
		int e = sc.nextInt();
		// MST는 v-1개의 간선만 있으면 됨
		p = new int[v + 1];
		for (int i = 1; i <= v; i++) {
			p[i] = i;
		}
		// 간선 넣을 pq
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 0; i < e; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			pq.offer(new Edge(a, b, c));
		}
		// 간선 연결하자, 이미 같은 그룹이 아닌 경우에만
		int count = 0;
		long ans = 0;
		
		while (true) {
			Edge ed = pq.poll();
			if (findset(ed.start) != findset(ed.end)) {
				// 합치자
				union(ed.start, ed.end);
				ans += ed.length;
				count++;
			}
			if (count == v - 1) break;
		}
		System.out.println(ans);
	}
	
	static int findset(int x) {
		if (p[x] != x) p[x] = findset(p[x]);
		return p[x];
	}
	static void union(int x, int y) {
		p[findset(y)] = findset(x);
	}
}