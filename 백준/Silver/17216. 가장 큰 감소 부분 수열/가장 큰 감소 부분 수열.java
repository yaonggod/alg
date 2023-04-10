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
		dp[0] = arr[0];
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[i] < arr[j] && dp[j] + arr[i] > dp[i]) {
					dp[i] = dp[j] + arr[i];
				}
			}
			if (dp[i] == 0) dp[i] = arr[i];
		}
		int max = 0;
		for (int i = 0; i < n; i++) {
			if (dp[i] > max) max = dp[i];
		}
		System.out.println(max);
	}
}