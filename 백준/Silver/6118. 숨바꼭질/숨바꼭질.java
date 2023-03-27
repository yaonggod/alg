
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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int m = Integer.parseInt(line[1]);
		// 헛간
		List<Integer>[] box = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			box[i] = new ArrayList<>();
		}
		for (int i = 0; i < m; i++) {
			line = br.readLine().split(" ");
			int A = Integer.parseInt(line[0]);
			int B = Integer.parseInt(line[1]);
			box[A].add(B);
			box[B].add(A);
		}
		
		Queue<Integer[]> queue = new LinkedList<>();
		int[] visited = new int[n + 1];
		// 1번부터 시작
		queue.offer(new Integer[] {1, 0});
		// 안가본데는 1, 가본데는 거리
		Arrays.fill(visited, -1);
		visited[1] = 0;
		// 최대거리
		int maxlen = 0;
		
		while (!queue.isEmpty()) {
			Integer[] xl = queue.poll();
			int x = xl[0];
			int l = xl[1];
			if (l > maxlen) {
				maxlen = l;
			}
			for (int y : box[x]) {
				if (visited[y] == -1) {
					visited[y] = l + 1;
					queue.offer(new Integer[] {y, l + 1});
				}
			}
		}
		// 숨어야 하는 헛간 - 제일 작은거
		int ans1 = 0;
		// 같은 거리 헛간 개수
		int ans3 = 0;
		for (int i = n; i >= 1; i--) {
			if (visited[i] == maxlen) {
				ans1 = i;
				ans3++;
			}
		}
		System.out.println(ans1 + " " + maxlen + " " + ans3);
	}	
}