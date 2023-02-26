import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		ArrayList<ArrayList<Integer>> tree = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < n + 1; i++) {
			tree.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < n - 1; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			tree.get(a).add(b);
			tree.get(b).add(a);
		}
		int[] visited = new int[n + 1];
		visited[1] = 1;
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(1);
		int[] parent = new int[n + 1];
		while (queue.size() > 0) {
			int p = queue.poll();
			for (int c : tree.get(p)) {
				if (visited[c] == 0) {
					visited[c] = 1;
					queue.offer(c);
					parent[c] = p;
				}
			}
		}
		for (int i = 2; i < n + 1; i++) {
			System.out.println(parent[i]);
		}

	}

}
