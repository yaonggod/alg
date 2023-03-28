import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static class Spot implements Comparable<Spot> {
		// (x, y)로 오면서 루트에 몇 개의 벽이 있었는지
		int x;
		int y;
		int cost;
		
		public Spot(int x, int y, int cost) {
			super();
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Spot o) {
			return this.cost - o.cost;
		}
	}
	public static void main(String[] args) {
		// BFS해서 거쳐온 벽이 적은 스팟이 우선적으로 나오게
		// x, y 꺼내서 4방탐색해서 벽을 cost에 더해줘서 PQ에 다시 넣음
		// int[][] count해서 지금까지 거쳐온 벽의 수의 최소값 갱신
		// 뱅글뱅글 돌아서 벽 뿌순거 없이 갈 수 있는 경로도 있으니까 visited 필요없음, 다만 cost 저장 배열 필요
		// (0, 0)에서 (n - 1, m - 1)로 가고 싶음
		Scanner sc = new Scanner(System.in);
		int m = sc.nextInt();
		int n = sc.nextInt();
		char[][] algo = new char[n][m];
		for (int i = 0; i < n; i++) {
			algo[i] = sc.next().toCharArray();
		}
		
		PriorityQueue<Spot> pq = new PriorityQueue<>();
		pq.offer(new Spot(0, 0, 0));
		// 최소값 갱신
		int[][] cost = new int[n][m];
		for (int i = 0; i < n; i++) {
			Arrays.fill(cost[i], Integer.MAX_VALUE);
		}
		cost[0][0] = 0;
		
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		while (!pq.isEmpty()) {
			Spot s = pq.poll();
			// 최소값이랑 같거나 최소값보다 작을 떄만 ㄱㄱ
			if (s.cost <= cost[s.x][s.y]) {
				for (int d = 0; d < 4; d++) {
					int nx = s.x + dx[d];
					int ny = s.y + dy[d];
					if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
						if (s.cost + (algo[nx][ny] - '0') < cost[nx][ny]) {
							// 갱신
							cost[nx][ny] = s.cost + (algo[nx][ny] - '0');
							pq.offer(new Spot(nx, ny, s.cost + (algo[nx][ny] - '0')));
						}	
					}
				}
			}
		}
		System.out.println(cost[n - 1][m - 1]);
	}
}