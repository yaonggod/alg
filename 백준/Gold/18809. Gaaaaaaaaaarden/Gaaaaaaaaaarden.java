import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int n, m, g, r, t, maxFlower;
	static int[][] garden;
	static int[] picked, tx, ty, arrg, arrr, dx, dy;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		g = sc.nextInt();
		r = sc.nextInt();
		// 배양액 가능한데
		// tP(g + r)
		t = 0;
		garden = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				garden[i][j] = sc.nextInt();
				if (garden[i][j] == 2) {
					t++;
				}
			}
		}
		tx = new int[t];
		ty = new int[t];
		int idx = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (garden[i][j] == 2) {
					tx[idx] = i;
					ty[idx] = j;
					idx++;
				}
			}
		}
		// 배양액을 뿌릴 수 있는 땅의 개수(10개 이하) 중 배양액의 개수만큼 순열...
		picked = new int[t];
		arrg = new int[g];
		arrr = new int[r];
		maxFlower = 0;
		// 방향
		dx = new int[] {-1, 1, 0, 0};
		dy = new int[] {0, 0, -1, 1};
		// 순열이 아니라 조합이였음을...
		comb(0, 0, 0);
		System.out.println(maxFlower);
	}
	
	public static void comb(int idx, int gi, int ri) {
		// 다넣었어
		if (gi == g && ri == r) {
			if (gardening(arrg, arrr) > maxFlower) {
				maxFlower = gardening(arrg, arrr);
			}
			return;
		}
		if (idx == t) {
			return;
		}
		// idx를 g에 넣거나
		if (gi < g) {
			arrg[gi] = idx;
			comb(idx + 1, gi + 1, ri);
			arrg[gi] = 0;
		}
		// idx를 r에 넣거나
		if (ri < r) {
			arrr[ri] = idx;
			comb(idx + 1, gi, ri + 1);
			arrr[ri] = 0;
		}
		// 안넣거나
		comb(idx + 1, gi, ri);
		
		
		
	}
	// 배양액을 뿌려서 피울 수 있는 꽃
	public static int gardening(int[] arrg, int[] arrr) {
		// 배양액이 도착한 시간
		int[][][] visited = new int[n][m][2];
		int flower = 0;
		Queue<Integer[]> queue = new LinkedList<>();
		// 초록색
		for (int i = 0; i < g; i++) {
			// 배양액 뿌릴 x좌표, y좌표, 시간, 초록색
			queue.offer(new Integer[] {tx[arrg[i]], ty[arrg[i]], 1, 1});
			// 여기에 초록색 뿌렸음
			visited[tx[arrg[i]]][ty[arrg[i]]][0] = 1;
		}
		// 빨간색
		for (int i = 0; i < r; i++) {
			// 배양액 뿌릴 x좌표, y좌표, 시간, 빨간색
			queue.offer(new Integer[] {tx[arrr[i]], ty[arrr[i]], 1, 2});
			// 여기에 빨간색 뿌렸음
			visited[tx[arrr[i]]][ty[arrr[i]]][1] = 1;
		}
		
		while (!queue.isEmpty()) {
			Integer[] xytc = queue.poll();
			int x = xytc[0];
			int y = xytc[1];
			int time = xytc[2];
			int color = xytc[3];
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				// 인덱스 안
				if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
					// 초록색
					if (color == 1) {
						// 갈 수 있는 땅이고 배양액 뿌린적없음, 번질 수 있음
						if (garden[nx][ny] != 0 && visited[x][y][0] != -1 && visited[nx][ny][0] == 0 && visited[nx][ny][1] == 0) {
							// 뿌려
							visited[nx][ny][0] = time + 1;
							queue.offer(new Integer[] {nx, ny, time + 1, color});
						}
					}
					// 빨간색
					if (color == 2) {
						// 초록색이랑 같이 도착했음
						if (garden[nx][ny] != 0 && visited[nx][ny][0] == time + 1 && visited[nx][ny][1] == 0) {
							// 뿌리고 꽃 추가
							visited[nx][ny][1] = time + 1;
							// 이제 번질 수 없음
							visited[nx][ny][0] = -1;
							visited[nx][ny][1] = -1;
							flower++;
						// 갈 수 있는 땅이고 배양액 뿌린적없음
						} else if (garden[nx][ny] != 0 && visited[nx][ny][0] == 0 && visited[nx][ny][1] == 0) {
							visited[nx][ny][1] = time + 1;
							queue.offer(new Integer[] {nx, ny, time + 1, color});
						}
					}
				}
			}
		}
		return flower;
	}
}