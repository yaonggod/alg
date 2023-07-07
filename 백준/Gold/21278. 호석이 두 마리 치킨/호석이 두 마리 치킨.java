import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[][] graph = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			Arrays.fill(graph[i], Integer.MAX_VALUE);
		}
		
		
		for (int i = 1; i <= n; i++) {
			graph[i][i] = 0;
		}
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			graph[a][b] = 1;
			graph[b][a] = 1;
		}
		
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (i != j && i != k && k != j) {
						if (graph[i][k] != Integer.MAX_VALUE && graph[k][j] != Integer.MAX_VALUE && graph[i][k] + graph[k][j] <= graph[i][j]) {
							graph[i][j] = graph[i][k] + graph[k][j];
						}
					}
				}
			}
		}
		
		int chicken1 = 0;
		int chicken2 = 0;
		int sum = Integer.MAX_VALUE;
		for (int i = 1; i < n; i++) {
			for (int j = i + 1; j <= n; j++) {
				if (i != j) {
					int s = 0;
					for (int k = 1; k <= n; k++) {
						s += Math.min(graph[i][k], graph[j][k]);
					}
					if (s < sum) {
						chicken1 = i;
						chicken2 = j;
						sum = s;
					}
				}
			}
		}
		
		System.out.printf("%d %d %d", chicken1, chicken2, sum * 2);
	}
}