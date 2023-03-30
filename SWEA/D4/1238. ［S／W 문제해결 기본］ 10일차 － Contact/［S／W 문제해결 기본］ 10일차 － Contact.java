import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= 10; tc++) {
			sb.append("#" + tc + " ");
			int n = sc.nextInt();
			int start = sc.nextInt();
			// 100명의 사람이 누구한테로 연락할지 저장, 단방향
			List<Integer>[] contact = new ArrayList[101];
			for (int i = 1; i <= 100; i++) {
				contact[i] = new ArrayList<>();
			}
			for (int i = 0; i < n / 2; i++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				contact[from].add(to);
			}
			int[] visited = new int[101];
			int turn = 1;
			visited[start] = 1;
			Queue<Integer> queue = new LinkedList<>();
			queue.offer(start);
			while (!queue.isEmpty()) {
				int k = queue.poll();
				for (int x : contact[k]) {
					if (visited[x] == 0) {
						visited[x] = visited[k] + 1;
						turn = visited[x];
						queue.offer(x);
					}
				}
			}
			int ans = -1;
			for (int i = 1; i <= 100; i++) {
				if (visited[i] == turn) ans = i;
			}
			sb.append(ans + "\n");
		}
		System.out.println(sb);
	}
}