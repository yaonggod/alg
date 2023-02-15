import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int tc = 1; tc < t + 1; tc++) {
			int m = sc.nextInt();
			int a = sc.nextInt();
			int[][][] map = new int[10][10][a];
			// 사람 이동
			int[] movea = new int[m];
			int[] moveb = new int[m];
			for (int i = 0; i < m; i++) {
				movea[i] = sc.nextInt();
			}
			for (int i = 0; i < m; i++) {
				moveb[i] = sc.nextInt();
			}
			// 배터리 충전량 : 굳이 a, b를 나눠서 관리할 필요가 없는듯
			int battery = 0;
			// 배터리 영역
			for (int i = 0; i < a; i++) {
				int x = sc.nextInt() - 1;
				int y = sc.nextInt() - 1;
				int c = sc.nextInt();
				int p = sc.nextInt();
				for (int xx = -c; xx < c + 1; xx++) {
					for (int yy = -c; yy < c + 1; yy++) {
						if (0 <= x + xx && x + xx < 10 && 0 <= y + yy && y + yy < 10 && Math.abs(xx) + Math.abs(yy) <= c) {
							map[y + yy][x + xx][i] = p;
						}
					}
				}
			}
			// 사람 움직임
			// AB 시작
			int start = 0;
			for (int i = 0; i < a; i++) {
				for (int j = 0; j < a; j++) {
					// 배터리 영역이 겹치면 
					if (i == j && map[0][0][i] == map[9][9][j] && map[0][0][i] != 0) {
						if (map[0][0][i] > start) {
							start = map[0][0][1];
						}
					} else {
						if (map[0][0][i] + map[9][9][j] > start) {
							start = map[0][0][i] + map[9][9][j];
						}
					}
				}
			}
			battery += start;
			// X 상 우 하 좌
			int[] dx = {0, -1, 0, 1, 0};
			int[] dy = {0, 0, 1, 0, -1};
			// x, y
			int ax = 0;
			int ay = 0;
			int bx = 9;
			int by = 9;
			
			// 움직이자 m번
			for (int i = 0; i < m; i++) {
				int charge = 0;
				ax += dx[movea[i]];
				ay += dy[movea[i]];
				bx += dx[moveb[i]];
				by += dy[moveb[i]];
				// 충전량
				for (int j = 0; j < a; j++) {
					for (int k = 0; k < a; k++) {
						if (j == k && map[ax][ay][j] == map[bx][by][k] && map[ax][ay][j] != 0) {
							if (map[ax][ay][j] > charge) {
								charge = map[ax][ay][j];
							}
						} else {
							if (map[ax][ay][j] + map[bx][by][k] > charge) {
								charge = map[ax][ay][j] + map[bx][by][k];
							}
						}
					}
				}
				battery += charge;
			}
			System.out.println("#" + tc + " " + battery);
		}
	}
}