import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
	public static int[][] board, wormhole;
	public static int[] dx, dy;
	public static int n;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int t = sc.nextInt();
		for (int tc = 1; tc <= t; tc++) {
			sb.append("#" + tc + " ");
			n = sc.nextInt();
			board = new int[n][n];
			// 상 우 하 좌
			dx = new int[] {-1, 0, 1, 0};
			dy = new int[] {0, 1, 0, -1};
			// 시작점이 될 수 있는 곳 == 0
			List<Integer> startx = new ArrayList<>();
			List<Integer> starty = new ArrayList<>();
			// 웜홀 최대 5개 - 웜홀 숫자에 6 뺀 인덱스에 저장
			// 1x, 1y, 2x, 2y
			wormhole = new int[5][4]; 
			// 몇번째 웜홀까지 저장했는지
			int[] wormholeidx = new int[5];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					board[i][j] = sc.nextInt();
					if (board[i][j] == 0) {
						startx.add(i);
						starty.add(j);
					// 웜홀 정보 저장
					} else if (board[i][j] >= 6 && board[i][j] <= 10) {
						// 웜홀 쌍이 처음으로 나타남
						if (wormholeidx[board[i][j] - 6] == 0) {
							wormhole[board[i][j] - 6][0] = i;
							wormhole[board[i][j] - 6][1] = j;
							wormholeidx[board[i][j] - 6]++;
						// 두 번째 웜홀이 나타남
						} else if (wormholeidx[board[i][j] - 6] == 1) {
							wormhole[board[i][j] - 6][2] = i;
							wormhole[board[i][j] - 6][3] = j;
						}
					}
				}
			}
			// 최대값 저장
			int maxScore = 0;
			for (int i = 0; i < startx.size(); i++) {
				for (int d = 0; d < 4; d++) {
					int result = pinball(startx.get(i), starty.get(i), d);
					if (result > maxScore) maxScore = result;
				}
			}
			sb.append(maxScore + "\n");
		}
		System.out.println(sb);
	}
	private static int pinball(Integer x, Integer y, int d) {
		int score = 0;
		int nx = x;
		int ny = y;
		while (true) {
			// 다음에 갈 좌표
			nx += dx[d];
			ny += dy[d];
			// 벽이다 - 미리 검사해서 인덱스에러 방지
			if (nx == -1 || nx == n || ny == -1 || ny == n) {
				// 점수 더하고
				score++;
				// 방향 전환한다 180도
				d = (d + 2) % 4;
			}
			
			// 블랙홀을 만났거나
			else if (board[nx][ny] == -1) break;
			// 출발점으로 돌아왔을 때 브레이크
			else if (nx == x && ny == y) break;
			
			// 만난 지점이 블록이다
			else if (board[nx][ny] >= 1 && board[nx][ny] <= 5) {
				// 점수를 더하고
				score++;
				// 방향 변화
				d = dir(d, board[nx][ny]);
			}
			
			// 만난 지점이 웜홀이다 - 방향은 유지
			else if (board[nx][ny] >= 6 && board[nx][ny] <= 10) {
				// 첫 번째로 저장된 웜홀이면
				if (nx == wormhole[board[nx][ny] - 6][0] && ny == wormhole[board[nx][ny] - 6][1]) {
					// 두 번째로 간다
					int tempx = nx;
					int tempy = ny;
					nx = wormhole[board[tempx][tempy] - 6][2];
					ny = wormhole[board[tempx][tempy] - 6][3];
				// 두 번째 웜홀이면 첫번째로 감
				} else {
					int tempx = nx;
					int tempy = ny;
					nx = wormhole[board[tempx][tempy] - 6][0];
					ny = wormhole[board[tempx][tempy] - 6][1];
				}
			}
		}
		return score;
	}
	// 핀볼이 블록에 충돌했을 때 방향 처리
	// 상 우 하 좌
	// d방향으로 가고 있었는데 몇 번 블록을 만났다
	public static int dir(int d, int block) {
		if (d == 0) {
			if (block == 1 || block == 4 || block == 5) {
				return 2;
			} else if (block == 2) {
				return 1;
			} else {
				return 3;
			}
		} else if (d == 1) {
			if (block == 1 || block == 2 || block == 5) {
				return 3;
			} else if (block == 3) {
				return 2;
			} else {
				return 0;
			}
		} else if (d == 2) {
			if (block == 2 || block == 3 || block == 5) {
				return 0;
			} else if (block == 1) {
				return 1;
			} else {
				return 3;
			}
		} else {
			if (block == 3 || block == 4 || block == 5) {
				return 1;
			} else if (block == 1) {
				return 0;
			} else {
				return 2;
			}
		}
	}
	
	
}