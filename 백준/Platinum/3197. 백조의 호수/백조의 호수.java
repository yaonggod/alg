import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static char[][] lake;
	static int[][] visited;
	static int[] dx, dy;
	static int r, c, day;
	static boolean meet;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		r = sc.nextInt();
		c = sc.nextInt();
		lake = new char[r][c];
		int[][] swan = new int[2][2];
		int idx = 0;
		for (int i = 0; i < r; i++) {
			lake[i] = sc.next().toCharArray();
			for (int j = 0; j < c; j++) {
				if (lake[i][j] == 'L') {
					swan[idx][0] = i;
					swan[idx][1] = j;
					idx++;
				}
			}
		}
		dx = new int[] {-1, 1, 0, 0};
		dy = new int[] {0, 0, -1, 1};
		visited = new int[r][c];
		visited[swan[0][0]][swan[0][1]] = 1;
		visited[swan[1][0]][swan[1][1]] = 2;
		day = 0;
		meet = false;
		// 녹을 후보군 큐에 넣기
		Queue<Integer[]> ice = new LinkedList<>();
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				// 얼음인데 주변에 물이 있다
				if (lake[i][j] == 'X') {
					boolean m = false;
					for (int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						if (nx >= 0 && nx < r && ny >= 0 && ny < c && lake[nx][ny] != 'X') {
							m = true;
						}
					}
					if (m) ice.offer(new Integer[] {i, j});
				}
			}
		}
		// 0번째날 bfs를 해서 백조를 이동시킴
		Queue<Integer[]> queue = new LinkedList<>();
		queue.offer(new Integer[] {swan[0][0], swan[0][1], 1});
		queue.offer(new Integer[] {swan[1][0], swan[1][1], 2});
		// 탐색의 여지가 있는 위치는 두 번째 큐에 옮겨담기
		Queue<Integer[]> queue2 = new LinkedList<>(); 
		while (!queue.isEmpty()) {
			Integer[] spot = queue.poll();
			int x = spot[0];
			int y = spot[1];
			int sw = spot[2];
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				// 인덱스
				if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
					// 뚫려있음
					if (lake[nx][ny] != 'X') {
						// 가본적없음
						if (visited[nx][ny] == 0) {
							visited[nx][ny] = sw;
							queue.offer(new Integer[] {nx, ny, sw});
						// 다른 백조랑 만남
						} else if (visited[nx][ny] != sw) {
							meet = true;
						}
					// 막힌 곳이 있음
					} else {
						queue2.offer(spot);
					}
					
				} 
			}
			if (meet) break;
		}
		queue = queue2;
		if (meet) System.out.println(0);
		else {
			while (true) {
				day++;
				ice = melt(ice);
				queue = swan(queue);
				if (meet) break;
			}
			System.out.println(day);
		}
		
		
	}
	private static Queue<Integer[]> melt(Queue<Integer[]> queue) {
		Queue<Integer[]> queue2 = new LinkedList<>();
		// 녹임
		for (Integer[] xy : queue) {
			lake[xy[0]][xy[1]] = '.';
		}
		
		boolean[][] visited = new boolean[r][c];
		while (!queue.isEmpty()) {
			Integer[] xy = queue.poll();
			int x = xy[0];
			int y = xy[1];
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				// 인덱스, 얼음임
				if (nx >= 0 && nx < r && ny >= 0 && ny < c && lake[nx][ny] == 'X' && !visited[nx][ny]) {
					visited[nx][ny] = true;
					queue2.offer(new Integer[] {nx, ny});
				}
			}
		}
		return queue2;
	}
	
	public static Queue<Integer[]> swan(Queue<Integer[]> queue) {
		// 큐를 가지고 얼음을 녹임
		Queue<Integer[]> queue2 = new LinkedList<>();
		while (!queue.isEmpty()) {
			Integer[] spot = queue.poll();
			int x = spot[0];
			int y = spot[1];
			int sw = spot[2];
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				// 인덱스
				if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
					// 뚫려있음
					if (lake[nx][ny] != 'X') {
						// 가본적없음
						if (visited[nx][ny] == 0) {
							visited[nx][ny] = sw;
							queue.offer(new Integer[] {nx, ny, sw});
						// 다른 백조랑 만남
						} else if (visited[nx][ny] != sw) {
							meet = true;
						}
					// 막힌 곳이 있음
					} else {
						queue2.offer(spot);
					}
					
				} 
			}
			if (meet) break;
		}
		return queue2;
	}
}