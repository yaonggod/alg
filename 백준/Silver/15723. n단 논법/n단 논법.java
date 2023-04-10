import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		boolean[][] matrix = new boolean[26][26];
		int n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			int a = sc.next().toCharArray()[0] - 'a';
			char[] is = sc.next().toCharArray();
			int b = sc.next().toCharArray()[0] - 'a';
			matrix[a][b] = true;
		}
		for (int k = 0; k < 26; k++) {
			for (int i = 0; i < 26; i++) {
				for (int j = 0; j < 26; j++) {
					if (i != k && k != j && i != j) {
						if (matrix[i][k] && matrix[k][j]) {
							matrix[i][j] = true;
						}
					}
				}
			}
		}
		int m = sc.nextInt();
		for (int i = 0; i < m; i++) {
			int a = sc.next().toCharArray()[0] - 'a';
			char[] is = sc.next().toCharArray();
			int b = sc.next().toCharArray()[0] - 'a';
			if (matrix[a][b]) sb.append("T\n");
			else sb.append("F\n");
		}
		System.out.println(sb);
	}
}