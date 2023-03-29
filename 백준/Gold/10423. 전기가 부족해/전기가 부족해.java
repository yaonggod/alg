import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

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
		int m = sc.nextInt();
		int k = sc.nextInt();
		// 발전소
		Set<Integer> pp = new HashSet<>();
		for (int i = 0; i < k; i++) {
			pp.add(sc.nextInt());
		}
		p = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			p[i] = i;
		}
		int cost = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 0; i < m; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			int w = sc.nextInt();
			pq.offer(new Edge(u, v, w));
		}
		
		while (true) {
			Edge e = pq.poll();
			// 발전소 - 도시, 도시 - 도시만 연결
			// 도시 - 도시는 나중으로 보류하는 줄 알았는데 아닌가봄 일단 길이가 적으니가..
			if (pp.contains(findset(e.start)) && !pp.contains(findset(e.end))) {
				union(e.start, e.end);
				cost += e.length;
			} else if (!pp.contains(findset(e.start)) && pp.contains(findset(e.end))) {
				union(e.end, e.start);
				cost += e.length;
			} else if (!pp.contains(findset(e.start)) && !pp.contains(findset(e.end))) {
				if (findset(e.start) != findset(e.end)) {
					union(e.end, e.start);
					cost += e.length;
				}
			}
			boolean allpp = true;
			for (int i = 1; i <= n; i++) {
				if (!pp.contains(findset(p[i]))) {
					allpp = false;
					break;
				}
			}
			if (allpp) break;
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