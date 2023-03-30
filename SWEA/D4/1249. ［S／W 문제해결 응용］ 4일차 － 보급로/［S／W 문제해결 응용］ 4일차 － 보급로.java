import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {
	public static class Spot implements Comparable<Spot> {
		// x, y좌표까지 가는데 걸리는 시간
		int x; int y; int length;

		public Spot(int x, int y, int length) {
			super();
			this.x = x;
			this.y = y;
			this.length = length;
		}

		@Override
		public int compareTo(Spot o) {
			return this.length - o.length;
		}
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int t = sc.nextInt();
		for (int tc = 1; tc <= t; tc++) {
			sb.append("#" + tc + " ");
			int n = sc.nextInt();
			char[][] board = new char[n][n];
			for (int i = 0; i < n; i++) {
				board[i] = sc.next().toCharArray();
			}
			int[] dx = {-1, 1, 0, 0};
			int[] dy = {0, 0, -1, 1};
			int[][] cost = new int[n][n];
			for (int i = 0; i < n; i++) {
				Arrays.fill(cost[i], Integer.MAX_VALUE);
			}
			cost[0][0] = 0;
			PriorityQueue<Spot> pq = new PriorityQueue<>();
			pq.offer(new Spot(0, 0, 0));
			while (!pq.isEmpty()) {
				Spot s = pq.poll();
				for (int d = 0; d < 4; d++) {
					int nx = s.x + dx[d];
					int ny = s.y + dy[d];
					if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
						// xy까지 온 거리 + nxny cost 갱신 가능
						if (cost[nx][ny] > s.length + (board[nx][ny] - '0')) {
							cost[nx][ny] = s.length + (board[nx][ny] - '0');
							pq.offer(new Spot(nx, ny, cost[nx][ny]));
						}
					}
				}
			}
			sb.append(cost[n - 1][n - 1] + "\n");
		}
		System.out.println(sb);
	}
}