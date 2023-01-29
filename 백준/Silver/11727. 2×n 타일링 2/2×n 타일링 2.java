import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Integer[] dp = new Integer[n + 1];
		dp[0] = 0;
		dp[1] = 1;
		if (n >= 2) {
			dp[2] = 3;
			for (int i = 2; i < n; i++) {
				if (i % 2 == 0) {
					dp[i + 1] = (dp[i] * 2 - 1) % 10007;
				} else {
					dp[i + 1] = (dp[i] * 2 + 1) % 10007;
				}
			}
		}
		System.out.println(dp[n]);
		sc.close();
	}
}