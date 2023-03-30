import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static class Spot implements Comparable<Spot> {
		int x; int y; int garbage; int pass;
		
		public Spot(int x, int y, int garbage, int pass) {
			super();
			this.x = x;
			this.y = y;
			this.garbage = garbage;
			this.pass = pass;
		}

		@Override
		public int compareTo(Spot o) {
			if (this.garbage > o.garbage) return 1;
			if (this.garbage == o.garbage) {
				if (this.pass > o.pass) return 1;
			} return -1;
		}
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int sx = 0;
		int sy = 0;
		int fx = 0; 
		int fy = 0;
		char[][] map = new char[n][m];
		for (int i = 0; i < n; i++) {
			map[i] = sc.next().toCharArray();
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 'S') {
					sx = i; sy = j;
				} else if (map[i][j] == 'F') {
					fx = i; fy = j;
				} 
			}
		}
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		int[][][] meet = new int[n][m][2];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				Arrays.fill(meet[i][j], Integer.MAX_VALUE);				
			}
		}
		PriorityQueue<Spot> pq = new PriorityQueue<>();
		pq.offer(new Spot(sx, sy, 0, 0));
		meet[sx][sy][0] = 0;
		meet[sx][sy][1] = 0;
		while (!pq.isEmpty()) {
			Spot s = pq.poll();
			for (int d = 0; d < 4; d++) {
				int nx = s.x + dx[d];
				int ny = s.y + dy[d];
				int g2 = s.garbage;
				int p2 = s.pass;
				if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
					// 그 칸 자체가 쓰레기다
					if (map[nx][ny] == 'g') g2++;
					else if (map[nx][ny] == '.') {
						// 그 칸 주변에 쓰레기가 있다
						boolean pass = false;
						for (int dd = 0; dd < 4; dd++) {
							int nnx = nx + dx[dd];
							int nny = ny + dy[dd];
							if (nnx >= 0 && nnx < n && nny >= 0 && nny < m) {
								if (map[nnx][nny] == 'g') {
									pass = true;
								}
							}
						}
						if (pass) p2++;
					}
					// 최소값 갱신
					if (meet[nx][ny][0] > g2) {
						meet[nx][ny][0] = g2;
						meet[nx][ny][1] = p2;
						pq.offer(new Spot(nx, ny, g2, p2));
					} else if (meet[nx][ny][0] == g2 && meet[nx][ny][1] > p2) {
						meet[nx][ny][1] = p2;
						pq.offer(new Spot(nx, ny, g2, p2));
					}
				}
			}
		}
		System.out.println(meet[fx][fy][0] + " " + meet[fx][fy][1]);
	}
	
}