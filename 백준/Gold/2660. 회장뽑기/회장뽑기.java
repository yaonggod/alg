import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 회원 수
		int n = sc.nextInt();
		// 회원 idx는 누구랑 친구인가
		List<Integer>[] friends = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			friends[i] = new ArrayList<>();
		}
		while (true) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			if (a == b && a == -1) {
				break;
			}
			friends[a].add(b);
			friends[b].add(a);
		}
		// 점수
		int[] score = new int[n + 1];
		// 사람마다 큐 돌려보자
		for (int i = 1; i <= n; i++) {
			Queue<Integer[]> queue = new LinkedList<>();
			boolean[] visited = new boolean[n + 1];
			// 첫 번째 사람 넣자
			// i를 몇 다리 건너 만나는지
			queue.offer(new Integer[] {i, 0});
			visited[i] = true;
			// 조건에 다 만날 수 있다고 되어있음
			while (!queue.isEmpty()) {
				Integer[] xc = queue.poll();
				int x = xc[0];
				int c = xc[1];
				// 몇 다리 건너 만나는지 갱신
				score[i] = c;
				for (int y : friends[x]) {
					if (!visited[y]) {
						visited[y] = true;
						queue.offer(new Integer[] {y, c + 1});
					}
				}
			}
		}
		int minScore = Integer.MAX_VALUE;
		List<Integer> cand = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			if (score[i] < minScore) {
				minScore = score[i];
			}
		}
		for (int i = 1; i <= n; i++) {
			if (score[i] == minScore) {
				cand.add(i);
			}
		}
		System.out.println(minScore + " " + cand.size());
		for (int c : cand) {
			System.out.print(c + " ");
		}
	}	
}