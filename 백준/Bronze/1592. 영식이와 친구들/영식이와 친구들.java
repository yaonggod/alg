import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int l = sc.nextInt();
		int[] friends = new int[n];
		int t = 0;
		int idx = 0;
		while (true) {
			friends[idx]++;
			if (friends[idx] == m) {
				break;
			}
			else if (friends[idx] % 2 == 1) {
				idx += l;
				if (idx >= n) {
					idx -= n;
				}
			} else {
				idx -= l;
				if (idx < 0) {
					idx += n;
				}
			}
			t++;
		}
		System.out.println(t);
	}
}