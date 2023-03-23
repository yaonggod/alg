import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int n = 0;
	static int m = 0;
	static int[] arr = null;
	static int[] newarr = null;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		arr = new int[n];
		newarr = new int[m];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		backtracking(0, 0);
		System.out.println(sb);
	}
	
	public static void backtracking(int idx, int c) {
		if (c == m) {
			for (int e : newarr) {
				sb.append(e + " ");
			}
			sb.append("\n");
			return;
		}
		for (int i = idx; i < n; i++) {
			newarr[c] = arr[i];
			// 내 뒤엔 i보다 크거나 같은 애들만 넣자
			backtracking(i, c + 1);
		}
	}
}