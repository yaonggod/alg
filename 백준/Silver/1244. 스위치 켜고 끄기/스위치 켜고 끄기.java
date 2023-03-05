import java.util.Scanner;

public class Main {
	static int[] s;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		s = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			s[i] = sc.nextInt();
		}
		int st = sc.nextInt();
		for (int i = 0; i < st; i++) {
			int gender = sc.nextInt();
			int c = sc.nextInt();
			if (gender == 1) {
				int m = 1;
				while (c * m <= n) {
					change(c * m);
					m++;
				}
			} else {
				change(c);
				int add = 1;
				while (c + add <= n && c - add >= 1 && s[c + add] == s[c - add]) {
					change(c + add);
					change(c - add);
					add++;
				}
			}
		}
		for (int i = 0; i <= n / 20; i++) {
			for (int j = 1; j <= 20; j++) {
				if (i * 20 + j <= n) {
					System.out.print(s[i * 20 + j] + " ");
				}
			}
			System.out.println();
		}
	}
	public static void change(int i) {
		if (s[i] == 0) {
			s[i] = 1;
		} else {
			s[i] = 0;
		}
	}
}