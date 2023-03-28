import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static class Bus implements Comparable<Bus> {
		int end;
		int cost;
		
		public Bus(int end, int cost) {
			super();
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Bus o) {
			return this.cost - o.cost;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		// i도시에서 나와서 어느 도시로 갈 수 있는지
		List<Bus>[] route = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			route[i] = new ArrayList<>();
		}
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			route[a].add(new Bus(b, c));
		}
		// s -> e 가고싶음
		int s = sc.nextInt();
		int e = sc.nextInt();
		// s에서 다른 도시들로 가는 데 드는 비용
		int[] cost = new int[n + 1];
		Arrays.fill(cost, Integer.MAX_VALUE);
		cost[s] = 0;
		
		PriorityQueue<Bus> pq = new PriorityQueue<>();
		pq.offer(new Bus(s, 0));
		
		while (!pq.isEmpty()) {
			// s -> b.end로 가는데 기존 경로보다 길면 할 필요가 없음
			Bus b = pq.poll();
			if (b.cost <= cost[b.end]) {
				// b.end -> y.end 가고싶음
				for (Bus y : route[b.end]) {
					// s -> y > s -> b + b -> y
					if (cost[y.end] > cost[b.end] + y.cost) {
						// s -> y로 가는 최단 경로가 갱신됨
						// 짧을수록 pq에서 먼저 나옴
						cost[y.end] = cost[b.end] + y.cost;
						pq.offer(new Bus(y.end, cost[y.end]));
					}
				}
			}
		}
		System.out.println(cost[e]);
	}
}