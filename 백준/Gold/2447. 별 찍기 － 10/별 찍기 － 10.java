import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int n;
	static char[][] answer;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		n = sc.nextInt();
		answer = new char[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(answer[i], ' ');
		}
		fill(n, 0, 0);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(answer[i][j]);
			}
			if (i != n - 1) {
				sb.append("\n");
			}
		} 
		System.out.println(sb);
	}
	
	public static void fill(int n, int x, int y) {
		if (n == 3) {
			for (int i = x; i < x + 3; i++) {
				for (int j = y; j < y + 3; j++) {
					answer[i][j] = '*';
				}
			}
			answer[x + 1][y + 1] = ' ';
			return;
		}
		// n / 3만큼 뛰자
		for (int i = x; i < x + n; i += n / 3) {
			for (int j = y; j < y + n; j += n / 3) {
				if (!(i == x + n / 3 && j == y + n / 3)) {
					fill(n / 3, i, j);
				}
			}
		}
	}
}
