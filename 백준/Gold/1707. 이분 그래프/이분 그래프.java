import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		int k = Integer.parseInt(line[0]);
		for (int tc = 0; tc < k; tc++) {
			line = br.readLine().split(" ");
			int v = Integer.parseInt(line[0]);
			int e = Integer.parseInt(line[1]);
			List<Integer>[] graph = new ArrayList[v + 1];
			for (int i = 1; i <= v; i++) {
				graph[i] = new ArrayList<>();
			}
			for (int i = 0; i < e; i++) {
				line = br.readLine().split(" ");
				int x = Integer.parseInt(line[0]);
				int y = Integer.parseInt(line[1]);
				graph[x].add(y);
				graph[y].add(x);
			}
			// 안가본 정점은 1로 칠하고 시작
			String ans = "YES";
			int[] visited = new int[v + 1];
			for (int i = 1; i <= v; i++) {
				Queue<Integer> queue = new LinkedList<>();
				if (visited[i] == 0) {
					visited[i] = 1;
					queue.offer(i);
					while (!queue.isEmpty()) {
						int x = queue.poll();
						for (int y : graph[x]) {
							// 안방문했으면 칠해 맞닿은 점이 다른 수로
							if (visited[x] == 1 && visited[y] == 0) {
								visited[y] = 2;
								queue.offer(y);
							} else if (visited[x] == 2 && visited[y] == 0) {
								visited[y] = 1;
								queue.offer(y);
							// 규칙에 위배됨
							} if (visited[x] == visited[y]) {
								ans = "NO";
								break;
							}
						}
					}
				}
				if (ans == "NO") {
					break;
				}
			}
			sb.append(ans + "\n");
		}
		System.out.println(sb);
	}	
}