import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static class Planet {
		int n; int x; int y; int z;

		public Planet(int n, int x, int y, int z) {
			super();
			this.n = n;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
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
	static int[] parent;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		List<Planet> planetx = new ArrayList<>();
		List<Planet> planety = new ArrayList<>();
		List<Planet> planetz = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			Planet p = new Planet(i, sc.nextInt(), sc.nextInt(), sc.nextInt());
			planetx.add(p);
			planety.add(p);
			planetz.add(p);
		}
		Collections.sort(planetx, (a, b) -> a.x - b.x);
		Collections.sort(planety, (a, b) -> a.y - b.y);
		Collections.sort(planetz, (a, b) -> a.z - b.z);
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 0; i < n - 1; i++) {
			pq.offer(new Edge(planetx.get(i).n, planetx.get(i + 1).n, (planetx.get(i + 1).x - planetx.get(i).x)));
			pq.offer(new Edge(planety.get(i).n, planety.get(i + 1).n, (planety.get(i + 1).y - planety.get(i).y)));
			pq.offer(new Edge(planetz.get(i).n, planetz.get(i + 1).n, (planetz.get(i + 1).z - planetz.get(i).z)));
		}
		
		parent = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
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
			if (cnt == n - 1) break;
		}
		System.out.println(cost);
		
	}
	public static int findset(int x) {
		if (parent[x] != x) parent[x] = findset(parent[x]);
		return parent[x];
	}
	
	public static void union(int x, int y) {
		parent[findset(y)] = findset(x);
	}
 	
}