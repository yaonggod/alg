import java.util.Scanner;

public class Main {
	static int[] p;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int tc = 0; tc < t; tc++) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			p = new int[n + 1];
			for (int i = 1; i <= n; i++) {
				p[i] = i;
			}
			int plane = 0;
			for (int i = 0; i < m; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				if (findset(a) != findset(b)) {
					plane++;
					union(a, b);
				}
			}
			System.out.println(plane);
		}
	}
	
	static int findset(int x) {
		if (p[x] != x) p[x] = findset(p[x]);
		return p[x];
	}
	static void union(int x, int y) {
		p[findset(y)] = findset(x);
	}
}