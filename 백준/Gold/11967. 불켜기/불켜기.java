import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		ArrayList<int[]>[][] room = new ArrayList[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				room[i][j] = new ArrayList<>();
			}
		}
		
		for (int i = 0; i < m; i++) {
			int x = sc.nextInt() - 1;	
			int y = sc.nextInt() - 1;
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			int[] ab = {a, b};
			room[x][y].add(ab);
		}
		
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		Queue<Integer[]> queue = new LinkedList<>();
		// 불켜자
		boolean[][] lighton = new boolean[n][n];
		// 방문 - 이거 카운트하면됨
		boolean[][] visited = new boolean[n][n];
		lighton[0][0] = true;
		int count = 0;
		while (true) {
			boolean on = false;
			visited = new boolean[n][n];
			queue = new LinkedList<>();
			queue.offer(new Integer[] {0, 0});
			visited[0][0] = true;
			while (!queue.isEmpty()) {
				Integer[] xy = queue.poll();
				int x = xy[0];
				int y = xy[1];
				for (int[] i : room[x][y]) {
					if (lighton[i[0]][i[1]] == false) {
						on = true;
						lighton[i[0]][i[1]] = true;
					}
				}
				// 탐색하자
				for (int d = 0; d < 4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					// 인덱스 맞고
					if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
						if (visited[nx][ny] == false && lighton[nx][ny] == true) {
							visited[nx][ny] = true;
							queue.offer(new Integer[] {nx, ny});
						}
					}
				}
			}
			if (!on) {
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						if (lighton[i][j] == true) {
							count++;
						}
					}
				}
				break;
			}
		}
		System.out.println(count);
	}
	
}