import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int n, m, p;
	static int[] s, dx, dy;
	static ArrayList<ArrayList<Integer>> sx, sy;
	static char[][] board;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 행
		n = sc.nextInt();
		// 열
		m = sc.nextInt();
		// 플레이어 1부터 p까지
		p = sc.nextInt();
		// 플레이어 i는 몇 칸 확장 가능?
		s = new int[p + 1];
		for (int i = 1; i <= p; i++) {
			s[i] = sc.nextInt();
		}
		// 시작점 저장해두기
		sx = new ArrayList<ArrayList<Integer>>();
		sy = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= p; i++) {
			sx.add(new ArrayList<Integer>());
			sy.add(new ArrayList<Integer>());
		}
		// 4방
		dx = new int[] {-1, 1, 0, 0};
		dy = new int[] {0, 0, -1, 1};
		// 결과
		int[] fin = new int[p + 1];
		// 판
		board = new char[n][m];
		for (int i = 0; i < n; i++) {
			board[i] = sc.next().toCharArray();
			for (int j = 0; j < m; j++) {
				for (int pl = 1; pl <= p; pl++) {
					// 플레이어 시작점 찾음
					if (board[i][j] - '0' == pl) {
						sx.get(pl).add(i);
						sy.get(pl).add(j);
					}
				}
			}
		}
		// 사람마다 큐 만들어서 돌리기
		List<Queue<int[]>> queueList = new ArrayList<>();
		for (int i = 0; i <= p; i++) {
			queueList.add(new LinkedList<>());
		}
		for (int i = 1; i <= p; i++) {
			// x, y, 몇트째인지
			for (int j = 0; j < sx.get(i).size(); j++) {
				queueList.get(i).offer(new int[] {sx.get(i).get(j), sy.get(i).get(j), 0});
			}
		}
		
		// 모든 플레이어가 확장을 할 수 없을때까지 == 모든 플레이어의 expand의 return이 empty할때까지
		while (true) {
			for (int i = 1; i <= p; i++) {
				queueList.set(i, expand(queueList.get(i), i));
			}
			boolean allempty = true;
			for (int i = 1; i <= p; i++) {
				if (!queueList.get(i).isEmpty()) {
					allempty = false;
					break;
				}
			}
			if (allempty) {
				break;
			}
		}
		// 최종 결과
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				for (int k = 1; k <= p; k++) {
					if (board[i][j] - '0' == k) {
						fin[k]++;
					}
				}
			}
		}
		// 출력
		for (int i = 1; i <= p; i++) {
			System.out.print(fin[i] + " ");
		}
		
	}
	// n번 플레이어가 확장을 한다 - s[n]번 하고 나온 원소들을 새로운 큐에 넣어서 그걸 리턴함
	public static Queue<int[]> expand(Queue<int[]> queue, int idx) {
		Queue<int[]> queue2 = new LinkedList<>();
		while (!queue.isEmpty()) {
			int[] xyt = queue.poll();
			int x = xyt[0];
			int y = xyt[1];
			int t = xyt[2];
			if (t == s[idx]) {
				queue2.offer(new int[] {x, y, 0});
			} else {
				for (int d = 0; d < 4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					// 인덱스
					if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
						// 갈 수 있는 곳이다
						if (board[nx][ny] == '.') {
							board[nx][ny] = (char) (idx + '0');
							queue.offer(new int[] {nx, ny, t + 1});
						}
					}
				}
			}
		}
		return queue2;
	}
}
