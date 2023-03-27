import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		// idx번 친구는 누구랑 친구인가
		List<Integer>[] friend = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			friend[i] = new ArrayList<>();
		}
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			friend[a].add(b);
			friend[b].add(a);
		}
		boolean[] visited = new boolean[n + 1];
		// 상근이 true
		visited[1] = true;
		Queue<Integer[]> queue = new LinkedList<>();
		// 큐에 상근이 넣기
		// c = 0 : 상근이 / c = 1 : 상근이 친구 / c = 2 : 상근이 친구의 친구
		queue.offer(new Integer[] {1, 0});
		// 친구 세기
		int f = 0;
		while (!queue.isEmpty()) {
			Integer[] xc = queue.poll();
			int x = xc[0];
			int c = xc[1];
			// 상근이 친구의 친구까지만 넣자
			if (c < 2) {
				for (int y : friend[x]) {
					if (!visited[y]) {
						f++;
						visited[y] = true;
						queue.offer(new Integer[] {y, c + 1});
					}
				}	
			}
		}
		System.out.println(f);
	}	
}