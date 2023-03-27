import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	
	public static void main(String[] args) throws IOException {
		// 집합들을 분리해서 그 집합에서 가장 친구비가 적은 친구를 고르기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int m = Integer.parseInt(line[1]);
		int k = Integer.parseInt(line[2]);
		int[] fee = new int[n + 1];
		line = br.readLine().split(" ");
		// 친구가 요구하는 친구비
		for (int i = 1; i <= n; i++) {
			fee[i] = Integer.parseInt(line[i - 1]);
		}
		// 친구 그래프
		List<Integer>[] friends = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			friends[i] = new ArrayList<>();
		}
		for (int i = 0; i < m; i++) {
			line = br.readLine().split(" ");
			int v = Integer.parseInt(line[0]);
			int w = Integer.parseInt(line[1]);
			friends[v].add(w);
			friends[w].add(v);
		}
		
		// 친구 집합 만들기
		int group = 1;
		int[] visited = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			if (visited[i] == 0) {
				visited[i] = group;
				Queue<Integer> queue = new LinkedList<>();
				queue.offer(i);
				while (!queue.isEmpty()) {
					int x = queue.poll();
					for (int y : friends[x]) {
						if (visited[y] == 0) {
							visited[y] = group;
							queue.offer(y);
						}
					}
				}
				// 그래프 다 만들었으니 다음 그래프로 ㄱㄱ
				group++;
			}	
		}
		int[] minfee = new int[group];
		Arrays.fill(minfee, 10000);
		for (int i = 1; i <= n; i++) {
			if (fee[i] < minfee[visited[i]]) {
				minfee[visited[i]] = fee[i];
			}
		}
		int minsum = 0;
		for (int i = 1; i < group; i++) {
			minsum += minfee[i];
		}
		if (minsum > k) {
			System.out.println("Oh no");
		} else {
			System.out.println(minsum);
		}
	}	
}