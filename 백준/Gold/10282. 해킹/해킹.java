import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	static int n, d, c;
	static int[] cost;
	static List<Integer[]>[] route;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int tc = 0; tc < t; tc++) {
			n = sc.nextInt();
			d = sc.nextInt();
			c = sc.nextInt();
			route = new ArrayList[n + 1];
			for (int i = 1; i <= n; i++) {
				route[i] = new ArrayList<>();
			}
			for (int i = 0; i < d; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				int s = sc.nextInt();
				route[b].add(new Integer[] {a, s});
			}
			int count = 0;
			int maxtime = 0;
			cost = new int[n + 1];
			dijkstra(c);
			for (int i = 1; i <= n; i++) {
				if (cost[i] != Integer.MAX_VALUE) {
					count++;
					if (cost[i] > maxtime) maxtime = cost[i];
				}
			}
			System.out.println(count + " " + maxtime);
		}
	}

	private static void dijkstra(int i) {
		Arrays.fill(cost, Integer.MAX_VALUE);
		cost[i] = 0;
		// 시작점부터 x까지 가는데 드는 거리
		PriorityQueue<Integer[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		pq.offer(new Integer[] {i, 0});
		while (!pq.isEmpty()) {
			Integer[] spot = pq.poll();
			int end = spot[0];
			int length = spot[1];
			// end에서 뻗어나갈 수 있는 곳
			for (Integer[] to : route[end]) {
				if (cost[to[0]] > length + to[1]) {
					cost[to[0]] = length + to[1];
					pq.offer(new Integer[] {to[0], cost[to[0]]});
				}
			}
		}
	}
}