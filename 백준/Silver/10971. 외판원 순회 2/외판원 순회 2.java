import java.util.Scanner;

public class Main {
	static int n, minCost;
	static boolean[] visited;
	static int[] arr;
	static int[][] matrix;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		// 행렬
		// i번 도시에서 j번 도시로 가기 위한 비용
		// 비용 == 0이면 갈 수 없음
		matrix = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = sc.nextInt();
			}
		}
		// n개 도시 순회
		arr = new int[n];
		visited = new boolean[n];
		minCost = Integer.MAX_VALUE;
		// 시작점
		for (int i = 0; i < n; i++) {
			arr[0] = i;
			visited[i] = true;
			// 1번 인덱스부터 넣어보자
			tsp(1, 0);
			visited[i] = false;
		}
		System.out.println(minCost);
	}	
	
	public static void tsp(int idx, int cost) {
		if (idx == n) {
			// 돌아가는 길이 있으면
			if (matrix[arr[n - 1]][arr[0]] != 0) {
				if (cost + matrix[arr[n - 1]][arr[0]] < minCost) {
					minCost = cost + matrix[arr[n - 1]][arr[0]];
				}
			}
			return;
		}
		for (int i = 0; i < n; i++) {
			// 가본 적 없고 arr[idx - 1] -> i에 경로가 있다
			if (!visited[i] && matrix[arr[idx - 1]][i] != 0) {
				visited[i] = true;
				arr[idx] = i;
				tsp(idx + 1, cost + matrix[arr[idx - 1]][i]);
				visited[i] = false;
			}
		}
		
	}
}