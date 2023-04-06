import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		// 구슬
		boolean[][] board = new boolean[n + 1][n + 1];
		for (int i = 0; i < m; i++) {
			// a가 b보다 무겁다 => 뒤에 오는게 더 크다
			int a = sc.nextInt();
			int b = sc.nextInt();
			board[b][a] = true;
		}
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					// i보다 k가 크고 k보다 j가 크면 i보다 j가 크다
					if (i != j) {
						if (board[i][k] && board[k][j]) {
							board[i][j] = true;
						}
					}
				}
			}
		}
		int count = 0;
		for (int i = 1; i <= n; i++) {
			int heavier = 0;
			int lighter = 0;
			for (int j = 1; j <= n; j++) {
				// i보다 j가 무겁다
				if (board[i][j]) {
					heavier++;
				}
				// i보다 j가 가볍다
				if (board[j][i]) {
					lighter++;
				}
			}
			if (heavier > n / 2 || lighter > n / 2) count++;
		}
		System.out.println(count);
	}
}