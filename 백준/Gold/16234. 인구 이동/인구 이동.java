import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int l = sc.nextInt();
		int r = sc.nextInt();
		int[][] land = new int[n][n];
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		int t = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				land[i][j] = sc.nextInt();
			}
		}
		while (true) {
			boolean change = false;
			boolean[][] visited = new boolean[n][n];
			Queue<Integer> qx = new LinkedList<>();
			Queue<Integer> qy = new LinkedList<>();
			Stack<Integer> sx = new Stack<>();
			Stack<Integer> sy = new Stack<>();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (!visited[i][j]) {
						visited[i][j] = true;
						int count = 1;
						qx.offer(i);
						qy.offer(j);
						sx.add(i);
						sy.add(j);
						while (!qx.isEmpty()) {
							int x = qx.poll();
							int y = qy.poll();
							for (int d = 0; d < 4; d++) {
								int nx = x + dx[d];
								int ny = y + dy[d];
								if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny]) {
									int gap = Math.abs(land[x][y] - land[nx][ny]);
									if (gap <= r && gap >= l) {
										visited[nx][ny] = true;
										qx.offer(nx);
										qy.offer(ny);
										sx.add(nx);
										sy.add(ny);
										count++;
									}
								}
							}
						}
						if (count > 1) {
							change = true;
						}
						int people = 0;
						for (int s = 0; s < sx.size(); s++) {
							people += land[sx.get(s)][sy.get(s)];
						}
						int result = people / count;
						while (!sx.isEmpty()) {
							land[sx.pop()][sy.pop()] = result;
						}
					}
				}
			}
			if (change) {
				t++;
			} else {
				break;
			}
		}
		System.out.println(t);
	}
}