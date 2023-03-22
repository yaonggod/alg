import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			int l = sc.nextInt();
			int r = sc.nextInt();
			int c = sc.nextInt();
			if (l == 0 && r == 0 && c == 0) {
				break;
			}
			int sf = 0;
			int sx = 0;
			int sy = 0;
			char[][][] building = new char[l][r][c];
			for (int i = 0; i < l; i++) {
				for (int j = 0; j < r; j++) {
					building[i][j] = sc.next().toCharArray();
					for (int k = 0; k < c; k++) {
						if (building[i][j][k] == 'S') {
							sf = i;
							sx = j;
							sy = k;
						}
					}
				}
			}
			boolean[][][] visited = new boolean[l][r][c];
			// 동 서 남 북 상 하
			int[] df = {0, 0, 0, 0, -1, 1};
			int[] dx = {0, 0, 1, -1, 0, 0};
			int[] dy = {1, -1, 0, 0, 0, 0};
			
			String message = "Trapped!";
			
			Queue<Integer[]> queue = new LinkedList<>();
			queue.offer(new Integer[] {sf, sx, sy, 0});
			visited[sf][sx][sy] = true;
			while (!queue.isEmpty()) {
				Integer[] fxy = queue.poll();
				int f = fxy[0];
				int x = fxy[1];
				int y = fxy[2];
				int time = fxy[3];
				if (building[f][x][y] == 'E') {
					message = "Escaped in " + time + " minute(s).";
					break;
				}
				for (int d = 0; d < 6; d++) {
					int nf = f + df[d];
					int nx = x + dx[d];
					int ny = y + dy[d];
					if (nf >= 0 && nf < l && nx >= 0 && nx < r && ny >= 0 && ny < c && visited[nf][nx][ny] == false) {
						if (building[nf][nx][ny] != '#') {
							visited[nf][nx][ny] = true;
							queue.offer(new Integer[] {nf, nx, ny, time + 1});
						}
					}
				}
			}
			System.out.println(message);
		}
	}
}
