import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		long ans = arr[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			if (arr[i] > ans) {
				ans = arr[i];
			} else if (arr[i] < ans && ans % arr[i] != 0) {
				ans = (ans / arr[i] + 1) * arr[i];	
			}
		}
		System.out.println(ans);
	}
}