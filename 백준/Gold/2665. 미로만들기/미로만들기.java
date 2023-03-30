import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		char[][] miro = new char[n][n];
		for (int i = 0; i < n; i++) {
			miro[i] = sc.next().toCharArray();
		}
		int[][] visited = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(visited[i], Integer.MAX_VALUE);
		}
		visited[0][0] = cost(miro[0][0]);
		PriorityQueue<Integer[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		pq.offer(new Integer[] {0, 0, visited[0][0]});
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		while (!pq.isEmpty()) {
			Integer[] spot = pq.poll();
			int x = spot[0];
			int y = spot[1];
			int c = spot[2];
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
					if (visited[nx][ny] > c + cost(miro[nx][ny])) {
						visited[nx][ny] = c + cost(miro[nx][ny]);
						pq.offer(new Integer[] {nx, ny, visited[nx][ny]});
					}
				}
			}
		}
		System.out.println(visited[n - 1][n - 1]);
	}
	
	public static int cost(char x) {
		if (x == '1') return 0;
		return 1;
	}
}