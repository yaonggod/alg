import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 컴퓨터
		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int m = Integer.parseInt(line[1]);
		List<Integer>[] computer = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			computer[i] = new ArrayList<>();
		}
		// B를 해킹하면 A를 해킹할 수 있다
		// 역방향은 성립하지 않음
		for (int i = 0; i < m; i++) {
			line = br.readLine().split(" ");
			int A = Integer.parseInt(line[0]);
			int B = Integer.parseInt(line[1]);
			computer[B].add(A);
		}
		int[] count = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			Queue<Integer> queue = new LinkedList<>();
			queue.offer(i);
			boolean[] visited = new boolean[n + 1];
			visited[i] = true;
			count[i] = 1;
			while (!queue.isEmpty()) {
				int x = queue.poll();
				for (int y : computer[x]) {
					if (!visited[y]) {
						// 감염시켰음
						// 방문처리
						visited[y] = true;
						// 감염된 컴퓨터++
						count[i]++;
						// 큐에 넣기
						queue.offer(y);
					}
				}
			}
		}
		int maxCnt = 0;
		for (int i = 1; i <= n; i++) {
			if (count[i] > maxCnt) {
				maxCnt = count[i];
			}
		}
		for (int i = 1; i <= n; i++) {
			if (count[i] == maxCnt) {
				System.out.print(i + " ");
			}
		}
	}	
}