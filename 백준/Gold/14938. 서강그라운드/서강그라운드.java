import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	static int n, m, r;
	static int[] item;
	static List<Integer[]>[] route;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		r = sc.nextInt();
		item = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			item[i] = sc.nextInt();
		}
		route = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			route[i] = new ArrayList<>();
		}
		for (int i = 0; i < r; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int l = sc.nextInt();
			route[a].add(new Integer[] {b, l});
			route[b].add(new Integer[] {a, l});
		}
		int result = 0;
		for (int i = 1; i <= n; i++) {
			if (dijkstra(i) > result) result = dijkstra(i);
		}
		System.out.println(result);
	}

	private static int dijkstra(int i) {
		int[] cost = new int[n + 1];
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
		int result = 0;
		for (int j = 1; j <= n; j++) {
			if (cost[j] <= m) result += item[j];
		}
		return result;
	}
}