import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[][] castle = new int[m][n];
		// 1번부터 차례대로 방에 번호를 붙이기
		int roomNum = 1;
		// 방 크기
		Map<Integer, Integer> roomSize = new HashMap<>();
		int[][] visited = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				castle[i][j] = sc.nextInt();
			}
		}
		// 서 북 동 남
		int[] dx = {0, -1, 0, 1};
		int[] dy = {-1, 0, 1, 0};
		// 방에 번호를 붙이자
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				// 방만들기
				if (visited[i][j] == 0) {
					visited[i][j] = roomNum;
					int roomCnt = 1;
					Queue<int[]> queue = new LinkedList<>();
					queue.offer(new int[] {i, j});
					while (!queue.isEmpty()) {
						int[] xy = queue.poll();
						int x = xy[0];
						int y = xy[1];
						for (int d = 0; d < 4; d++) {
							// 벽이 없어
							if ((castle[x][y] & (1 << d)) == 0) {
								int nx = x + dx[d];
								int ny = y + dy[d];
								// 인덱스 있음
								if (nx >= 0 && nx < m && ny >= 0 && ny < n && visited[nx][ny] == 0) {
									queue.offer(new int[] {nx, ny});
									visited[nx][ny] = roomNum;
									roomCnt++;
								}
							}
						}
					}
					roomSize.put(roomNum, roomCnt);
					roomNum++;
				}
			}
		}
		// 방이 어느 방이랑 붙어있는지 파악하기
		boolean[][] roomNext = new boolean[roomNum][roomNum];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				for (int d = 0; d < 3; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					// 인덱스 있고
					if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
						if (visited[i][j] != visited[nx][ny]) {
							roomNext[visited[i][j]][visited[nx][ny]] = true;
							roomNext[visited[nx][ny]][visited[i][j]] = true;
						}
					}
					
				}
			}
		}
		int ans3 = 0;
		for (int i = 1; i < roomNum - 1; i++) {
			for (int j = i + 1; j < roomNum; j++) {
				// i번 방이랑 j번 방이랑 붙어있음
				if (roomNext[i][j]) {
					if (roomSize.get(i) + roomSize.get(j) > ans3) {
						ans3 = roomSize.get(i) + roomSize.get(j);
					}
				}
			}
		}
		int ans1 = roomNum - 1;
		int ans2 = 0;
		for (int v : roomSize.values()) {
			if (v > ans2) {
				ans2 = v;
			}
		}
		System.out.println(ans1);
		System.out.println(ans2);
		System.out.println(ans3);
	}
	
}