import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 큐에다가 횟수 소모값 넣어서 돌리고
		// 한다 안한다 
		int k = sc.nextInt();
		int w = sc.nextInt();
		int h = sc.nextInt();
		int[][] land = new int[h][w];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				land[i][j] = sc.nextInt();
			}
		}
		// 말
		int[] hx = {-2, -1, -2, -1, 2, 1, 2, 1};
		int[] hy = {-1, -2, 1, 2, -1, -2, 1, 2};
		// 일반
		int[] dx = {0, 0, 1, -1};
		int[] dy = {-1, 1, 0, 0};
		
		Queue<Integer[]> queue = new LinkedList<>();
		// 원숭이 시작
		queue.offer(new Integer[] {0, 0, k, 0});
		// visited를 3차원으로 구현해야할거같은데
		int[][][] visited = new int[k + 1][h][w];
		visited[k][0][0] = 1;
		int ans = -1;
		while (!queue.isEmpty()) {
			Integer[] arr = queue.poll();
			int x = arr[0];
			int y = arr[1];
			int ht = arr[2];
			int cnt = arr[3];
			if (x == h - 1 && y == w - 1) {
				ans = cnt;
				break;
			}
			// 말 움직이기
			if (ht > 0) {
				for (int i = 0; i < 8; i++) {
					int nx = x + hx[i];
					int ny = y + hy[i];
					if (nx >= 0 && nx < h && ny >= 0 && ny < w && visited[ht - 1][nx][ny] == 0) {
						if (land[nx][ny] == 0) {
							visited[ht - 1][nx][ny] = visited[ht][x][y] + 1;
							queue.offer(new Integer[] {nx, ny, ht - 1, cnt + 1});							
						}
					}
				}
			}
			// 일반 움직이기
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx >= 0 && nx < h && ny >= 0 && ny < w && visited[ht][nx][ny] == 0) {
					if (land[nx][ny] == 0) {
						visited[ht][nx][ny] = visited[ht][x][y];
						queue.offer(new Integer[] {nx, ny, ht, cnt + 1});
					}
				}
			}
		}	
		System.out.println(ans);
	}
}