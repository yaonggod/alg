import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int n;
	static int[] min, len;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		int[] dp = new int[n];
		Arrays.fill(dp, 1);
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[i] < arr[j] && dp[j] + 1 > dp[i]) {
					dp[i] = dp[j] + 1;
				}
			}
		}
		int max = 0;
		for (int i = 0; i < n; i++) {
			if (dp[i] > max) max = dp[i];
		}
		System.out.println(max);
	}
}