import java.util.Scanner;

public class Main {
	static int count = 0;
	static int n = 0;
	static int s = 0;
	static int[] arr = null;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		s = sc.nextInt();
		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		finds();
		System.out.println(count);	
	}
	
	public static void finds() {
		for (int i = 1; i < (1 << n); i++) {
			int sum = 0;
			// j번째 인덱스가 있으면 더함
			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) != 0) {
					sum += arr[j];
				}
			}
			if (sum == s) {
				count++;
			}
		}
	}
}