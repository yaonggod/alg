import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int q = sc.nextInt();
		Set<Integer> occupied = new HashSet<>();
		for (int i = 0; i < q; i++) {
			int x = sc.nextInt();
			int y = x;
			int o = 0;
			while (y >= 1) {
				if (occupied.contains(y)) {
					o = y;
				}
				y /= 2;
			}
			if (o == 0) {
				occupied.add(x);
			}
			System.out.println(o);
		}
	}
}