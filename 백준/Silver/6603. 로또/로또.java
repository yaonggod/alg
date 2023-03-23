import java.util.Scanner;

public class Main {
	static int k = 0;
	static int[] arr = null;
	static int[] lotto = new int[6];
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			k = sc.nextInt();
			if (k == 0) {
				break;
			}
			arr = new int[k];
			for (int i = 0; i < k; i++) {
				arr[i] = sc.nextInt();
			}
			backtracking(0, 0);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static void backtracking(int idx, int c) {
		if (c == 6) {
			for (int n : lotto) {
				sb.append(n + " ");
			}
			sb.append("\n");
			return;
		}
		if (idx == k) {
			return;
		}
		for (int i = idx; i < k; i++) {
			lotto[c] = arr[i];
			backtracking(i + 1, c + 1);
		}
	}
}