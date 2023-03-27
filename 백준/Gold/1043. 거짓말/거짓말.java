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
		String[] line = br.readLine().split(" ");
		// 사람
		int n = Integer.parseInt(line[0]);
		// 파티
		int m = Integer.parseInt(line[1]);
		
		// 진실을 아는 사람이 진실을 다 전염시키고
		// 진실을 모르는 사람만 남은 파티에 갈 수 있음
		line = br.readLine().split(" ");
		int t = Integer.parseInt(line[0]);
		int[] truth = new int[t];
		for (int i = 0; i < t; i++) {
			truth[i] = Integer.parseInt(line[1 + i]);
		}
		// i번 사람은 j번 파티에 갔음
		List<Integer>[] party = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			party[i] = new ArrayList<>();
		}
		// i번 파티에 j가 왔음
		List<Integer>[] people = new ArrayList[m];
		for (int i = 0; i < m; i++) {
			people[i] = new ArrayList<>();
		}
		for (int i = 0; i < m; i++) {
			line = br.readLine().split(" ");
			// p명이 파티에 왔음
			int p = Integer.parseInt(line[0]);
			for (int j = 0; j < p; j++) {
				party[Integer.parseInt(line[1 + j])].add(i);
				people[i].add(Integer.parseInt(line[1 + j]));
			}
		}
		// 파티마다 진실을 전염시키기
		// 갈 수 없는 파티인가?
		boolean[] cantgo = new boolean[m];
		boolean[] visited = new boolean[n + 1];
		for (int tr : truth) {
			if (!visited[tr]) {
				visited[tr] = true;
				Queue<Integer> queue = new LinkedList<>();
				queue.offer(tr);
				while (!queue.isEmpty()) {
					int x = queue.poll();
					for (int pa : party[x]) {
						// x가 참가한 파티
						cantgo[pa] = true;
						for (int y : people[pa]) {
							// y도 진실을 알게 되었음
							if (!visited[y]) {
								visited[y] = true;
								queue.offer(y);
							}
						}
					}
				}
			}
		}
		int cnt = 0;
		for (int i = 0; i < m; i++) {
			if (!cantgo[i]) cnt++;
		}
		System.out.println(cnt);
	}	
}
